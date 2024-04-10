package example.micronaut.chatgpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@ReflectiveAccess
@Serdeable
public class FormData {
    @JsonProperty
    boolean subscribe;
    @JsonProperty
    String attribute;

    public FormData() {
    }

    public FormData(boolean subscribe, String attribute) {
        this.subscribe = subscribe;
        this.attribute = attribute;
    }

    // Getter and setter for 'subscribe' property
    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    // Getter and setter for 'attribute' property
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}

//@ReflectiveAccess
//@Serdeable
//public record FormData(
//        boolean subscribe,
//        String attribute
//){
//
//    public FormData {
//    }
//}