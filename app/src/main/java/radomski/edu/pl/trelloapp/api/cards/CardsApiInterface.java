package radomski.edu.pl.trelloapp.api.cards;

import radomski.edu.pl.trelloapp.tools.retrofit.RetrofitBodyDelete;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by adam on 7/7/15.
 */
public interface CardsApiInterface {
    @FormUrlEncoded
    @PUT("/cards/{cardID}/idList")
    Observable<Card> moveCard(@Path("cardID") String cardID,
                              @Field("value") String value,
                              @Field("key") String key,
                              @Field("token") String token);


    //Strange response
    //    {
    //        "_value": null
    //    }
    @FormUrlEncoded
    @RetrofitBodyDelete("/cards/{cardID}/")
    Observable<CardDeleteResponse> deleteCard(@Path("cardID") String cardID,
                                  @Field("key") String key,
                                  @Field("token") String token);

    @FormUrlEncoded
    @POST("/cards")
    Observable<Card> createCard(@Field("idList") String idList,
                                @Field("name") String name,
                                @Field("desc") String desc,
                                @Field("key") String key,
                                @Field("token") String token);

    @FormUrlEncoded
    @PUT("/cards/{cardID}/name")
    Observable<Card> editCardName(@Path("cardID") String cardID,
                                  @Field("value") String value,
                                  @Field("key") String key,
                                  @Field("token") String token);

    @FormUrlEncoded
    @PUT("/cards/{cardID}/desc")
    Observable<Card> editCardDesc(@Path("cardID") String cardID,
                                  @Field("value") String value,
                                  @Field("key") String key,
                                  @Field("token") String token);
}
