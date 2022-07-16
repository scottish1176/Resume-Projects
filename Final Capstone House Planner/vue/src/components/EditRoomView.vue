<template>
  <div>
       <H1>Edit Room</H1>
       <br /> <br/>
       <div class="RoomFormStyle" id="detailStyles">
      <form v-on:submit.prevent="">
      Room Name: <input class="RoomTextBox" type="text" required v-model="room[0].room_name"> <br>
      Room Size: Sq ft<input class="RoomTextBox" type="number" required v-model="room[0].room_size"> <br>
      Is this a Kitchen: <input class="checkbox" type="checkbox" value = room[0].is_kitchen v-model="room[0].is_kitchen"> <br>
      Is this a Bathroom: <input class="checkbox2" type="checkbox" value = room[0].is_bathroom v-model="room[0].is_bathroom"> <br>
      How many windows are in this room? <input class="RoomTextBox" type="number" required min= "0" value = room[0].number_of_windows v-model="room[0].number_of_windows"> <br>
      <button class="button" v-on:click="updateRoom()">Keep these changes</button>
      <button id="chex" class="button" v-on:click="deleteRoom()">Delete this room</button>
      </form>
</div>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
    name: "Edit-Room-view",
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
                        this.$router.push({name: "createFloorPlan", params: {id: this.houseId}})
                    }
                }
            )
        },
        deleteRoom() {
            HomeService.deleteRoom(this.roomId).then(
                (response) => {
                    window.confirm("Are you Sure You want to Delete This Room?")
                    if(response.status === 200) {
                        this.$router.push({name: "createFloorPlan" ,params: {id: this.houseId}})
                    }
                }
            )
        }
    }
}
</script>

<style>
#chex{
    margin-left: 10px;
}
</style>