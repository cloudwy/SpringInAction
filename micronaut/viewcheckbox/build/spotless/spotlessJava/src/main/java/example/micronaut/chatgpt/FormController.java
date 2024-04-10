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
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {
    @Get("/")
    public ModelAndView index() {
        Map<String, Object> data = new HashMap<>();
        data.put("formData", new FormData());
        return new ModelAndView("index", data); // 返回 index.html 视图
    }

    @Post("/submitForm")
    public HttpResponse<String> submitForm(FormData formData) {
        boolean subscribe = formData.isSubscribe();
        String attribute = formData.getAttribute();
        // 根据复选框和属性值返回不同的响应
        if (subscribe) {
            return HttpResponse.ok("User subscribed to newsletter with attribute: " + attribute);
        } else {
            return HttpResponse.ok("User did not subscribe to newsletter with attribute: " + attribute);
        }
    }
}
