package com.jokesapi.JokesAPI_SB.API;

import java.text.SimpleDateFormat;
import java.util.Date; //javas timestamps
import java.util.UUID;

public class Joke {
    private UUID id;
    private String joke;
    private String punchline;
    private String category;
    private String rating;
    private Date date_created;
    private Date date_updated;
    private Date date_deleted;

    public Joke(String joke, String punchline, String category) {
        setId(UUID.randomUUID());
        setJoke(joke);
        setPunchline(punchline);
        setCategory(category);
        setDate_created(new Date());
        setDate_updated(new Date());
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
    public void setRating(String rating) {
        this.rating = rating;
    }
    public void setDate_created(Date date) {
        this.date_created = date;
    }
    public void setDate_updated(Date date) {
        this.date_updated = date;
    }
    public void setDate_deleted(Date date) {
        this.date_deleted = date;
    }

    //getters
    //its weird, javaFX needs them to work, but there is no usages for some of them...
    //internet told me that PropertyValueFactory in controller does that behind the scenes.
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
    public String getRating() {
        return rating;
    }
    public String getDate_created() { //the date it self is stored in Date format, but for UI i return it in human readable, formatted String
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(date_created);
    }
    public String getDate_updated() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(date_updated);
    }
    public String getDate_deleted() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(date_deleted);
    }
}


