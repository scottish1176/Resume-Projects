package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseDetails {
    @JsonProperty("house_id")
    private Long houseId;
    @JsonProperty ("house_name")
    private String houseName;
    @JsonProperty("foundation_size")
    private Integer foundationSize;
    @JsonProperty ("user_id")
    private Long userId;
    private String city;
    @JsonProperty("state_abbreviation")
    private String stateAbbreviation;
    @JsonProperty("number_of_floors")
    private int numberOfFloors;
    @JsonProperty("is_private")
    private boolean isPrivate;

    public HouseDetails(){}



    @Override
    public String toString() {
        return "HouseDetails{" +
                "houseId=" + houseId +
                ", houseName='" + houseName + '\'' +
                ", foundationSize=" + foundationSize +
                ", userId=" + userId +
                ", numberOfFloors=" + numberOfFloors +
                '}';
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getFoundationSize() {
        return foundationSize;
    }

    public void setFoundationSize(Integer foundationSize) {
        this.foundationSize = foundationSize;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
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
}
