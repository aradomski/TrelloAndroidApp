package radomski.edu.pl.trelloapp.api.board;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import radomski.edu.pl.trelloapp.BlockingTestObserver;
import radomski.edu.pl.trelloapp.BuildConfig;
import radomski.edu.pl.trelloapp.api.BaseTest;

import static org.junit.Assert.assertEquals;

/**
 * Created by adam on 7/6/15.
 */
@RunWith(AndroidJUnit4.class)
public class BoardApiTest extends BaseTest {

    private BoardApi boardApi;

    @Before
    public void setUp() {
        boardApi = new BoardApi();
    }

    @Test
    public void getBoard() {

        BlockingTestObserver<Board> boardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        boardApi.getBoard(BuildConfig.BOARD_ID).subscribe(boardBlockingTestObserver);
        waitUntilCompleted();

        Assert.assertEquals(1, boardBlockingTestObserver.getOnNextEvents().size());
        Board board = boardBlockingTestObserver.getOnNextEvents().get(0);

        assertEquals(BuildConfig.BOARD_ID_API, board.getId());
        assertEquals("My_Board", board.getName());
        assertEquals("https://trello.com/b/" + BuildConfig.BOARD_ID + "/my-board", board.getUrl());
    }

}
