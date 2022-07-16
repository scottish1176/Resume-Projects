package com.techelevator.dao;

import com.techelevator.model.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcRoomDao implements RoomDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRoomDao (JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}



    @Override
    public boolean addRoomToFloor(Room room, int floorId) {
        String sql ="INSERT INTO room_details (room_name, room_size, floor_id,is_kitchen, is_bathroom," +
                " number_of_windows, flooring_tier_id) VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, room.getRoomName(), room.getRoomSize(), floorId,room.isKitchen(),
                room.isBathroom(),room.getNumOfWindows(),room.getTierFlooring()) ==1 ;
    }

    @Override
    public boolean changeRoomSize(Room room, int roomId) {
        String sql = "UPDATE room_details SET room_size = ? WHERE room_id = ?";
        return jdbcTemplate.update(sql,room.getRoomSize(), roomId) == 1;
    }

    @Override
    public boolean deleteRoom(int roomId) {
        String sql = "DELETE FROM room_details WHERE room_id = ?";
        return jdbcTemplate.update(sql,roomId) == 1;
    }

    @Override
    public boolean changeRoomName(Room room, int roomId) {
        String sql = "UPDATE room_details SET room_name = ? WHERE room_id = ?";
        return jdbcTemplate.update(sql,room.getRoomName(), roomId) == 1;
    }

    @Override
    public boolean changeRoomFloor(Room room, int floorId) {
        String sql = "UPDATE room_details SET floor_id = ? WHERE room_id =?";
        return jdbcTemplate.update(sql,floorId, room.getRoomId()) == 1;
    }

    @Override
    public boolean updateRoomTier(Room room, int roomId ) {
       String sql = "UPDATE room_details SET flooring_tier_id = ? WHERE room_id = ? ";
        return jdbcTemplate.update(sql,room.getTierFlooring(),roomId) == 1;
    }

    @Override
    public List<Room> getAllRoomsByFloorId(int floorId) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room_details WHERE floor_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,floorId);

        while(results.next()) {
            Room room = mapRowToRoomDetails(results);
            rooms.add(room);
        }
        return rooms;
    }

    @Override
    public Room getRoomByRoomId(int roomId) {
        Room room = new Room();
        String sql = "SELECT * FROM room_details WHERE room_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,roomId);
        if(results.next()){
            room = mapRowToRoomDetails(results);
        }
        return room;
    }

    @Override
    public boolean updateRoomElements(Room room, int roomId) {
        String sql = "UPDATE room_details SET room_name = ?, room_size = ?, is_kitchen = ?, is_bathroom = ?, number_of_windows = ? WHERE room_id = ?";
        return jdbcTemplate.update(sql, room.getRoomName(), room.getRoomSize(), room.isKitchen(), room.isBathroom(), room.getNumOfWindows(), roomId) == 1;
    }

    private Room mapRowToRoomDetails (SqlRowSet rs) {
        Room room = new Room();
        room.setRoomId(rs.getInt("room_id"));
        room.setRoomName(rs.getString("room_name"));
        room.setRoomSize(rs.getInt("room_size"));
        room.setFloorId(rs.getInt("floor_id"));
        room.setKitchen(rs.getBoolean("is_kitchen"));
        room.setBathroom(rs.getBoolean("is_bathroom"));
        room.setNumOfWindows(rs.getInt("number_of_windows"));
        room.setTierFlooring(rs.getInt("flooring_tier_id"));
        return room;
    }




}
