package radomski.edu.pl.trelloapp.ux;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import radomski.edu.pl.trelloapp.BuildConfig;
import radomski.edu.pl.trelloapp.R;
import radomski.edu.pl.trelloapp.api.boards.BoardsApi;
import radomski.edu.pl.trelloapp.api.boards.TroelloList;
import radomski.edu.pl.trelloapp.api.cards.Card;
import radomski.edu.pl.trelloapp.api.cards.CardsApi;
import radomski.edu.pl.trelloapp.holdr.Holdr_MainActivity;
import rx.Observable;


public class MainActivity extends BaseActivity {
    public static final String EDIT_DIALOG_TAG = "edit_dialog_tag";
    @Inject
    BoardsApi boardsApi;
    @Inject
    CardsApi cardsApi;
    @Inject
    ListsPagerAdapter listsPagerAdapter;

    private boolean tabIsInited;
    private Holdr_MainActivity holdr;
    private CardEditDialogFragment cardEditDialogFragment;
    private CardEditDialogFragment.OnDialogActions onDialogsActions = new CardEditDialogFragment.OnDialogActions() {
        @Override
        public void onCardEdited(Card originalCard, Card editedCard) {
            progress(true);
            if (!originalCard.id.equals(editedCard.id)) {
                throw new RuntimeException("Something went wrong, ids are not equal. originalCard.id = "
                        + originalCard.id + " editedCard.id = " + editedCard.id);
            }
            List<Observable> observables = new ArrayList<>();
            if (!originalCard.idList.equals(editedCard.idList)) {
                observables.add(cardsApi.moveCard(originalCard.id, editedCard.idList, BuildConfig.TOKEN));
            }
            if (!originalCard.name.equals(editedCard.name)) {
                observables.add(cardsApi.editCardName(originalCard.id, editedCard.name, BuildConfig.TOKEN));
            }
            if (!originalCard.desc.equals(editedCard.desc)) {
                observables.add(cardsApi.editCardDesc(originalCard.id, editedCard.desc, BuildConfig.TOKEN));
            }

            subscribe(Observable.just(observables), list -> {
                for (Observable observable : list) {
                    observable.toBlocking().firstOrDefault(null);
                }
                refresh();
            });

        }

        @Override
        public void onCardCreated(Card newCard) {
            progress(true);
            subscribe(cardsApi.createCard(newCard.name, newCard.desc, newCard.idList, BuildConfig.TOKEN), card -> {
                refresh();
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        holdr = new Holdr_MainActivity(findViewById(android.R.id.content));
        if ((cardEditDialogFragment = (CardEditDialogFragment) getFragmentManager().findFragmentByTag(EDIT_DIALOG_TAG)) != null) {
            cardEditDialogFragment.setOnDialogActions(onDialogsActions);
        }
        holdr.mainNewCardButton.setOnClickListener(v -> {
            cardEditDialogFragment = new CardEditDialogFragment();
            cardEditDialogFragment.setOnDialogActions(onDialogsActions);
            cardEditDialogFragment.show(getFragmentManager(), EDIT_DIALOG_TAG);
        });

        holdr.mainViewPager.setAdapter(listsPagerAdapter);
        holdr.mainViewPager.setOffscreenPageLimit(0);

        listsPagerAdapter.setOnItemClickListener(card -> {
            cardEditDialogFragment = CardEditDialogFragment.create(card.id);
            cardEditDialogFragment.setOnDialogActions(onDialogsActions);
            cardEditDialogFragment.show(getFragmentManager(), EDIT_DIALOG_TAG);
        });
        listsPagerAdapter.setOnLongItemClickListener(card -> {
            progress(true);
            cardsApi.deleteCard(card.id, BuildConfig.TOKEN).subscribe(cardDeleteResponse -> {
                refresh();
            });
        });
    }

    @Override
    protected void injectDependencies() {
        injections.inject(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    protected void onCommonError(Throwable error) {
        super.onCommonError(error);
        progress(false);
    }

    private void refresh() {
        progress(true);
        subscribe(RxHelper.loadData(boardsApi), troelloListListMap -> {
            List<Map.Entry<TroelloList, List<Card>>> troelloLists = new ArrayList<>();
            troelloLists.addAll(troelloListListMap.entrySet());
            Collections.sort(troelloLists, (lhs, rhs) -> lhs.getKey().getName().compareTo(rhs.getKey().getName()));
            listsPagerAdapter.setItems(troelloLists);
            if (!tabIsInited) {
                holdr.mainTabLayout.setupWithViewPager(holdr.mainViewPager);
                tabIsInited = true;
            }
            progress(false);
        });
    }

    private void progress(boolean visible) {
        runOnUiThread(() -> holdr.progress.setVisibility(visible ? View.VISIBLE : View.GONE));
    }
}
