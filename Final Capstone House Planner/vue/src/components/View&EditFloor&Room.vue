<template>
  <div>
    <div class="top-links">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <br />
    <br />
    <h1>You're currently looking at: {{ this.currentHouseName }}</h1>
    <div class="editFloor">
      <table class="floor-details-user-homes">
        <tr v-for="floor in floors" v-bind:key="floor.id">
          <div class="floor-level">
            Floor Level:
            {{ floor.floorLevel }} <br />
            <button
              class="button-name"
              v-on:click="addRoomToThisFloor(floor.floorId)"
            >
              Add a Room to This Floor
            </button>
          </div>
          <table>
            <tr v-for="room in floor.rooms" v-bind:key="room.id">
              <div>
                Room: {{ room.roomName }} <br />
                Room Size: {{ room.roomSize }} Sq.ft <br />
                number of Windows: {{ room.numOfWindows }} <br />
                <br />
                <button
                  class="button-name"
                  v-on:click="
                    $router.push({
                      name: 'editExistingRoom',
                      params: { id: room.roomId },
                    })
                  "
                >
                  Edit this Room
                </button>
                <br />
              </div>
            </tr>
          </table>
          <br />
        </tr>
      </table>
    </div>
    <div class="editFloor2">
      <button class="button" v-on:click="$router.push({ name: 'userHomes' })">
        Go back to the Homes List
      </button>
      <br />
      <button class="button" v-on:click="addFloorToHouse()">
        Add a Floor to this House
      </button>
      <br />
      <button class="button" v-on:click="removeFloorFromHouse()">
        Remove the Top Floor from this House
      </button>
      <br />
      <button class="button" v-on:click="editHouseDetails()">
        Edit The Details of This House
      </button>
    </div>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "view-and-edit-floors-and-rooms",
  data() {
    return {
      house_id: this.$route.params.id,
      floors: [],
      numOfFloors: 0,
      currentHouseName: "",
    };
  },
  created() {
    this.getHouseDetails();
    this.getFloors(this.house_id);
    this.$store.commit("SET_ACTIVE_HOUSE", this.$route.params.id);
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
    addRoomToThisFloor(floorId) {
      this.$store.commit("SET_ACTIVE_HOUSE", this.$route.params.id);
      this.$store.commit("SET_ACTIVE_FLOOR", floorId);
      this.$router.push({
        name: "addRoomToExistingFloor",
        params: { id: floorId, houseId: this.house_id },
      });
    },
    addFloorToHouse() {
      HomeService.addFloorToHouse(this.house_id).then((response) => {
        if (response.status === 200) {
          window.location.reload();
        }
      });
    },
    removeFloorFromHouse() {
      if (this.floors.length > 1) {
        if (window.confirm("Are you sure you want to delete this floor?")) {
          HomeService.removeFloorFromHouse(
            this.floors[this.floors.length - 1].floorId,
            this.house_id
          ).then((response) => {
            if (response.status === 200) {
              window.location.reload();
            }
          });
        }
      } else {
        window.alert("You must have at least one floor in your house");
      }
    },
    editHouseDetails() {
      this.$router.push({
        name: "editExistingHouseDetails",
        params: { id: this.house_id },
      });
    },
  },
};
</script>

<style>
.top-links {
  text-align: left;
  margin-left: 20px;
  margin-top: 20px;
  font-family: "Montserrat";
  font-weight: bold;
  font-size: 20px;
  color: black;
}
.floor-details-user-homes {
  align-items: center;
  background-color: rgba(54, 148, 66, 0.397);
  margin-left: 50px;
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
.editFloor {
  display: flex;
  justify-content: center;
}
.editFloor2 {
  display: flex;
  margin-top: 20px;
  justify-content: center;
  margin-left: 20px;
}
</style>