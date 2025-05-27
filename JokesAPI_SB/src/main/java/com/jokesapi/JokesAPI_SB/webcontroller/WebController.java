package com.jokesapi.JokesAPI_SB.webcontroller;

import org.springframework.ui.Model;
import com.jokesapi.JokesAPI_SB.API.Joke;
import com.jokesapi.JokesAPI_SB.API.JokesAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        Joke joke = null;
        try {
            joke = JokesAPI.requestAPI();
        } catch (Exception e) {
            //todo need to add error logging somehow here
        }
        model.addAttribute("joke", joke);
        return "index";
    }
}