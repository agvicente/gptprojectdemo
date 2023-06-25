package com.agvicente.gptprojectdemo.entities;

public class SciFi extends Work {

    private String[] scenario;

    public String[] getScenario() {
        return scenario;
    }

    public void setScenario(String[] scenario) {
        this.scenario = scenario;
    }

    @Override
    public String getOutputChat() {
        return getScenario();
    }

    @Override
    public String getInputChat() {
        return new String[0];
    }
}
