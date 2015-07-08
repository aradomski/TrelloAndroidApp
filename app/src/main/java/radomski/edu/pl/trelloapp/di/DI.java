package radomski.edu.pl.trelloapp.di;

import radomski.edu.pl.trelloapp.ux.BaseActivity;
import radomski.edu.pl.trelloapp.ux.CardEditDialogFragment;
import radomski.edu.pl.trelloapp.ux.MainActivity;

/**
 * Created by adam on 7/6/15.
 */
public class DI {

    private static DI di = new DI();
    private Injections injections = new Injections();
    private UiComponent uiComponent;

    public static DI di() {
        return di;
    }

    public Injections injections() {
        return injections;
    }

    public class Injections {


        public void inject(MainActivity mainActivity) {
            UiComponent uiComponent = getUiComponent(mainActivity);
            uiComponent.inject(mainActivity);
        }

        public void inject(CardEditDialogFragment cardEditDialogFragment) {
            UiComponent uiComponent = getUiComponent((BaseActivity) cardEditDialogFragment.getActivity());
            uiComponent.inject(cardEditDialogFragment);
        }
    }

    private UiComponent getUiComponent(BaseActivity mainActivity) {
        if (uiComponent == null) {
            uiComponent = DaggerUiComponent.builder().uiModule(new UiModule(mainActivity)).build();
        }
        return uiComponent;
    }
}
