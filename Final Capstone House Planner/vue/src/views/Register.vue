<template>
  <body id="register-body">
    
    <div id="register" class="text-center">
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link> -->
      <form class="form-register" @submit.prevent="register">
        <h1 id="register-header">Create Account</h1>
        <!--class="h3 mb-3 font-weight-normal"-->
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <!-- <label for="username" class="sr-only">Username</label> -->
        <input
          type="text"
          id="regusername"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <!-- <label for="password" class="sr-only">Password</label> -->
        <input
          type="password"
          id="regpassword"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
        <!-- <input
          type="radio"
          id="home-buyer"
          class="form-control"
          v-model="user.role"
          name="Role"
          value="Home_Buyer"
          required
        /> -->
        <!-- <label class="select-role"  for="role" id="home-buyer-label">Home Buyer</label><br /> -->
        <button class="learn-more" id="reg-create-account">
          <span class="circle" aria-hidden="true">
            <span class="icon arrow"></span>
          </span>
          <span class="button-text">Create</span>
        </button>
        <router-link id="login-link" :to="{ name: 'login' }"
          ><button class="cta">
            <span class="hover-underline-animation">
              <b>Already have an account?</b>
            </span>
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
      </form>
    </div>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
    />
  </body>
</template>

<script>
import authService from "../services/AuthService";



export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "HOME_BUYER",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
#register-body {
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  background-color: #023047;
  background-image: url(https://wallpaperaccess.com/full/2056374.jpg);
  background-size:cover;
  padding-bottom: 440px;
  width: 2000px;
  overflow-y: auto;
  margin: -1%;
}
#register-header {
  color: white;
  padding-top: 150px;
  font-size: 45px;
}
#register {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0 150px;
  size: 50px;
  text-align: center;
  font-family: "Montserrat", sans-serif;
}
#regusername {
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
#regpassword {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-family: "Montserrat", sans-serif;
  text-align: center;
  height: 40px;
  width: 401px;
  border-radius: 30px;
  font-size: 20px;
  border: 0px;
}
#confirmPassword {
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
#home-buyer {
  justify-self: center;
}

#system-admin {
  margin-left: -30px;
}
#home-buyer-label {
  color: whitesmoke;
}
#system-admin-label {
  color: whitesmoke;
}
#reg-create-account {
  display: flex;
  flex-direction: column;
  margin-top: 35px;
  border-radius: 10px;
  margin-left: 108px;
  margin-bottom: 35px;
}
#login-link {
  font-family: "Montserrat", sans-serif;
  size: 50px;
  color: whitesmoke;
  align-content: top;
}
html{
  /* overflow-y: hidden; */
  overflow-x: hidden;
}
.select-role{
  color: black;

}
/* ::-webkit-scrollbar {
    display: none;
} */
</style>
