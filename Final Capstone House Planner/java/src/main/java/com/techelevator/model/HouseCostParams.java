package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseCostParams {
    @JsonProperty("foundation_size")
    private Integer foundationSize;
    private String city;
    @JsonProperty("state_abbreviation")
    private String stateAbbreviation;
    @JsonProperty("number_of_floors")
    private int numberOfFloors;
    private int bathrooms;
    private int bedrooms;


    public HouseCostParams(){}

    public Integer getFoundationSize() {
        return foundationSize;
    }

    public void setFoundationSize(Integer foundationSize) {
        this.foundationSize = foundationSize;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }


}
