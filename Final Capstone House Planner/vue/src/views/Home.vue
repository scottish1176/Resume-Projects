<template>
  <div class="home">
    <div class="homeLogout">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <p class="buttonTag">Welcome to Build a Home!</p>
    <div class="buttons">
      <button class="button" v-on:click="$router.push({ name: 'userHomes' })">
        View your home plans
      </button>
      <br />
      <br />
      <button class="button" v-on:click="$router.push({ name: 'publicHomes' })">
        View public home plans
      </button>
      <br />
      <br />
      <button
        class="button"
        v-on:click="$router.push({ name: 'createNewHome' })"
      >
        Create a new home plan
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>


<style>
.buttons {
  margin-top: 100px;
}
.button:hover {
  background-color: #4e944f;
  transition-duration: 0.15s;
}
.button {
  background-color: rgba(54, 148, 67, 0.61); /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  border-radius: 25px;
}
div {
  text-align: center;
  align-self: center;
}
.buttonTag {
  margin-top: 100px;
  font-size: 35px;
  font-weight: bold;
}
.homeLogout {
  text-align: left;
  margin-left: 20px;
  margin-top: 20px;
  font-family: "Montserrat";
  font-weight: bold;
  font-size: 20px;
  color: black;
}
a:visited {
  text-decoration: none;
  color: black;
}
</style>
