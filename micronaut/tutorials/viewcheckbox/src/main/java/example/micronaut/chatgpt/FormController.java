/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
