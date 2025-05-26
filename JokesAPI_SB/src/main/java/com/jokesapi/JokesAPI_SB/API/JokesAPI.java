package com.jokesapi.JokesAPI_SB.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.jokesapi.JokesAPI_SB.application.Joke;
import org.json.JSONObject;

public class JokesAPI {
    public static Joke requestAPI() throws IOException, InterruptedException {

        //client section
        HttpClient client = HttpClient.newHttpClient();

        //request section
        HttpRequest request = HttpRequest.newBuilder() //this handles request
                .uri(URI.create("https://official-joke-api.appspot.com/random_joke")) //endpoint
                .timeout(Duration.ofSeconds(5)) //timeout for request
                .header("Content-type", "application/json") //header parameters
                .GET() //method
                .build(); //mandatory to execute request

        //response section
        HttpResponse<String> response = client //this handles response
                .send(request, HttpResponse.BodyHandlers.ofString());

        //using JSONObject library for data organization (key & value) because API returns JSON object, so i need to get the correct data from it and then store in my Joke class
        //I also have to decode the namings, for example setup = joke, so i need to store it as a joke and not as a setup which might be confusing
        //I store it later as a String (everywhere), and add to the list also as a String to avoid complications
        JSONObject jokeResponse = new JSONObject(response.body());
        String joke = jokeResponse.getString("setup");
        String punchline = jokeResponse.get("punchline").toString();
        String category = jokeResponse.get("type").toString();
        return new Joke(joke, punchline, category); // + "\n" + punchline;
    }
}


