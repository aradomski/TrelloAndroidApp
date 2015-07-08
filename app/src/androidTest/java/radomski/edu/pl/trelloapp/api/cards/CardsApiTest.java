package radomski.edu.pl.trelloapp.api.cards;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import radomski.edu.pl.trelloapp.BlockingTestObserver;
import radomski.edu.pl.trelloapp.BuildConfig;
import radomski.edu.pl.trelloapp.api.BaseTest;

import static org.junit.Assert.assertEquals;

/**
 * Created by adam on 7/7/15.
 */
@RunWith(AndroidJUnit4.class)
public class CardsApiTest extends BaseTest {

    private CardsApi cardsApi;
    String cardName = "Karta z testu andoid";
    String cardDesc = "Teest";
    String listID1 = BuildConfig.LIST_1;
    String listID2 = BuildConfig.LIST_2;
    String listID3 = BuildConfig.LIST_3;
    private Card newCard;

    @Before
    public void setUp() {
        cardsApi = new CardsApi();


        BlockingTestObserver<Card> cardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.createCard(cardName, cardDesc, listID1, BuildConfig.TOKEN).subscribe(cardBlockingTestObserver);
        waitUntilCompleted();

        assertEquals(1, cardBlockingTestObserver.getOnNextEvents().size());
        newCard = cardBlockingTestObserver.getOnNextEvents().get(0);
    }

    @After
    public void tearDown() {
        BlockingTestObserver<CardDeleteResponse> deleteCardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.deleteCard(newCard.id, BuildConfig.TOKEN).subscribe(deleteCardBlockingTestObserver);
        waitUntilCompleted();
        assertEquals(1, deleteCardBlockingTestObserver.getOnNextEvents().size());
    }


    @Test
    public void moveCard() {

        BlockingTestObserver<Card> cardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.moveCard(newCard.id, listID3, BuildConfig.TOKEN).subscribe(cardBlockingTestObserver);
        waitUntilCompleted();

        assertEquals(1, cardBlockingTestObserver.getOnNextEvents().size());
        Card card = cardBlockingTestObserver.getOnNextEvents().get(0);

        assertEquals(newCard.id, card.id);
        assertEquals(listID3, card.idList);


        BlockingTestObserver<Card> cardBlockingTestObserver2 = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.moveCard(newCard.id, listID2, BuildConfig.TOKEN).subscribe(cardBlockingTestObserver2);
        waitUntilCompleted();

        assertEquals(1, cardBlockingTestObserver2.getOnNextEvents().size());
        Card card2 = cardBlockingTestObserver2.getOnNextEvents().get(0);

        assertEquals(newCard.id, card2.id);
        assertEquals(listID2, card2.idList);
    }

    @Test
    public void createCard() {
        BlockingTestObserver<Card> cardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.createCard(cardName, cardDesc, listID1, BuildConfig.TOKEN).subscribe(cardBlockingTestObserver);
        waitUntilCompleted();

        assertEquals(1, cardBlockingTestObserver.getOnNextEvents().size());
        Card card = cardBlockingTestObserver.getOnNextEvents().get(0);
        assertEquals(cardName, card.name);
        assertEquals(cardDesc, card.desc);
        assertEquals(listID1, card.idList);

        BlockingTestObserver<CardDeleteResponse> deleteCardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.deleteCard(card.id, BuildConfig.TOKEN).subscribe(deleteCardBlockingTestObserver);
        waitUntilCompleted();
        assertEquals(1, deleteCardBlockingTestObserver.getOnNextEvents().size());
    }

    @Test
    public void deleteCard() {
        BlockingTestObserver<Card> cardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.createCard(cardName, cardDesc, listID1, BuildConfig.TOKEN).subscribe(cardBlockingTestObserver);
        waitUntilCompleted();

        assertEquals(1, cardBlockingTestObserver.getOnNextEvents().size());
        Card card = cardBlockingTestObserver.getOnNextEvents().get(0);


        BlockingTestObserver<CardDeleteResponse> deleteCardBlockingTestObserver = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.deleteCard(card.id, BuildConfig.TOKEN).subscribe(deleteCardBlockingTestObserver);
        waitUntilCompleted();

        assertEquals(1, deleteCardBlockingTestObserver.getOnNextEvents().size());
        CardDeleteResponse response = deleteCardBlockingTestObserver.getOnNextEvents().get(0);
        assertEquals(null, response.getValue());

    }


    @Test
    public void editCardName() {
        String newCardName = "new Card Name";


        BlockingTestObserver<Card> cardBlockingTestObserver2 = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.editCardName(newCard.id, newCardName, BuildConfig.TOKEN).subscribe(cardBlockingTestObserver2);
        waitUntilCompleted();

        assertEquals(1, cardBlockingTestObserver2.getOnNextEvents().size());
        Card card2 = cardBlockingTestObserver2.getOnNextEvents().get(0);

        assertEquals(newCard.id, card2.id);
        assertEquals(newCardName, card2.name);
    }

    @Test
    public void editCardDesc() {
        String newCardDesc = "new Card Desc";


        BlockingTestObserver<Card> cardBlockingTestObserver2 = new BlockingTestObserver<>(getCountDownLatch());
        cardsApi.editCardDesc(newCard.id, newCardDesc, BuildConfig.TOKEN).subscribe(cardBlockingTestObserver2);
        waitUntilCompleted();

        assertEquals(1, cardBlockingTestObserver2.getOnNextEvents().size());
        Card card2 = cardBlockingTestObserver2.getOnNextEvents().get(0);

        assertEquals(newCard.id, card2.id);
        assertEquals(newCardDesc, card2.desc);
    }


}
