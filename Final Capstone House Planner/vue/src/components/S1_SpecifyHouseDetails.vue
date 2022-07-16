<template>
  <div class="userForm">
    <br />
    <form v-on:submit.prevent="addHouseDetails">
      <div class="userFormStyle">
        House Name: <br />
        <input
          class="textbox"
          type="text"
          placeholder="Enter a name for this build"
          required
          v-model="newHome.house_name"
        />
        <br />
        <br />
        City: <br />
        <input
          class="textbox"
          type="text"
          required
          placeholder="Where will your house be located?"
          v-model="newHome.city"
        /><br />
        <br />
        State Abbreviation: <br /> <!-- style="text-transform: uppercase"  Maybe try this for uppercase?-->
        <input
          class="textbox"
          type="text"
          maxlength="2"
          required
          placeholder="Where will your house be located?"
          v-model="newHome.state_abbreviation"
        /><br />
        <br />
        Foundation Size sq. ft: <br />
        <input
          class="textbox"
          type="number"
          placeholder="How large do you want your foundation to be?"
          min="1"
          required
          v-model="newHome.foundation_size"
        /><br />
        <br />
        Number of Floors in this House: <br />
        <input
          class="textbox"
          type="number"
          placeholder="How many floors?"
          min="1"
          required
          v-model="newHome.number_of_floors"
        /><br />
        <br />
        Should this house be Public or Private? <br />
        <select
          class="textbox"
          name="isPrivate"
          required
          v-model="newHome.is_private"
        >
          <option value="false">Public</option>
          <option value="true">Private</option></select
        ><br />
        <br />
        <div class="container-name">
          <a class="" href="#" target="_blank" rel="nofollow"
            ><button class="submit">Submit</button></a
          >
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "specify-house-details",
  data() {
    return {
      newHome: {
        house_name: "",
        city: "",
        state_abbreviation: "".toUpperCase(),
        foundation_size: "",
        number_of_floors: "",
        is_private: "",
        user_id: this.$store.state.user.id,
        house_id: ""
      },
    };
  },
  methods: {
    addHouseDetails() {
      HomeService.addNewHouse(this.newHome).then((response) => {
        if (response.status === 200) {
          this.newHome.house_id = response.data
          // let houseId = response.data;
          this.$store.commit("SET_ACTIVE_HOUSE", this.house_id);
          this.$store.commit("SET_CURRENT_HOUSE", this.newHome);
          this.$router.push({ name: "createFloorPlan", params: { id: this.newHome.house_id} });
        }
      });
    },
  },
};
</script>

<style>
.userFormStyle {
  display: flex;
  flex-direction: column;
  font-weight: bold;
  font-size: 20px;
  background-color: rgba(255, 255, 255, 0.123);
  backdrop-filter: blur(30px);
  padding: 40px;
}
.userForm{
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
  background: #4E965F;
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
.submit{
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

/* html {
  overflow-y: scroll;
} */
input{
  font-family: 'Montserrat';
}
</style>