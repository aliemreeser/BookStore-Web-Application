import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: function () {
      return import("../views/HomeView.vue");
    },
  },
  {
    path: "/order",
    name: "order",
    component: function () {
      return import("../views/OrderView.vue");
    },
  },
  {
    path: "/panel",
    name: "panel",
    component: function () {
      return import("../views/PanelView.vue");
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
