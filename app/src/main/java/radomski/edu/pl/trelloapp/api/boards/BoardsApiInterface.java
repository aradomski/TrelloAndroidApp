package radomski.edu.pl.trelloapp.api.boards;

import java.util.List;

import radomski.edu.pl.trelloapp.api.cards.Card;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by adam on 7/6/15.
 */
public interface BoardsApiInterface {
    @GET("/boards/{boardID}/lists")
    Observable<List<TroelloList>> getLists(@Path("boardID") String boardID, @Query("fields") String fields, @Query("key") String key);

    @GET("/boards/{boardID}/cards")
    Observable<List<Card>> getCards(@Path("boardID") String boardID, @Query("fields") String fields, @Query("key") String key);

}
