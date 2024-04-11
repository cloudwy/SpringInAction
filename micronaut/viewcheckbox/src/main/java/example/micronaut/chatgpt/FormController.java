package example.micronaut.chatgpt;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {

    private final ObjectMapper objectMapper;

    public FormController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Get("/")
    @View("forms/index.html")
    public ModelAndView index() {
        Map<String, Object> data = new HashMap<>();
        data.put("formData", new FormData());
        return new ModelAndView("index", data);
    }

    @Post("/submitForm")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(MediaType.TEXT_HTML)
    public HttpResponse<String> submitForm(
            @Body FormData formData
    ){
//      final FormData formData = objectMapper.readValue(data, FormData.class);
//        boolean subscribe = formData.isSubscribeCheckBox();
        String attribute = formData.getAttributeInput();
//        if (subscribe) {
//            return HttpResponse.ok("User subscribed to newsletter with attribute: " + attribute);
//        } else {
//            return HttpResponse.ok("User did not subscribe to newsletter with attribute: " + attribute);
//        }
        return HttpResponse.ok("hello");
    }
}
