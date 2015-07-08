package radomski.edu.pl.trelloapp.test;

import android.content.Context;

import dagger.Provides;

@dagger.Module
public class TestModule {

    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }
}