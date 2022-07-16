<template>
  <body class="guest-public-homes">
    <div>
      <div class="in-and-out">
        <router-link v-bind:to="{ name: 'guestHome' }">Home</router-link
        >&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'register' }"
          >Create Account?</router-link
        >
        <br />
        <h1>Public Homes</h1>
      </div>
      <table id="guestFloorTable">
        <tr v-for="house in homes" v-bind:key="house.id">
          <div class="homes">
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
  </body>
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
          numOfFloors: eachHome.number_of_floors,
          houseId: eachHome.house_id,
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
      this.$router.push({ name: "GuestViewFloor" });
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
      // this.houseEstimate = HouseCostAPI.getCostOfHouse(house);
    },
  },
};
</script>

<style>
#guestFloorTable {
  align-items: center;
  background-color: rgba(54, 148, 66, 0.397);
  margin-left: 733px;
  border-radius: 107px;
  padding-bottom: 60px;
  backdrop-filter: blur(10px);
  color: rgb(0, 0, 0);
  position: absolute;
  margin-top: 25px;
  padding: 60px;
  font-weight: bold;
}
.in-and-out {
  text-align: left;
  margin-left: 12px;
  margin-top: 20px;
  font-family: "Montserrat";
  font-weight: bold;
  font-size: 20px;
  color: black;
}
.homes {
  font-size: 20px;
  text-align: center;
}

.button-name {
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

.button-name:focus {
  box-shadow: #d6d6e7 0 0 0 1.5px inset, rgba(45, 35, 66, 0.4) 0 2px 4px,
    rgba(45, 35, 66, 0.3) 0 7px 13px -3px, #d6d6e7 0 -3px 0 inset;
}

.button-name:hover {
  box-shadow: rgba(45, 35, 66, 0.3) 0 4px 8px,
    rgba(45, 35, 66, 0.2) 0 7px 13px -3px, #d6d6e7 0 -3px 0 inset;
  transform: translateY(-2px);
}

.button-name:active {
  box-shadow: #d6d6e7 0 3px 7px inset;
  transform: translateY(2px);
}
br {
  content: "";
  margin: 2em;
  display: block;
  font-size: 24%;
}

</style>