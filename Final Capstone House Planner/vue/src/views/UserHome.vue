<template>

<div class="top-links">
  <router-link class="homeButton" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link class="logoutButton" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
   <br>
    <view-homes />
</div>

</template>


<script>
import ViewHomes from "../components/ViewHomes.vue";

export default {
  name: "userHome",
  components: {
    ViewHomes,
  },
};

</script>

<style>
.top-links{
    text-align: left;
  margin-left: 20px;
  margin-top: 20px;
  font-family: 'Montserrat';
  font-weight: bold;
  font-size: 20px;
  color: black;
}
</style>