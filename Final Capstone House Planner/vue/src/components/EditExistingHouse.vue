<template>
  <div class="top-links">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      >Logout</router-link
    >
    <div class="userForm">
      <br />
      <form v-on:submit.prevent="submitChanges()">
        <div class="userFormStyle">
          House Name: <br />
          <input
            class="textbox"
            type="text"
            required
            v-model="home[0].house_name"
          />
          <br />
          <br />
          City: <br />
          <input
            class="textbox"
            type="text"
            required
            placeholder="Where will your house be located?"
            v-model="home[0].city"
          /><br />
          <br />
          State Abbreviation: <br />
          <!-- style="text-transform: uppercase"  Maybe try this for uppercase?-->
          <input
            class="textbox"
            type="text"
            maxlength="2"
            required
            placeholder="Where will your house be located?"
            v-model="home[0].state_abbreviation"
          /><br />
          <br />
          Foundation Size sq. ft: <br />
          <input
            class="textbox"
            type="number"
            placeholder="How large do you want your foundation to be?"
            min="1"
            required
            v-model="home[0].foundation_size"
          /><br />
          <br />
          <br />
          Should this house be Public or Private? <br />
          <select
            class="textbox"
            name="isPrivate"
            required
            v-model="home[0].is_private"
          >
            <option value="false">Public</option>
            <option value="true">Private</option></select
          ><br />
          <br />
          <div class="container-name">
            <a class="" href="#" target="_blank" rel="nofollow"
              ><button class="submit">Keep these changes</button></a
            >
          </div>
        </div>
      </form>
      <br />
      <button id="deleteButton" class="button" v-on:click="deleteHouse()">
        Delete this House
      </button>
    </div>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "edit-existing-house",
  data() {
    return {
      houseId: this.$route.params.id,
      home: [],
      floorIds: [],
    };
  },
  created() {
    HomeService.getHouseDetails(this.houseId).then((response) => {
      let currentHome = [];
      if (response.status === 200) {
        const eachHome = response.data;
        currentHome = {
          house_name: eachHome.house_name,
          city: eachHome.city,
          state_abbreviation: eachHome.state_abbreviation,
          foundation_size: eachHome.foundation_size,
          house_id: eachHome.house_id,
          number_of_floors: eachHome.number_of_floors,
          is_private: eachHome.is_private,
          houseEstimate: 0,
        };
      }
      this.home.push(currentHome);
    });
    HomeService.getFloorDetails(this.houseId).then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        const eachFloor = response.data[i];
        const currentFloors = {
          floor_id: eachFloor.floorId,
        };
        this.floorIds.push(currentFloors);
      }
    });
  },
  methods: {
    submitChanges() {
      HomeService.updateExistingHouseDetails(this.houseId, this.home[0]).then(
        (response) => {
          if (response.status === 200) {
            this.$router.push({
              name: "view&EditFloors&Rooms",
              params: { id: this.houseId },
            });
          }
        }
      );
    },
    // deleteFloors() {
    //   window.confirm("Are you sure you want to Delete this House");
    //   // let floorToDelete = this.floorIds.length;
    //   // while (floorToDelete > 0) {
    //   //   for (let i = 0; i < this.floorIds.length; i++) {
    //   //     HomeService.removeFloorFromHouse(
    //   //       this.floorIds[i].floor_id,
    //   //       this.houseId
    //   //     ).then((response) => {
    //   //       if (response.status === 200) {
    //   //         floorToDelete -= 1;
    //   //       }
    //   //     });
    //   //   }

    //   // for (let i = 0; i < this.floorIds.length; i++) {
    //   //   HomeService.removeFloorFromHouse(
    //   //     this.floorIds[i].floor_id,
    //   //     this.houseId
    //   //   );
    //   // }
    //   // window.confirm(
    //   //   "Building as been removed, do you wish to delete the lot?"
    //   // );
    //   // this.deleteHouse();

    //   HomeService.removeAllFloorsFromHouse(this.houseId).then((response) => {
    //     if(response.status === 200){
    //       this.deleteHouse()
    //     }
    //   })
    // },
    deleteHouse() {
      if (window.confirm("Do you want to DELETE this house?")) {
        HomeService.deleteHouse(this.houseId).then((response) => {
          if (response.status === 200) {
            this.$router.push({ name: "userHomes" });
          }
        });
      }
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
.userFormStyle {
  display: flex;
  flex-direction: column;
  font-weight: bold;
  font-size: 20px;
  background-color: rgba(255, 255, 255, 0.123);
  backdrop-filter: blur(30px);
  padding: 40px;
}
.userForm {
  justify-content: center;
}

.example_a {
  font-family: "Montserrat";
  color: #ffffff;
  text-transform: Capitalize;
  text-decoration: none;
  background: rgba(54, 148, 67, 0.45);
  padding: 20px;
  border-radius: 5px;
  display: inline-block;
  border: none;
  transition: all 0.4s ease 0s;
}

.example_a:hover {
  background: #4e965f;
  letter-spacing: 1px;
  -webkit-box-shadow: 0px 5px 40px -10px rgba(0, 0, 0, 0.57);
  -moz-box-shadow: 0px 5px 40px -10px rgba(0, 0, 0, 0.57);
  box-shadow: 5px 40px -10px rgba(0, 0, 0, 0.57);
  transition: all 0.4s ease 0s;
}

.textbox {
  height: 40px;
  font-size: 10pt;
  display: flex;
  flex-direction: column;
  margin-top: 25px;
  border-radius: 10px;
  line-height: 80px;
  box-sizing: border-box;
  font-size: 20px;
}
.submit {
  font-family: "Montserrat";
  text-transform: Capitalize;
  align-items: center;
  appearance: none;
  background-color: #fcfcfd;
  border-radius: 10px;
  border-width: 0;
  box-shadow: rgba(0, 0, 0, 0.2) 0 2px 4px, rgba(0, 0, 0, 0.15) 0 7px 13px -3px,
    #d6d6e7 0 -3px 0 inset;
  box-sizing: border-box;
  color: #000000;
  cursor: pointer;
  display: inline-flex;
  height: 48px;
  justify-content: center;
  line-height: 1;
  list-style: none;
  overflow: hidden;
  padding-left: 16px;
  padding-right: 16px;
  position: relative;
  text-align: left;
  text-decoration: none;
  transition: box-shadow 0.15s, transform 0.15s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
  will-change: box-shadow, transform;
  font-size: 18px;
  margin-bottom: 30px;
  font-weight: bold;
}

#deleteButton:hover {
  background: red;
}

#deleteButton {
  font-size: 20px;
}

/* html {
  overflow-y: scroll;
} */
input {
  font-family: "Montserrat";
}
</style>