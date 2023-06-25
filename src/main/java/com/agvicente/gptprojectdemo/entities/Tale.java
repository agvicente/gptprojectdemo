package com.agvicente.gptprojectdemo.entities;

public class Tale extends Work {

    private String[] tale;

    public String[] getTale() {
        return tale;
    }

    public void setTale(String[] tale) {
        this.tale = tale;
    }

    @Override
    public String getOutputChat() {
        return getTale();
    }

    @Override
    public String getInputChat() {
        return new String[]{this.getTitle()};
    }
}
