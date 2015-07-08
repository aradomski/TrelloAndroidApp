package radomski.edu.pl.trelloapp.ux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import radomski.edu.pl.trelloapp.BuildConfig;
import radomski.edu.pl.trelloapp.api.boards.BoardsApi;
import radomski.edu.pl.trelloapp.api.boards.TroelloList;
import radomski.edu.pl.trelloapp.api.cards.Card;
import rx.Observable;

/**
 * Created by adam on 7/8/15.
 */
public class RxHelper {

    public static Observable<Map<TroelloList, List<Card>>> loadData(BoardsApi boardsApi) {
        return boardsApi.getLists(BuildConfig.BOARD_ID).flatMap(troelloLists -> boardsApi.getCards(BuildConfig.BOARD_ID).map(cards -> {
            Map<TroelloList, List<Card>> listCardMap = new HashMap<>();
            List<Card> sortedCards;
            for (TroelloList troelloList : troelloLists) {
                sortedCards = new ArrayList<>();
                for (Card card : cards) {
                    if (card.idList.equals(troelloList.getId())) {
                        sortedCards.add(card);
                    }
                }
                listCardMap.put(troelloList, sortedCards);
            }
            return listCardMap;
        }));
    }

}
