package radomski.edu.pl.trelloapp.ux;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import radomski.edu.pl.trelloapp.R;
import radomski.edu.pl.trelloapp.api.boards.BoardsApi;
import radomski.edu.pl.trelloapp.api.boards.TroelloList;
import radomski.edu.pl.trelloapp.api.cards.Card;
import radomski.edu.pl.trelloapp.di.DI;
import radomski.edu.pl.trelloapp.holdr.Holdr_CardEditFragment;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by adam on 7/8/15.
 */
public class CardEditDialogFragment extends DialogFragment {
    public static final String CARD_ID = "cardID";

    public interface OnDialogActions {
        void onCardEdited(Card originalCard, Card editedCard);

        void onCardCreated(Card newCard);

    }

    public static CardEditDialogFragment create(String cardID) {
        CardEditDialogFragment cardEditDialogFragment = new CardEditDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CARD_ID, cardID);
        cardEditDialogFragment.setArguments(bundle);
        return cardEditDialogFragment;
    }


    @Inject
    BoardsApi boardsApi;


    private Card editedCard;
    private List<Subscription> subscriptions = new LinkedList<>();
    private Map<TroelloList, List<Card>> troelloListListMap;
    private Holdr_CardEditFragment holder;
    private OnDialogActions onDialogActions;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DI.di().injections().inject(this);
        progress(true);
        subscribe(RxHelper.loadData(boardsApi), troelloListListMap -> {
            this.troelloListListMap = troelloListListMap;
            if (getArguments() != null && getArguments().containsKey(CARD_ID)) {
                String editedCardId = getArguments().getString(CARD_ID);
                extractCard(troelloListListMap, editedCardId);
                holder.cardEditDesc.setText(editedCard.desc);
                holder.cardEditName.setText(editedCard.name);

                TroelloList list = extractListById(editedCard.idList);
                switch (list.getName()) {
                    case "To do":
                        holder.cardEditListToDo.setChecked(true);
                        break;
                    case "Doing":
                        holder.cardEditListDoing.setChecked(true);
                        break;
                    case "Done":
                        holder.cardEditListDone.setChecked(true);
                        break;
                }
            }
            progress(false);
        });
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        holder = new Holdr_CardEditFragment(LayoutInflater
                .from(getActivity()).inflate(Holdr_CardEditFragment.LAYOUT, null, false));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(holder.getView())
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                })
                .setNegativeButton(android.R.string.cancel, (dialog1, which1) -> {
                })
                .setCancelable(false);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
            if (onDialogActions != null) {
                onPositiveButtonClicked();
            } else {
                dismiss();
            }
        });

    }

    private void onPositiveButtonClicked() {
        if (holder.cardEditName.getText().toString().trim().length() == 0 || holder.cardEditDestinationList.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
            return;
        }
        Card card = new Card();
        card.name = holder.cardEditName.getText().toString();
        card.desc = holder.cardEditDesc.getText().toString();
        switch (holder.cardEditDestinationList.getCheckedRadioButtonId()) {
            case R.id.card_edit_list_to_do:
                card.idList = extractListByName("To do").getId();
                break;
            case R.id.card_edit_list_doing:
                card.idList = extractListByName("Doing").getId();
                break;
            case R.id.card_edit_list_done:
                card.idList = extractListByName("Done").getId();
                break;
        }
        if (editedCard != null) {
            card.id = editedCard.id;
            onDialogActions.onCardEdited(editedCard, card);
        } else {
            onDialogActions.onCardCreated(card);
        }
        dismiss();
    }

    private TroelloList extractListByName(String name) {
        for (TroelloList troelloList : troelloListListMap.keySet()) {
            if (troelloList.getName().trim().equalsIgnoreCase(name)) {
                return troelloList;
            }
        }
        throw new RuntimeException("List should be found! " + name);
    }

    private TroelloList extractListById(String id) {
        for (TroelloList troelloList : troelloListListMap.keySet()) {
            if (troelloList.getId().trim().equalsIgnoreCase(id)) {
                return troelloList;
            }
        }
        throw new RuntimeException("List should be found! " + id);
    }

    private void extractCard(Map<TroelloList, List<Card>> troelloListListMap, String editedCardId) {
        for (Map.Entry<TroelloList, List<Card>> troelloListListEntry : troelloListListMap.entrySet()) {
            for (Card card : troelloListListEntry.getValue()) {
                if (card.id.equals(editedCardId)) {
                    editedCard = card;
                    break;
                }
            }
        }
    }

    private void progress(boolean visible) {
        if (holder != null) {
            getActivity().runOnUiThread(() -> holder.progress.setVisibility(visible ? View.VISIBLE : View.GONE));
        }
    }

    public void setOnDialogActions(OnDialogActions onDialogActions) {
        this.onDialogActions = onDialogActions;
    }

    @Override
    public void onDestroyView() {
        dispose();
        super.onDestroyView();
    }

    protected <T> void subscribe(Observable<T> signal, Action1<T> next) {
        subscribe(signal, next, (error) -> onCommonError(error));
    }

    protected void onCommonError(Throwable error) {
        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
        Log.d("Common error:", error.getMessage(), error);
        progress(false);
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
}
