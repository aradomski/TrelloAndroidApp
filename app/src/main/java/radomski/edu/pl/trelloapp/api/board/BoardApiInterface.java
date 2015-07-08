package radomski.edu.pl.trelloapp.api.board;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by adam on 7/6/15.
 */
public interface BoardApiInterface {

    @GET("/board/{boardID}/")
    Observable<Board> getBoard(@Path("boardID") String boardID, @Query("fields") String fields, @Query("key") String key);


}
