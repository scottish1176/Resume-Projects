<template>
  <div class="home-logout">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <br>
      <view-public-homes />
  </div>
</template>

<script>
import viewPublicHomes from '../components/ViewPublicHomes.vue';
export default {
  name: "publicHomes",
  components: {
    viewPublicHomes,
  }
}
</script>

<style>
.home-logout{
    text-align: left;
  margin-left: 20px;
  margin-top: 20px;
  font-family: 'Montserrat';
  font-weight: bold;
  font-size: 20px;
  color: black;
}
</style>