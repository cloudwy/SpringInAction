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
        System.out.println("subscribe: " + formData.getSubscribeCheckBox() + " " + "attrinbute: " + formData.getAttributeInput());
        String subscribe = formData.getSubscribeCheckBox();
        String attribute = formData.getAttributeInput();
        if (subscribe == null) {
            return HttpResponse.ok("User did not subscribe to newsletter with attribute: " + attribute);
        } else {
            return HttpResponse.ok("Use subscribed to newsletter with attribute: " + attribute);
        }
    }
}
