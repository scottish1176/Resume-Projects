<template>
  <body>
    <div class="guestHome">
      <!-- <router-link v-bind:to="{ name: 'guestHome' }">Home</router-link>&nbsp;|&nbsp; -->
      <router-link
        id="create"
        v-bind:to="{ name: 'register' }"
        style="text-decorations: none; color: inherit"
        >Create Account?</router-link
      >

      <h1 id="guest"><b>Real Construction</b></h1>
      <span class="guest-welcome-text">
        <p id="welcome"><b>Welcome to our house planner</b></p>
        <p id="limited-view">
          <b>As a guest you can only view limited items</b>
        </p>
        <p id="create-account-prompt">
          <b>Create an account to customize your own home today!</b>
        </p>
        <p id="click-on">
          <b>Otherwise click here to see public floor plans</b>
        </p>
      </span>
      <div>
        <button
          class="guestbutton"
          v-on:click="$router.push({ name: 'guestPublicHomes' })"
        >
          Public Floor plans
        </button>
        <br />
        <br />
      </div>
    </div>
  </body>
</template>

<script>
export default {
  name: "guestHome",
};
</script>

<style>
body {
  font-family: "Montserrat", sans-serif;
}
html {

  margin: -1%;
  background: url(https://www.decoraid.com/wp-content/uploads/2021/04/bright-minimalist-living-room-design-scaled.jpeg)
    no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

#guest {
  font-size: 80px;
  text-align: left;
  margin-left: 30px;
}
.guestbutton {
  background-color: rgba(54, 148, 66, 0.397);
  border: none;
  color: rgb(0, 0, 0);
  padding: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 35px;
  border-radius: 90px;
  margin-right: 1346px;
  width: 500px;
}
.guestbutton:hover {
  background-color: #4e944f;
  transition-duration: 0.15s;
}
#create {
  position: absolute;
  padding: 20px;
  margin-left: 713px;
  font-size: 25px;
  margin-top: -45px;
  color: rgb(0, 0, 0);
  border-bottom-left-radius: 30px;
  background-color: rgba(54, 148, 66, 0.397);
  white-space: nowrap;
  backdrop-filter: blur(5px);
  text-decoration: none;
}
#create:hover{
  background-color: #4e944f;
  transition-duration: 0.15s;
}
.guest-welcome-text {
  display: inline-block;
  font-size: 20px;
  text-align: left;
  margin-right: 1300px;
}
</style>