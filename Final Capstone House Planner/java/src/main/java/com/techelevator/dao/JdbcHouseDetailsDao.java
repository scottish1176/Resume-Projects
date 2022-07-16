package com.techelevator.dao;

import com.techelevator.model.Floor;
import com.techelevator.model.HouseCostParams;
import com.techelevator.model.HouseDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcHouseDetailsDao implements HouseDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcHouseDetailsDao (JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Long createHouse(HouseDetails houseDetails) {
        String sql = "INSERT INTO house_details (house_name, foundation_size, city, state_abbreviation, user_id, is_private,number_of_floors) " +
                        "VALUES (?,?,?,?,?,?,?) RETURNING house_id";
        Long house_id = jdbcTemplate.queryForObject(sql,Long.class, houseDetails.getHouseName(),houseDetails.getFoundationSize(),
                houseDetails.getCity(), houseDetails.getStateAbbreviation(), houseDetails.getUserId(), houseDetails.isPrivate(), houseDetails.getNumberOfFloors());
        addFloorsWhenHouseisCreated(houseDetails,house_id);
        return house_id;
  }



    @Override
    public HouseDetails getHouseDetails(Long houseId) {
        HouseDetails houseDetails = new HouseDetails();
        String sql = "SELECT * FROM house_details WHERE house_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, houseId);
        while(results.next()) {
            houseDetails = mapRowToHouseDetails(results);
        }
        return houseDetails;
    }

    @Override
    public List<HouseDetails> getAllHousesByUserId(Long userId) {
        List<HouseDetails> houses = new ArrayList<>();
        String sql = "SELECT * FROM house_details WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            HouseDetails houseDetails = mapRowToHouseDetails(results);
            houses.add(houseDetails);
        }
        return houses;
    }


    @Override
    public boolean addFloorsWhenHouseisCreated(HouseDetails houseDetails, Long houseId) {
        String sql = "INSERT INTO floor (house_id, floor_level) VALUES (?, ?)";

        for(int i=1;i<=houseDetails.getNumberOfFloors();i++){
            if(i== houseDetails.getNumberOfFloors()){
                return jdbcTemplate.update(sql,houseId,i) ==1;
            }
            jdbcTemplate.update(sql,houseId,i);
        }

        return false;
    }

    @Override
    public boolean removeFloorsFromHouseTable(Long houseId, int floorId) {
        String sql = "UPDATE house_details SET number_of_floors = number_of_floors - 1 WHERE house_id =?";
        jdbcTemplate.update(sql, houseId);
        return removeFloorsFromFloorTable(floorId);
    }

    @Override
    public boolean removeFloorsFromFloorTable(int floorId) {
        String sql = "DELETE FROM room_details WHERE floor_id =?";
        jdbcTemplate.update(sql,floorId);
        String sql2 ="DELETE FROM floor WHERE floor_id = ?";
        return jdbcTemplate.update(sql2, floorId)==1;
    }

    @Override
    public boolean addFloorToExistingFloors(Long houseId) {
        String sql = "UPDATE house_details SET number_of_floors = number_of_floors + 1 WHERE house_id =?";
        jdbcTemplate.update(sql,houseId);
        return addFloorToFloorTable(houseId);
    }

    @Override
    public boolean addFloorToFloorTable(Long houseId) {
        String sql = "INSERT INTO floor (floor_level, house_id) VALUES ((SELECT COUNT(floor_level) + 1 FROM floor WHERE house_id = ?),?)";
        return jdbcTemplate.update(sql,houseId,houseId) == 1;
    }

    @Override
    public List<Floor> getAllFloorsByHouseId(Long houseId) {
        List<Floor> floors = new ArrayList<>();
        String sql ="SELECT * FROM floor WHERE house_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,houseId);

        while(results.next()) {
            floors.add(mapRowToFloor(results));
        }
        return floors;
    }

    @Override
    public HouseCostParams getParamsForHouseCost(Long houseId) {
        HouseCostParams houseCostParams = new HouseCostParams();
        String sql = "SELECT house_details.foundation_size, house_details.city,house_details.state_abbreviation, MAX(floor.floor_level) AS stories,\n" +
                "\t(SELECT COUNT(room_details.room_id) as bedrooms\n" +
                "\tFROM room_details\n" +
                "\tJOIN floor USING (floor_id)\n" +
                "\tJOIN house_details USING (house_id)\n" +
                "\tWHERE room_details.is_bathroom IS false AND house_details.house_id = ? AND room_details.is_kitchen IS false) AS bedrooms, \n" +
                "\t\t(SELECT COUNT(room_details.is_bathroom)\n" +
                "\t\tFROM room_details\n" +
                "\t\tJOIN floor USING (floor_id)\n" +
                "\t\tJOIN house_details USING (house_id)\n" +
                "\t\tWHERE room_details.is_bathroom IS true AND house_details.house_id = ?) as bathrooms\n" +
                "FROM house_details\n" +
                "JOIN floor USING (house_id)\n" +
                "JOIN room_details USING (floor_id)\n" +
                "WHERE house_id = ? \n" +
                "GROUP BY house_details.city, house_details.state_abbreviation, house_details.foundation_size";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, houseId,houseId,houseId);
        while(results.next()) {
            houseCostParams = mapRowToHouseCostParams(results);
        }
        return houseCostParams;

    }

    @Override
    public Floor getFloorWithFloorId(int floorId) {
        Floor floor = new Floor();
        String sql = "SELECT * FROM floor WHERE floor_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,floorId);
        if(results.next()) {
            floor = mapRowToFloor(results);
        }
        return floor;
    }

    @Override
    public boolean updateHouseDetails(Long houseId, HouseDetails houseDetails) {
        String sql = "UPDATE house_details SET foundation_size = ?, city = ?, state_abbreviation = ?, house_name = ?, number_of_floors = ?, is_private = ? WHERE house_id = ?";
        return jdbcTemplate.update(sql, houseDetails.getFoundationSize(), houseDetails.getCity(), houseDetails.getStateAbbreviation(),
                houseDetails.getHouseName(), houseDetails.getNumberOfFloors(), houseDetails.isPrivate(),houseId) == 1;
    }


    @Override
    public boolean deleteHouse(Long houseId) {
        String sql1 = "DELETE\n" +
                "FROM room_details\n" +
                "WHERE floor_id IN (SELECT floor_id FROM floor WHERE house_id = ?)\n";
        jdbcTemplate.update(sql1,houseId);
        String sql2 = "DELETE\n" +
                "FROM floor\n" +
                "WHERE house_id = ?";
        jdbcTemplate.update(sql2,houseId);
        String sql = "DELETE FROM house_details WHERE house_id = ?";
        return jdbcTemplate.update(sql,houseId) == 1;
    }

    @Override
    public List<HouseDetails> getAllHousesForGuestUser() {
        List<HouseDetails> houses = new ArrayList<>();
        String sql = "SELECT * FROM house_details WHERE is_private = false";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            HouseDetails houseDetails = mapRowToHouseDetails(results);
            houses.add(houseDetails);
        }
        return houses;
    }



    private HouseDetails mapRowToHouseDetails (SqlRowSet rs) {
        HouseDetails houseDetails = new HouseDetails();
        houseDetails.setFoundationSize(rs.getInt("foundation_size"));
        houseDetails.setHouseId(rs.getLong("house_id"));
        houseDetails.setHouseName(rs.getString("house_name"));
        houseDetails.setCity(rs.getString("city"));
        houseDetails.setStateAbbreviation(rs.getString("state_abbreviation"));
        houseDetails.setUserId(rs.getLong("user_id"));
        houseDetails.setNumberOfFloors(rs.getInt("number_of_floors"));
        return houseDetails;
    }

    private Floor mapRowToFloor(SqlRowSet results){
        Floor floor = new Floor();
        floor.setFloorId(results.getInt("floor_id"));
        floor.setFloorLevel(results.getInt("floor_level"));
        floor.setHouseId(results.getInt("house_id"));
        return floor;
    }

    private HouseCostParams mapRowToHouseCostParams (SqlRowSet rs) {
        HouseCostParams houseCostParams = new HouseCostParams();
        houseCostParams.setFoundationSize(rs.getInt("foundation_size"));
        houseCostParams.setCity(rs.getString("city"));
        houseCostParams.setStateAbbreviation(rs.getString("state_abbreviation"));
        houseCostParams.setNumberOfFloors(rs.getInt("stories"));
        houseCostParams.setBathrooms(rs.getInt("bathrooms"));
        houseCostParams.setBedrooms(rs.getInt("bedrooms"));
        return houseCostParams;
    }


}
