package com.example.course_fx;

public class Run {
    private int runId;
    private String startCity;
    private String endCity;
    private String distance;
    private int completing;

    public Run(int runId, String startCity, String endCity, String distance, int completing){
        this.runId = runId;
        this.startCity = startCity;
        this.endCity = endCity;
        this.distance = distance;
        this.completing = completing;
    }
    public Run(String startCity, String endCity, String distance, int completing){
        this.startCity = startCity;
        this.endCity = endCity;
        this.distance = distance;
        this.completing = completing;
    }

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getCompleting() {
        return completing;
    }

    public void setCompleting(int completing) {
        this.completing = completing;
    }
}