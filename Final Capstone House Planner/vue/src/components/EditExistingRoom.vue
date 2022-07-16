<template>
  <div class="roomDetail">
       <h1>Edit Room</h1>
       <br /> <br/>
      <form v-on:submit.prevent="">
      Room Name: <input class="textbox2" type="text" required v-model="room[0].room_name"> <br>
      Room Size: Sq ft<input class="textbox2" type="number" required v-model="room[0].room_size"> <br>
      Is this a Kitchen: <input type="checkbox" value = room[0].is_kitchen v-model="room[0].is_kitchen"> Yes <br>
      Is this a Bathroom: <input type="checkbox" value = room[0].is_bathroom v-model="room[0].is_bathroom"> Yes <br>
      How many windows are in this room? <input class="textbox2" type="number" required min= "0" value = room[0].number_of_windows v-model="room[0].number_of_windows"> <br>
      <button id="buttons" class="button-name" v-on:click="updateRoom()">Keep these changes</button>
      <button class="button-name" v-on:click="deleteRoom()">Delete this room</button>
      </form>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
    name: "edit-existing-room",
    data() {
        return {
            roomId: this.$route.params.id,
            room: [],
            houseId: this.$store.state.currentHouseId
        
        }
    },
    created() {
        HomeService.getRoomByRoomId(this.roomId).then(
            (response) => {
               let roomInfo = []
               if(response.status === 200) {
                    roomInfo = {
                    room_name: response.data.room_name,
                    room_size: response.data.room_size,
                    is_kitchen: response.data.is_kitchen,
                    is_bathroom: response.data.is_bathroom,
                    number_of_windows: response.data.number_of_windows,
                    room_id: response.data.room_id,
                    }
                }
                this.room.push(roomInfo)
            }
        )
    },
    methods: {
        updateRoom() {
            HomeService.updateExistingRoom(this.roomId, this.room[0]).then(
                (response) => {
                    if(response.status === 200) {
                        this.$router.push({name: "view&EditFloors&Rooms", params: {id: this.houseId}})
                    }
                }
            )
        },
        deleteRoom() {
            HomeService.deleteRoom(this.roomId).then(
                (response) => {
                    window.confirm("Are you Sure You want to Delete This Room?")
                    if(response.status === 200) {
                        this.$router.push({name: "view&EditFloors&Rooms" ,params: {id: this.houseId}})
                    }
                }
            )
        }
    }
}
</script>

<style>
.roomDetail{
  align-items: center;
  background-color: rgba(54, 148, 66, 0.397);
  margin-left: 50px;
  border-radius: 107px;
  padding-bottom: 60px;
  backdrop-filter: blur(10px);
  color: rgb(0, 0, 0);
  position: relative;
  margin-top: 50px;
  padding: 60px;
  font-size: 20px;
  font-weight: bold;
  width: 50%;
  display: flex;
  flex-direction: column;
  margin-left: 420px;
  line-height: 50px;
}
.textbox2{
  height: 40px;
  font-size: 10pt;
  border-radius: 10px;
  line-height: 80px;
  box-sizing: border-box;
  font-size: 20px;
  margin-left: 10px;
  
}
#buttons {
    margin-right: 20px;
}



</style>