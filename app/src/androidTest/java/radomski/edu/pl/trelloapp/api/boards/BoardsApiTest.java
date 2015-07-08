package radomski.edu.pl.trelloapp.api.boards;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import radomski.edu.pl.trelloapp.BlockingTestObserver;
import radomski.edu.pl.trelloapp.BuildConfig;
import radomski.edu.pl.trelloapp.api.BaseTest;
import radomski.edu.pl.trelloapp.api.cards.Card;

import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by adam on 7/7/15.
 */
@RunWith(AndroidJUnit4.class)
public class BoardsApiTest extends BaseTest {

    private BoardsApi boardsApi;

    @Before
    public void setUp() {
        boardsApi = new BoardsApi();
    }


    @Test
    public void getLists() {

        BlockingTestObserver<List<TroelloList>> listBlockingTestObserver;
        listBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        boardsApi.getLists(BuildConfig.BOARD_ID).subscribe(listBlockingTestObserver);
        waitUntilCompleted();

        Assert.assertEquals(1, listBlockingTestObserver.getOnNextEvents().size());
        List<TroelloList> list = listBlockingTestObserver.getOnNextEvents().get(0);

        assertEquals(3, list.size());

        for (TroelloList troelloList : list) {
            assertEquals(BuildConfig.BOARD_ID_API, troelloList.getIdBoard());
            assertThat(troelloList.getName(), isOneOf("To do", "Doing", "Done"));
        }


    }

    @Test
    public void getCards() {

        BlockingTestObserver<List<Card>> cardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        boardsApi.getCards(BuildConfig.BOARD_ID).subscribe(cardBlockingTestObserver);
        waitUntilCompleted();

        Assert.assertEquals(1, cardBlockingTestObserver.getOnNextEvents().size());
        List<Card> card = cardBlockingTestObserver.getOnNextEvents().get(0);


    }


}
