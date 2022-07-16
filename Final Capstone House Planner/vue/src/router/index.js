import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import UserHome from '../views/UserHome.vue'
import PublicHomes from '../views/PublicHomes.vue'
import CreateNewHome from '../views/CreateNewHome.vue'
import CreateFloorPlan from '../components/S2_SpecifyFloorDetails.vue'
import GuestHome from '../views/GuestHome.vue'
import GuestPublicHomes from '../components/GuestPublicHomes.vue'
import SelectedHouseDetails from '../views/SelectedHouseDetails.vue'
import AddRoomToFloor from '../components/S3_AddRoomToFloor.vue'
import CurrentFloorAndRoomDetails from '../components/ViewFloorAndRoomDetails.vue'
import GuestViewFloor from '../components/GuestViewFloorPlans.vue'
import ViewAndEditFloorAndRoom from '../components/View&EditFloor&Room.vue'
import EditRoomView from '../components/EditRoomView.vue'
import EditExistingRoom from '../components/EditExistingRoom.vue'
import AddRoomToExistingHouse from '../components/AddRoomToExistingHouse.vue'
import EditExistingHouseDetails from '../components/EditExistingHouse.vue'
import about from '../views/About.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/userHomes",
      name: "userHomes",
      component: UserHome,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/publicHomes",
      name: "publicHomes",
      component: PublicHomes,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/createNewHome",
      name: "createNewHome",
      component: CreateNewHome,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/createFloorPlan/:id", //S2 House ID
      name: "createFloorPlan",
      component: CreateFloorPlan,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/guestHome",
      name: "guestHome",
      component: GuestHome,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/guestPublicHomes",
      name: "guestPublicHomes",
      component: GuestPublicHomes,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/selectedHouseDetails",
      name: "selectedHouseDetails",
      component: SelectedHouseDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/addRoomToFloor/:id",  //S3 floorID
      name: "addRoomToFloor",
      component: AddRoomToFloor,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/viewCurrentFloorAndRoomDetails",
      name: "viewCurrentFloorAndRoomDetails",
      component: CurrentFloorAndRoomDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/GuestViewFloor",
      name: "GuestViewFloor",
      component: GuestViewFloor,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/view&EditFloors&Rooms/:id", //houseID
      name: "view&EditFloors&Rooms",
      component: ViewAndEditFloorAndRoom,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/editRoomView/:id", //roomID
      name: "editRoomView",
      component: EditRoomView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/editExistingRoom/:id",
      name: "editExistingRoom",
      component: EditExistingRoom,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/addRoomToExistingFloor/:id/:houseId", //floorID
      name: "addRoomToExistingFloor",
      component: AddRoomToExistingHouse,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/editExistingHouseDetails/:id",
      name: "editExistingHouseDetails",
      component: EditExistingHouseDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/about",
      name: "about",
      component: about,
      meta: {
        requiresAuth: false
      }
    }
    
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
