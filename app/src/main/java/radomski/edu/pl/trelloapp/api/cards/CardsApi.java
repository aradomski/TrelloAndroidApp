package radomski.edu.pl.trelloapp.api.cards;

import javax.inject.Inject;
import javax.inject.Singleton;

import radomski.edu.pl.trelloapp.BuildConfig;
import radomski.edu.pl.trelloapp.api.BaseApi;
import rx.Observable;

/**
 * Created by adam on 7/7/15.
 */
@Singleton
public class CardsApi extends BaseApi {


    private CardsApiInterface cardsApiInterface;


    @Inject
    public CardsApi() {
        cardsApiInterface = getRestAdapter().create(CardsApiInterface.class);
    }


    public Observable<Card> moveCard(String cardId, String destinationList, String token) {
        return cardsApiInterface.moveCard(cardId, destinationList, BuildConfig.APP_KEY, token);
    }

    public Observable<CardDeleteResponse> deleteCard(String cardId, String token) {
        return cardsApiInterface.deleteCard(cardId, BuildConfig.APP_KEY, token);
    }

    public Observable<Card> createCard(String name, String desription, String destinationList, String token) {
        return cardsApiInterface.createCard(destinationList, name, desription, BuildConfig.APP_KEY, token);
    }

    public Observable<Card> editCardName(String cardId, String newCardName, String token) {
        return cardsApiInterface.editCardName(cardId, newCardName, BuildConfig.APP_KEY, token);
    }

    public Observable<Card> editCardDesc(String cardId, String newCardDesc, String token) {
        return cardsApiInterface.editCardDesc(cardId, newCardDesc, BuildConfig.APP_KEY, token);
    }

}
