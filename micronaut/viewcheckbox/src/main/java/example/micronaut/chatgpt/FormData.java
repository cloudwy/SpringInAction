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
    boolean subscribeCheckBox;
    @JsonProperty
    String attributeInput;

    public FormData() {
    }

    public FormData(
            boolean subscribeCheckBox,
            String attributeInput) {
        this.subscribeCheckBox = subscribeCheckBox;
        this.attributeInput = attributeInput;
    }

    // Getter and setter for 'subscribe' property
    public boolean isSubscribeCheckBox() {
        return subscribeCheckBox;
    }

    public void setSubscribeCheckBox(boolean subscribeCheckBox) {
        this.subscribeCheckBox = subscribeCheckBox;
    }

    // Getter and setter for 'attribute' property
    public String getAttributeInput() {
        return attributeInput;
    }

    public void setAttributeInput(String attributeInput) {
        this.attributeInput = attributeInput;
    }
}