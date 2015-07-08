package radomski.edu.pl.trelloapp.api.cards;

import com.google.gson.annotations.SerializedName;

/**
 * Created by adam on 7/8/15.
 */
public class CardDeleteResponse {
    @SerializedName("_value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
