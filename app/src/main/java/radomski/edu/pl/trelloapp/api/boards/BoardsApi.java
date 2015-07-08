package radomski.edu.pl.trelloapp.api.boards;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import radomski.edu.pl.trelloapp.BuildConfig;
import radomski.edu.pl.trelloapp.api.BaseApi;
import radomski.edu.pl.trelloapp.api.cards.Card;
import rx.Observable;

/**
 * Created by adam on 7/6/15.
 */
@Singleton
public class BoardsApi extends BaseApi {

    private BoardsApiInterface boardsApiInterface;


    @Inject
    public BoardsApi() {
        boardsApiInterface = getRestAdapter().create(BoardsApiInterface.class);
    }

    public Observable<List<TroelloList>> getLists(String boardID) {
        return boardsApiInterface.getLists(boardID, "id,name,idBoard", BuildConfig.APP_KEY);
    }

    public Observable<List<Card>> getCards(String boardID) {
        return boardsApiInterface.getCards(boardID, "id,name,idList,url,desc", BuildConfig.APP_KEY);
    }
}
