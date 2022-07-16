package com.techelevator.controller;

import com.techelevator.dao.HouseDetailsDao;
import com.techelevator.model.Floor;
import com.techelevator.model.HouseCostParams;
import com.techelevator.model.HouseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class HouseDetailsController {
    @Autowired
    private HouseDetailsDao houseDetailsDao;

    @PostMapping(value = "/create")
    public Long createHouse (@RequestBody HouseDetails houseDetails){
        return houseDetailsDao.createHouse(houseDetails);
    }
    @PreAuthorize("permitAll")
    @GetMapping(value = "/get-house-details/{id}")//house ID
    public HouseDetails getHouseDetails (@PathVariable Long id){
        return houseDetailsDao.getHouseDetails(id);
    }


    @GetMapping(value = "/get-all-houses/{id}")//user ID
    public List<HouseDetails> getAllHouses (@PathVariable Long id){
        return houseDetailsDao.getAllHousesByUserId(id);
    }

    @PutMapping(value = "/update-floors/{houseId}")
    public boolean addFloorsToExistingFloors(@PathVariable Long houseId){
        return houseDetailsDao.addFloorToExistingFloors(houseId);
    }

    @PutMapping(value = "/update-subtract-floors/{id}/{houseId}") //floor ID/houseID
    public boolean removeFloors(@PathVariable int id, @PathVariable Long houseId){
        return houseDetailsDao.removeFloorsFromHouseTable(houseId, id );
    }

    @DeleteMapping(value = "/delete-house/{id}")
    public boolean deleteHouse(@PathVariable Long id){
        return  houseDetailsDao.deleteHouse(id);
    }

    @PreAuthorize("permitAll")
    @GetMapping(value = "/guest")
    public List<HouseDetails> getAllHousesForGuestUser(){
        return houseDetailsDao.getAllHousesForGuestUser();
    }

    @PreAuthorize("permitAll")
    @GetMapping(value = "/get-all-floors/{id}") //houseId
    public List<Floor> getAllFloorsByHouseId (@PathVariable Long id){
        return houseDetailsDao.getAllFloorsByHouseId(id);
    }

    @PreAuthorize("permitAll")
    @GetMapping(value = "/get-house-cost/{id}")
    public HouseCostParams getHouseCost(@PathVariable Long id){
        return houseDetailsDao.getParamsForHouseCost(id);
    }

    @GetMapping(value = "/get-floor-level/{floorId}")
    public Floor getFloorLevelWithFloorId(@PathVariable int floorId) {
        return houseDetailsDao.getFloorWithFloorId(floorId);
    }

    @PutMapping (value = "/update-existing-house-details/{houseId}")
    public boolean updateHouse(@PathVariable Long houseId, @RequestBody HouseDetails houseDetails) {
        return houseDetailsDao.updateHouseDetails(houseId, houseDetails);
    }
}
