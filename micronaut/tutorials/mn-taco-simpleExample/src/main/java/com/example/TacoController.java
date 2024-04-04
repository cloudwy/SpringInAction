package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.View;
import com.example.domain.Taco;
import java.util.ArrayList;
import java.util.List;

@Controller("/tacos")
public class TacoController {
    private List<Taco> tacoList = new ArrayList<>();

    public TacoController(List<Taco> tacoList) {
        this.tacoList = tacoList;
        tacoList.add(new Taco("1", "coconus, champion, tomatosauce"));
        tacoList.add(new Taco("2", "beef, brocolli, carrot"));
    }

    @Get("/")
    @View("tacos")
    public List<Taco> listTacos() {
        return tacoList;
    }

    @Post("/create")
    public String createTaco(Taco taco) {
        tacoList.add(taco);
        return "redirect:/tacos/";
    }
}
