package com.jokesapi.JokesAPI_SB.webcontroller;

import com.jokesapi.JokesAPI_SB.Storage.JokeStorage;
import org.springframework.ui.Model;
import com.jokesapi.JokesAPI_SB.API.Joke;
import com.jokesapi.JokesAPI_SB.API.JokesAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class WebController { //thats the same thing that JavaFX had (controller), but for springboots

    @GetMapping("/")
    public String index(@RequestParam(value = "getJoke", required = false) String getJoke, Model model) throws IOException, InterruptedException { //Model model(Var) is used to pass the info from BE to .html
        if (getJoke != null) { //first time I load the page, there is no joke yet, so the getJoke parameter is null, else doesnt work
            Joke joke = JokesAPI.requestAPI();
            model.addAttribute("joke", joke); //this model is used for visual part of .html
            model.addAttribute("jokeJSON", joke.toJSON().toString(4)); //this model is used for backend analysis part of .html
        }
        return "index"; //this is mandatory, because of how spring boot and thymeleaf templates work. Pretty much this part, springboot tells which .html tempalte to return, since i have index.html, it instantly understands to return index.html
    }
/*
    @PostMapping("/saved-joke") //i want it to "proceed" with action when i click saved icon, so it saves the joke and opens the list
    public String saveJoke(@ModelAttribute Joke joke) {
        JokeStorage.savedJokes.add(joke);
        return "redirect:/saved-jokes"; //then opens redirect page to saved-jokes
    }

 */

    @PostMapping("/saved-joke") //i want it to "proceed" with action when i click saved icon, so it saves the joke and opens the list
    public String saveJoke(@RequestParam String joke,
                           @RequestParam String punchline,
                           @RequestParam String category) {
        Joke newJoke = new Joke(joke, punchline, category);
        JokeStorage.savedJokes.add(newJoke);
        return "redirect:/saved-jokes"; //then opens redirect page to saved-jokes
    }

    @GetMapping("/saved-jokes") //retrieves jokes from the storage within saved-jokes.html page
    public String showSavedJokes(Model model) {
        model.addAttribute("jokes", JokeStorage.savedJokes);
        return "saved-jokes";
    }
}
