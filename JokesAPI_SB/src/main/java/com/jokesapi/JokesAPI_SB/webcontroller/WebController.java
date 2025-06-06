package com.jokesapi.JokesAPI_SB.webcontroller;

import com.jokesapi.JokesAPI_SB.Storage.JokeStorage;
import org.springframework.ui.Model;
import com.jokesapi.JokesAPI_SB.API.Joke;
import com.jokesapi.JokesAPI_SB.API.JokesAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@Controller
public class WebController { //thats the same thing that JavaFX had (controller), but for springboots

    @GetMapping("/")
    public String index(@RequestParam(value = "getJoke", required = false) String getJoke, Model model) throws IOException, InterruptedException { //Model model(Var) is used to pass the info from BE to .html
        if (getJoke != null) { //first time I load the page, there is no joke yet, so the getJoke parameter is null, else doesnt work
            Joke joke = JokesAPI.requestAPI();
            model.addAttribute("joke", joke); //this model is used for visual part of .html
        }
        return "index"; //this is mandatory, because of how spring boot and thymeleaf templates work. Pretty much this part, springboot tells which .html tempalte to return, since i have index.html, it instantly understands to return index.html
    }

    @PostMapping("/saved-joke") //i want it to "proceed" with action when i click saved icon, so it saves the joke and opens the list
    public String saveJoke(@RequestParam String joke,
                           @RequestParam String punchline,
                           @RequestParam String category,
                           Model model) {
        Joke newJoke = new Joke(joke, punchline, category);
        JokeStorage.savedJokes.add(newJoke);
        model.addAttribute("joke", newJoke);
        return "redirect:/?getJoke=returnedJoke"; //then opens redirect page to saved-jokes
    }

    @PostMapping("/delete-joke/{id}")
    public String deleteJoke(@PathVariable("id") UUID id) {
        JokeStorage.savedJokes.removeIf(joke -> joke.getId().equals(id));
        return "redirect:/saved-jokes";
    }

    @GetMapping("/saved-jokes") //retrieves jokes from the storage within saved-jokes.html page
    public String showSavedJokes(Model model) {
        model.addAttribute("jokes", JokeStorage.savedJokes);
        return "saved-jokes";
    }
}
