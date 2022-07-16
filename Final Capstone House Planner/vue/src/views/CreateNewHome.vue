<template>
  <div>
    <div class="loginHome">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
     </div>
      <h2>Enter your house specifications</h2>
    <specify-house-details class="userForm" />
  </div>
</template>

<script>
import SpecifyHouseDetails from '../components/S1_SpecifyHouseDetails.vue'
export default {
    name: "CreateNewHome",
    components: {
        SpecifyHouseDetails,
    }
}
</script>

<style>
.userForm{
display: flex;
}
.loginHome{
  text-align: left;
  margin-left: 20px;
  margin-top: 20px;
  font-family: 'Montserrat';
  font-weight: bold;
  font-size: 20px;
  color: black;
}

</style>