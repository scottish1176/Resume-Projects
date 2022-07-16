<template>
  <div>
    This is the List of Public Homes
    <table class="publicHomesTable">
      <tr v-for="house in homes" v-bind:key="house.id">
        <div class="publicHomes">
          <br />
          House Name: {{ house.houseName }}<br />
          <br />
          City: {{ house.city }}<br />
          <br />
          State: {{ house.state }}<br />
          <br />
          Foundation size: {{ house.foundationSize }} <br />
          <br />
          Number of Floors: {{ house.numOfFloors }} <br />
          <br />
          Estimated Cost: ${{ house.houseEstimate }} <br />
          <br />
          <button
            role="button"
            class="button-name"
            v-on:click="setActiveHouse(house.houseId)"
          >
            Checkout {{ house.houseName }}
          </button>
        </div>
      </tr>
    </table>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";
import HouseCostAPI from "../services/HouseCostAPI";

export default {
  data() {
    return {
      userID: this.$store.state.user.id,
      homes: [],
      houseParamsCost: [],
    };
  },
  created() {
    HomeService.getAllPublicHouses().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        const eachHome = response.data[i];
        const newHome = {
          houseName: eachHome.house_name,
          city: eachHome.city,
          state: eachHome.state_abbreviation,
          foundationSize: eachHome.foundation_size,
          houseId: eachHome.house_id,
          numOfFloors: eachHome.number_of_floors,
          houseEstimate: 0,
        };
        this.homes.push(newHome);
        this.getParamsForHouseCost(newHome.houseId, newHome);
      }
    });
  },
  methods: {
    setActiveHouse(houseId) {
      this.$store.commit("SET_ACTIVE_HOUSE", houseId);
      this.$router.push({ name: "viewCurrentFloorAndRoomDetails" });
    },
    getParamsForHouseCost(houseId, newHome) {
      HomeService.getHouseCostParams(houseId).then((response) => {
        const houseParamsData = response.data;
        const houseParams = {
          houseParamsCity: houseParamsData.city,
          state_code: houseParamsData.state_abbreviation,
          bathrooms: houseParamsData.bathrooms.toString(),
          bedrooms: houseParamsData.bedrooms.toString(),
          houseSize: houseParamsData.foundation_size.toString(),
          stories: houseParamsData.number_of_floors,
        };
        if (houseParams.stories > 1) {
          houseParams.stories = "multi";
        } else {
          houseParams.stories = "single";
        }
        this.houseParamsCost.push(houseParams);
        this.getHouseCost(houseParams, newHome);
      });
    },
    getHouseCost(house, newHome) {
      HouseCostAPI.getCostOfHouse(house).then((response) => {
        newHome.houseEstimate =
          response.data.data.home_search.results[0].list_price;
      });
    },
  },
};
</script>

<style>
.publicHomesTable {
  align-items: center;
  background-color: rgba(54, 148, 66, 0.397);
  margin-left: 721px;
  border-radius: 107px;
  padding-bottom: 60px;
  backdrop-filter: blur(10px);
  color: rgb(0, 0, 0);
  position: absolute;
  margin-top: 25px;
  padding: 60px;
}
.publicHomes {
  font-size: 20px;
  text-align: center;
}
</style>