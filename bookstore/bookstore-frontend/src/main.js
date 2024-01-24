import Vue from "vue";
import App from "./App.vue";

import VueSweetalert2 from "vue-sweetalert2";
import "./config/axios";
import router from "./router";
import store from "./store";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

import "bootstrap-vue/dist/bootstrap-vue.css";
import "bootstrap/dist/css/bootstrap.css";
import "boxicons/css/boxicons.min.css";
import "sweetalert2/dist/sweetalert2.min.css";

Vue.use(BootstrapVue);

Vue.use(IconsPlugin);
Vue.use(VueSweetalert2);

Vue.filter("date", (value, withTime = false) => {
  if (!value) {
    return null;
  }
  const date = new Date(value);
  return date.toLocaleDateString("tr") + (withTime ? " " + date.toLocaleTimeString("tr") : "");
});

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: function (h) {
    return h(App);
  },
}).$mount("#app");
