package radomski.edu.pl.trelloapp.ux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import radomski.edu.pl.trelloapp.di.DI;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by adam on 7/6/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected DI.Injections injections;
    private List<Subscription> subscriptions = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injections = DI.di().injections();
        injectDependencies();
    }

    protected void injectDependencies() {
        throw new RuntimeException("not injectable at this moment");
    }

    protected <T> void subscribe(Observable<T> signal, Action1<T> next) {
        subscribe(signal, next, (error) -> onCommonError(error));
    }

    protected void onCommonError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
        Log.d("Common error:", error.getMessage(), error);
    }

    protected <T> void subscribe(Observable<T> signal, Action1<T> next, Action1<Throwable> error) {
        sub(signal, next, error);
    }

    private <T> Subscription sub(Observable<T> obs, Action1<T> next, Action1<Throwable> error) {
        Subscription s = obs
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
        subscriptions.add(s);
        return s;
    }

    private <T, V> Subscription sub(Observable<T> sourceSignal, Func1<T, Observable<V>> presenterAction, Action1<V> next, Action1<Throwable> error) {
        return sourceSignal.subscribe(type -> sub(presenterAction.call(type), next, error));
    }

    private void dispose() {
        for (Subscription s : subscriptions) {
            s.unsubscribe();
        }
    }

    @Override
    protected void onStop() {
        dispose();
        super.onStop();
    }
}
