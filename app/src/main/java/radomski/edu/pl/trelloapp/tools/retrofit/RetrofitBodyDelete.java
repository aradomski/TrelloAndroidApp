package radomski.edu.pl.trelloapp.tools.retrofit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import retrofit.http.RestMethod;

/**
 * Created by adam on 7/8/15.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RestMethod(value = "DELETE", hasBody = true)
public @interface RetrofitBodyDelete {
    String value();
}
