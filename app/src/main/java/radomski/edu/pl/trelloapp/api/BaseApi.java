package radomski.edu.pl.trelloapp.api;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import radomski.edu.pl.trelloapp.BuildConfig;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by adam on 7/6/15.
 */
@Singleton
public class BaseApi {
    @Inject
    public BaseApi() {
    }

    protected RestAdapter getRestAdapter() {
        Gson gson = new Gson();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BuildConfig.API_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        return restAdapter;
    }

}
