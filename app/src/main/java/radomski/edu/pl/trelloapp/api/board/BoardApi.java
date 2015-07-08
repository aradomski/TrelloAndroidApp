package radomski.edu.pl.trelloapp.api.board;

import javax.inject.Inject;
import javax.inject.Singleton;

import radomski.edu.pl.trelloapp.BuildConfig;
import radomski.edu.pl.trelloapp.api.BaseApi;
import rx.Observable;

/**
 * Created by adam on 7/6/15.
 */
@Singleton
public class BoardApi extends BaseApi {


    private BoardApiInterface mobileApi;

    @Inject
    public BoardApi() {
        mobileApi = getRestAdapter().create(BoardApiInterface.class);
    }

    public Observable<Board> getBoard(String boardID) {
        return mobileApi.getBoard(boardID, "id,name,url", BuildConfig.APP_KEY);
    }
}
