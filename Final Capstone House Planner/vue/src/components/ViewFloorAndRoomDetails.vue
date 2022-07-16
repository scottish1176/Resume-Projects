<template>
  <div>
      <div class="top-thingys">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      >Logout</router-link
    ><br />
    <br />
    <h1>You're currently looking at: {{ this.currentHouseName }} </h1> 
      </div>
      <div id="floorStyles">
    <table class="roomDetails">
      <tr v-for="floor in floors" v-bind:key="floor.id">
          <div class="floor-level">
        Floor Level:
        {{
          floor.floorLevel
        }}
          </div>
        <table>
            <tr v-for="room in floor.rooms" v-bind:key="room.id">
                <div id="spacingStyling">
                    Room: {{ room.roomName}} <br>
                    Room Size: {{ room.roomSize}} <br>
                    Number of Windows: {{room.numOfWindows}} <br>
                    <br>
                </div>

            </tr>

        </table>
        <br />
      </tr>
          <button id="sideButton" class="button-name" v-on:click="$router.push({name: 'publicHomes'})">Return to public homes list</button>

    </table>
    </div>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "view-floor-and-room-details",
  data() {
    return {
      house_id: this.$store.state.currentHouseId,
      floors: [],
      numOfFloors: 0,
      currentHouseName: "",
    };
  },
  created() {
    this.getHouseDetails();
    this.getFloors(this.house_id);
  },
  methods: {
    getFloors(houseId) {
      HomeService.getFloorDetails(houseId).then((response) => {
        for (let i = 0; i < response.data.length; i++) {
          const eachFloor = response.data[i];
          const newFloor = {
            floorLevel: eachFloor.floorLevel,
            floorId: eachFloor.floorId,
            rooms: [],
          };
          this.floors.push(newFloor);
          this.getRooms(newFloor);
        }
      });
    },
    getRooms(floor) {
      HomeService.getRoomsByFloorId(floor.floorId).then((roomResponse) => {
        for (let i = 0; i < roomResponse.data.length; i++) {
          const eachRoom = roomResponse.data[i];
          const newRooms = {
            roomId: eachRoom.room_id,
            roomName: eachRoom.room_name,
            roomSize: eachRoom.room_size,
            floorID: eachRoom.floor_id,
            isKitchen: eachRoom.is_kitchen,
            isBathroom: eachRoom.is_bathroom,
            numOfWindows: eachRoom.number_of_windows,
            flooringTierId: eachRoom.flooring_tier_id,
          };
          floor.rooms.push(newRooms);
        }
      });
    },
    getHouseDetails() {
      HomeService.getHouseDetails(this.house_id).then((response) => {
        this.currentHouseName = response.data.house_name;
      });
    },
  },
};
</script>

<style>
.roomDetails{
align-items: center;
  background-color: rgba(54, 148, 66, 0.397);
  border-radius: 107px;
  padding-bottom: 60px;
  backdrop-filter: blur(10px);
  color: rgb(0, 0, 0);
  position: relative;
  margin-top: 25px;
  padding: 60px;
  font-size: 20px;
  font-weight: bold;
}
.top-thingys{
text-align: left;
  margin-left: 20px;
  margin-top: 20px;
  font-family: 'Montserrat';
  font-weight: bold;
  font-size: 20px;
  color: black;
}
#floorStyles{
  display: inline-block;
  justify-items: center;
  
}
#spacingStyling{
  margin-bottom: 25px;
}
#sideButton{
  display: block;
  justify-items: center;
}
</style>