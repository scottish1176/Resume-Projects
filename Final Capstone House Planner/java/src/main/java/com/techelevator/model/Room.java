package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {

    @JsonProperty ("room_id")
    private int roomId;
    @JsonProperty ("room_name")
    private String roomName;
    @JsonProperty ("room_size")
    private int roomSize;
    @JsonProperty ("floor_id")
    private int floorId;
    @JsonProperty("is_kitchen")
    private boolean isKitchen;
    @JsonProperty("is_bathroom")
    private boolean isBathroom;
    @JsonProperty("number_of_windows")
    private int numOfWindows;
    @JsonProperty("flooring_tier_id")
    private int tierFlooring;

    public Room(){}


    public boolean isKitchen() {
        return isKitchen;
    }

    public void setKitchen(boolean kitchen) {
        isKitchen = kitchen;
    }

    public boolean isBathroom() {
        return isBathroom;
    }

    public void setBathroom(boolean bathroom) {
        isBathroom = bathroom;
    }

    public int getNumOfWindows() {
        return numOfWindows;
    }

    public void setNumOfWindows(int numOfWindows) {
        this.numOfWindows = numOfWindows;
    }

    public int getTierFlooring() {
        return tierFlooring;
    }

    public void setTierFlooring(int tierFlooring) {
        this.tierFlooring = tierFlooring;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}
