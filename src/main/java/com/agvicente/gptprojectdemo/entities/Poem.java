package com.agvicente.gptprojectdemo.entities;

public class Poem extends Work {

    private String[] verses;

    public String[] getVerses() {
        return verses;
    }

    public void setVerses(String[] verses) {
        this.verses = verses;
    }

    @Override
    public String getOutputChat() {
        return getVerses().toString();
    }

    @Override
    public String getInputChat() {
        return "Generate a movie synopsis based on de given data: "
                + "Initial Verse: " + getVerses()[0];
    }
}
