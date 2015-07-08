package radomski.edu.pl.trelloapp.di;

import android.content.Context;

import dagger.Provides;
import radomski.edu.pl.trelloapp.ux.BaseActivity;

@dagger.Module
public class UiModule {

    protected final BaseActivity activity;


    public UiModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public Context context() {
        return activity;
    }


    @Provides
    public BaseActivity baseActivity() {
        return activity;
    }


}
