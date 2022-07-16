<template>
  <body id="login-body" style="margin: -1%">
    <!-- <button class="about-button" v-on:click="$router.push({ name: 'about'})">About</button> -->

    <div id="login" class="text-center">
      <form class="form-signin" @submit.prevent="login">
        <h1 class="h3 mb-3 font-weight-normal">
          Please Sign in to your Account
        </h1>
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div
          id="new-account"
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >
          Thank you for registering, please sign in.
        </div>
        <!-- <label for="username" class="sr-only">Username</label> -->
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <!-- <label for="password" class="sr-only">Password</label> -->
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <button class="learn-more">
          <span class="circle" aria-hidden="true">
            <span class="icon arrow"></span>
          </span>
          <span class="button-text">Sign In</span></button
        ><br />
        <br />
      </form>
      <div class="scene">
        <button v-on:click="$router.push({ name: 'about' })">
          <div class="cube">
            <span class="side top">Us</span>
            <span class="side front">About</span>
          </div>
        </button>
      </div>
    </div>

    <div id="new-account-container">
      <h3 id="create-account">Are you new here?</h3>
      <router-link id="new-account" :to="{ name: 'register' }"
        ><button class="cta">
          <span class="hover-underline-animation"> <b>Create Account</b> </span>
          <svg
            id="arrow-horizontal"
            xmlns="http://www.w3.org/2000/svg"
            width="30"
            height="10"
            viewBox="0 0 46 16"
          >
            <path
              id="Path_10"
              data-name="Path 10"
              d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
              transform="translate(30)"
            ></path>
          </svg></button
      ></router-link>
    </div>
    <div class="main_div">
      <button v-on:click="$router.push({ name: 'guestHome' })">
        Continue as Guest
      </button>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Montserrat&display=swap");
#login-body {
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  background-image: url(https://images.unsplash.com/photo-1600585154340-be6161a56a0c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80);
  background-size: cover;
  /* background-color: #023047; */
}
#new-account {
  font-family: "Montserrat", sans-serif;
  size: 50px;
  color: rgb(255, 255, 255);
  margin-top: 50px;
}
#new-account-container {
  background-image: url(https://i.pinimg.com/originals/83/30/8f/83308f3e6c6fde56dd45f162c718ab60.jpg);
  /* padding: 150px; */
  padding-top: 405px;
  padding-bottom: 500px;
  padding-left: 150px;
  padding-right: 150px;
  margin-left: 27%;
  width: 300px;
  text-align: center;

  /* align-content: baseline; */
  /* flex-grow: .4; */
  /* text-align: center; */
  /* position: absolute; */
  /* bottom: 0%; */
}
#create-account {
  font-family: "Montserrat", sans-serif;
  color: rgb(0, 0, 0);
  font-size: 20px;
}
#login {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0 150px;
  size: 50px;
  text-align: center;
  font-family: "Montserrat", sans-serif;
  color: rgb(0, 0, 0);
}
h1 {
  text-align: center;
}
#username {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-family: "Montserrat", sans-serif;
  height: 40px;
  width: 401px;
  border-radius: 30px;
  text-align: center;
  font-size: 20px;
  border: 0px;
}
#password {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-family: "Montserrat", sans-serif;
  height: 40px;
  width: 401px;
  border-radius: 30px;
  text-align: center;
  font-size: 20px;
  border: 0px;
}
.sign-in {
  display: flex;
  flex-direction: column;
  margin-top: 25px;
  border-radius: 10px;
  margin-left: 176px;
  padding: 10px;
  line-height: 80px;
  box-sizing: border-box;
  padding-left: 50px;
  padding-right: 50px;
}
.form-signin {
  background-color: rgba(255, 255, 255, 0.301);
  padding: 50px;
  padding-bottom: 200px;
  backdrop-filter: blur(10px);
  border: 0px solid black;
  border-radius: 5px;
  position: relative;
  margin-left: 97%;
}

/* From uiverse.io */
button {
  position: relative;
  display: inline-block;
  cursor: pointer;
  outline: none;
  border: 0;
  vertical-align: middle;
  text-decoration: none;
  background: transparent;
  padding: 0;
  font-size: inherit;
  font-family: inherit;
}

button.learn-more {
  width: 12rem;
  height: auto;
}

button.learn-more .circle {
  transition: all 0.45s cubic-bezier(0.65, 0, 0.076, 1);
  position: relative;
  display: block;
  margin: 0;
  width: 3rem;
  height: 3rem;
  background: #000000;
  border-radius: 1.625rem;
}

button.learn-more .circle .icon {
  transition: all 0.45s cubic-bezier(0.65, 0, 0.076, 1);
  position: absolute;
  top: 0;
  bottom: 0;
  margin: auto;
  background: #fff;
}

button.learn-more .circle .icon.arrow {
  transition: all 0.45s cubic-bezier(0.65, 0, 0.076, 1);
  left: 0.625rem;
  width: 1.125rem;
  height: 0.125rem;
  background: none;
}

button.learn-more .circle .icon.arrow::before {
  position: absolute;
  content: "";
  top: -0.29rem;
  right: 0.0625rem;
  width: 0.625rem;
  height: 0.625rem;
  border-top: 0.125rem solid #fff;
  border-right: 0.125rem solid #fff;
  transform: rotate(45deg);
}

button.learn-more .button-text {
  transition: all 0.45s cubic-bezier(0.65, 0, 0.076, 1);
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  padding: 0.75rem 0;
  margin: 0 0 0 1.85rem;
  color: #000000;
  font-weight: 700;
  line-height: 1.6;
  text-align: center;
  text-transform: uppercase;
}

button:hover .circle {
  width: 100%;
}

button:hover .circle .icon.arrow {
  background: #fff;
  transform: translate(1rem, 0);
}

button:hover .button-text {
  color: #fff;
}
/* From uiverse.io by @alexmaracinaru */
.cta {
  border: none;
  background: none;
  padding-left: 3px;
}

.cta span {
  padding-bottom: 7px;
  letter-spacing: 4px;
  font-size: 15px;

  padding-right: 15px;
  text-transform: uppercase;
}

.cta svg {
  transform: translateX(-8px);
  transition: all 0.3s ease;
}

.cta:hover svg {
  transform: translateX(0);
}

.cta:active svg {
  transform: scale(0.9);
}

.hover-underline-animation {
  position: relative;
  color: black;
  padding-bottom: 20px;
}

.hover-underline-animation:after {
  content: "";
  position: absolute;
  width: 100%;
  transform: scaleX(0);
  height: 1.5px;
  bottom: 0;
  left: 0;
  background-color: #000000;
  transform-origin: bottom right;
  transition: transform 0.25s ease-out;
}

.cta:hover .hover-underline-animation:after {
  transform: scaleX(1);
  transform-origin: bottom left;
}
/* From uiverse.io by @SanketSuryawanshi */
.main_div {
  --color: #000000;
  position: absolute;
  z-index: 1;
  font-family: "Montserrat", sans-serif;
  margin-top: 280px;
  margin-right: 576px;
}

.main_div::before {
  content: "";
  position: absolute;
  width: 30px;
  height: 30px;
  background: transparent;
  top: -7px;
  left: -7px;
  z-index: -5;
  border-top: 3px solid var(--color);
  border-left: 3px solid var(--color);
  transition: 0.5s;
}

.main_div::after {
  content: "";
  position: absolute;
  width: 30px;
  height: 30px;
  background: transparent;
  bottom: -7px;
  right: -7px;
  z-index: -5;
  border-right: 3px solid var(--color);
  border-bottom: 3px solid var(--color);
  transition: 0.5s;
}

.main_div:hover::before {
  width: 100%;
  height: 100%;
}

.main_div:hover::after {
  width: 100%;
  height: 100%;
}

.main_div button {
  padding: 0.7em 2em;
  font-size: 16px;
  background: #222222;
  color: #fff;
  border: none;
  cursor: pointer;
  font-family: inherit;
}
.about-button {
  position: absolute;
  margin-bottom: 700px;
  margin-left: 2900px;
} /* From uiverse.io by @Mike11jr */
.scene {
  width: 10em;
  justify-content: center;
  align-items: center;
  position: absolute;
  margin-bottom: 670px;
  margin-left: 2698px;
}

.cube {
  color: #ccc;
  cursor: pointer;
  font-family: "Montserrat", sans-serif;
  transition: all 0.85s cubic-bezier(0.17, 0.67, 0.14, 0.93);
  transform-style: preserve-3d;
  transform-origin: 100% 50%;
  width: 10em;
  height: 4em;
}

.cube:hover {
  transform: rotateX(-90deg);
}

.side {
  box-sizing: border-box;
  position: absolute;
  display: inline-block;
  height: 4em;
  width: 10em;
  text-align: center;
  text-transform: uppercase;
  padding-top: 1.5em;
  font-weight: bold;
}

.top {
  background: rgba(54, 148, 67, 0.568);
  color: #222229;
  transform: rotateX(90deg) translate3d(0, 0, 2em);
  box-shadow: inset 0 0 0 5px #fff;
}

.front {
  background: rgba(59, 57, 57, 0.568);
  color: #fff;
  box-shadow: inset 0 0 0 5px #fff;
  transform: translate3d(0, 0, 2em);
}

#new-account {
  color: white;
  font-size: 20px;
  margin-bottom: 5px;
}
</style>