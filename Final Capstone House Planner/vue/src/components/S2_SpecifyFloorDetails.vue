<template>
  <div>
    <div class="loginHome">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <br />
    <h1>These are the floors in: {{ this.currentHouseName }}</h1>
    <br />
    <div class="floorDetailsStyle">
      <table>
        <tr v-for="floor in floors" v-bind:key="floor.id">
          <div id="">
            Floor Level:
            {{ floor.floorLevel }}
          </div>
          <br />
          <table id="rooms">
            <tr v-for="room in floor.rooms" v-bind:key="room.id">
              <!-- <div class="rooms"> -->
              Room:
              {{
                room.roomName
              }}
              <br />
              Room Size:
              {{
                room.roomSize
              }}
              <br />
              Number of Windows:
              {{
                room.numOfWindows
              }}
              <br />
              <button
                class="button"
                v-on:click="
                  $router.push({
                    name: 'editRoomView',
                    params: { id: room.roomId },
                  })
                "
              >
                Edit this room
              </button>
              <!-- </div> -->
            </tr>
          </table>
          <button class="button" v-on:click="setCurrentFloor(floor.floorId)">
            Add a room to this Floor</button
          ><br />
          <br />
        </tr>
        <button class="button" v-on:click="addFloorToHouse()">
          Add a floor to this house
        </button>
        <br />
        <button class="button" v-on:click="removeFloorFromHouse()">
          Remove the top floor
        </button>
      </table>
      <button id="submit" v-on:click="$router.push({ name: 'home' })">
        Submit this House
      </button>
    </div>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "specify-room-details",
  data() {
    return {
      currentHouse: this.$store.state.currentHouse,
      house_id: this.$route.params.id,
      // newFloors: [],
      floors: [],
      currentHouseName: this.$store.state.currentHouse.house_name,
      highestFloor: "",
    };
  },
  created() {
    // HomeService.getFloorDetails(this.house_id).then((response) => {
    //   for (let i = 0; i < response.data.length; i++) {
    //     const eachFloor = response.data[i];
    //     const newFloor = {
    //       floorLevel: eachFloor.floorLevel,
    //       floorId: eachFloor.floorId,
    //     };
    //     this.newFloors.push(newFloor);
    //   }
    // }),
    HomeService.getHouseDetails(this.house_id).then((response) => {
      this.currentHouseName = response.data.house_name;
    });
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
            floorId: eachRoom.floor_id,
            isKitchen: eachRoom.is_kitchen,
            isBathroom: eachRoom.is_bathroom,
            numOfWindows: eachRoom.number_of_windows,
            flooringTierId: eachRoom.flooring_tier_id,
          };
          floor.rooms.push(newRooms);
        }
      });
    },
    addFloorDetails() {
      HomeService.addNewRoom(this.newRooms, this.newRooms.floorId).then(
        // dble check
        (response) => {
          if (response.status === 200) {
            window.alert("Room Created!");
          }
        }
      );
    },
    setCurrentFloor(floorId) {
      this.$store.commit("SET_ACTIVE_FLOOR", floorId);
      this.$store.commit("SET_ACTIVE_HOUSE", this.house_id);
      this.$router.push({ name: "addRoomToFloor" });
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
  },
};
</script>

<style>
.floorDetailsStyle {
  display: flex;
  flex-direction: column;
  font-weight: bold;
  font-size: 20px;
  background-color: rgba(255, 255, 255, 0.123);
  backdrop-filter: blur(30px);
  padding: 40px;
  margin-left: 35%;
  margin-right: 35%;
}
#rooms {
  margin-left: 26%;
  margin-bottom: 15px;
}
#submit {
  align-items: center;
  background-color: #fcfcfd;
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.2) 0 2px 4px, rgba(0, 0, 0, 0.15) 0 7px 13px -3px,
    #d6d6e7 0 -3px 0 inset;
  color: #000000;
  cursor: pointer;
  display: inline-block;
  height: 48px;
  justify-content: center;
  line-height: 1;
  list-style: none;
  overflow: hidden;
  padding-left: 16px;
  padding-right: 16px;
  position: relative;
  text-align: center;
  text-decoration: none;
  transition: box-shadow 0.15s, transform 0.15s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
  will-change: box-shadow, transform;
  font-size: 18px;
  margin-top: 30px;
  font-weight: bold;
  margin-left: auto;
  margin-right: auto;
}
#whiteTitle {
  font-weight: bolder;
}
</style>