<template>
  <div>
    <div class="loginHome">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      >Logout</router-link>
      </div>
    <h1> Please add room details to floor level: {{this.floorLevel}}</h1>  <br />
    <div class="RoomFormStyle">
    <form v-on:submit.prevent="addRoomToFloor">
      Room Name: <input class="RoomTextBox" type="text" required v-model="newRoom.room_name" />
      <br />
      Room Size Sq ft:
      <input class="RoomTextBox" type="number" min="1" required v-model="newRoom.room_size" />
      <br />
      Is this Room a Kitchen?
      <input class="checkbox" type="checkbox" value="true" v-model="newRoom.is_kitchen" /><label class="checkbox"
        >Yes</label
      ><br/>
      is this room a Bathroom?
      <input class="checkbox2" type="checkbox" value="true" v-model="newRoom.is_bathroom" /><label 
        >Yes</label
      ><br>
       How many windows will be in this room?
      <input
        class="RoomTextBox"
        type="number"
        min="0"
        required
        v-model="newRoom.number_of_windows"
      />
      <br />
      <button class="button">Add New Room</button>
    </form>
    <br>
    <button id="goBack" class="button" v-on:click="$router.push({name: 'createFloorPlan'})">Go Back</button>
    </div>
    
    </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "add-room-too-floor",
  data() {
    return {
      floorId: this.$store.state.currentFloorId,
      floorLevel: "",
      rooms: [],
      newRoom: {
        room_name: "",
        room_size: "",
        is_kitchen: false,
        is_bathroom: false,
        number_of_windows: "",
        floor_id: this.$store.state.currentFloorId,
        flooring_tier_id: 1,
      },
    };
  },
  created() {
    HomeService.getRoomsByFloorId(this.floorId).then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        const eachRoom = response.data[i];
        const newRooms = {
          roomId: eachRoom.room_id,
          roomName: eachRoom.room_name,
        };
        this.rooms.push(newRooms);
      }
    });
    HomeService.getFloorWithFloorId(this.floorId).then(
      (response) => {
        if(response.status === 200) {
          this.floorLevel = response.data.floorLevel;
        }
      }
    )
  },
  methods: {
    addRoomToFloor() {
      HomeService.addNewRoom(this.newRoom, this.floorId).then((response) => {
        if (response.status === 200) {
          this.$router.push({ name: "createFloorPlan" });
        }
      });
    },
  },
};
</script>

<style>
.RoomFormStyle {
  display: flex;
  flex-direction: column;
  font-weight: bold;
  font-size: 20px;
  background-color: rgba(255, 255, 255, 0.123);
  backdrop-filter: blur(30px);
  padding: 60px;
  margin-left: 25%;
  margin-right: 25%;
}

.RoomTextBox {
  height: 40px;
  font-size: 10pt;
  margin-top: 45px;
  border-radius: 10px;
  line-height: 80px;
  box-sizing: border-box;
  font-size: 20px;
}

.checkbox {
  margin-top: 30px;
}
.checkbox2 {
  margin-top: 40px;
  margin-bottom: 15px;
}
#goBack{
  display: block;
  align-self: center;
  width: 23%;
}
</style>