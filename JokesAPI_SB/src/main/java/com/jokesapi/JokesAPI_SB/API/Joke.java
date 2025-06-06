package com.jokesapi.JokesAPI_SB.API;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Joke {
    private UUID id;
    private String joke;
    private String punchline;
    private String category;
    private Date date_created;

    public Joke(String joke, String punchline, String category) {
        setId(UUID.randomUUID());
        setJoke(joke);
        setPunchline(punchline);
        setCategory(category);
        setDate_created(new Date());
    }

    //setters
    public void setId(UUID uuid) {
        this.id = uuid;
    }
    public void setJoke(String joke) {
        this.joke = joke;
    }
    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDate_created(Date date) {
        this.date_created = date;
    }

    //getters
    public UUID getId() {
        return id;
    }
    public String getJoke() {
        return joke;
    }
    public String getPunchline() {
        return punchline;
    }
    public String getCategory() {
        return category;
    }
    public String getDate_created() { //the date it self is stored in Date format, but for UI i return it in human readable, formatted String
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(date_created);
    }
}




