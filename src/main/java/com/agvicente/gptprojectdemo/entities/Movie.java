package com.agvicente.gptprojectdemo.entities;

public class Movie extends Work {

    private String actor;
    private String actress;
    private String director;
    private String gender;
    private String synopsis;

    public Movie() { }



    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String getOutputChat() {
        return getSynopsis();
    }

    @Override
    public String getInputChat() {
        return "Generate a movie synopsis based on de given data: "
                + "Actor: "+ getActor() + "Actress: " +getActress() + "Gender: "+ getGender();
    }
}
