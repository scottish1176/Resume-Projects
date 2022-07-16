package com.techelevator.dao;

import com.techelevator.model.Room;

import java.util.List;

public interface RoomDao {

    boolean addRoomToFloor(Room room, int floorId);

    boolean changeRoomSize(Room room, int roomId);

    boolean deleteRoom(int roomId);

    boolean changeRoomName(Room room, int roomId);

    boolean changeRoomFloor (Room room, int floorId);

    boolean updateRoomTier (Room room, int roomId);

    public List<Room> getAllRoomsByFloorId(int floorId);

    Room getRoomByRoomId(int roomId);

    boolean updateRoomElements (Room room, int roomId);

}
