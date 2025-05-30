package com.jokesapi.JokesAPI_SB.webcontroller;

import org.springframework.ui.Model;
import com.jokesapi.JokesAPI_SB.API.Joke;
import com.jokesapi.JokesAPI_SB.API.JokesAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@Controller
public class WebController {

    @GetMapping("/")
    public String index(@RequestParam(value = "getJoke", required = false) String getJoke, Model model) throws IOException, InterruptedException {
        if (getJoke != null) {
            Joke joke = JokesAPI.requestAPI();
            model.addAttribute("joke", joke);
        }
        return "index";
    }
}
