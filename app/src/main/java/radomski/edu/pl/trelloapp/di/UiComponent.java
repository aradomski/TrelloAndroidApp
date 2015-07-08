package radomski.edu.pl.trelloapp.di;


import javax.inject.Singleton;

import radomski.edu.pl.trelloapp.ux.CardEditDialogFragment;
import radomski.edu.pl.trelloapp.ux.MainActivity;

@Singleton
@dagger.Component(
        modules = {
                UiModule.class,
        })
public interface UiComponent extends BaseComponent {

    void inject(MainActivity mainActivity);


    void inject(CardEditDialogFragment cardEditDialogFragment);
}
