import axios from "axios";
import Vue from "vue";
import VueAxios from "vue-axios";

Vue.use(VueAxios, axios);

const BACKEND_URL = process.env.VUE_APP_BACKEND_URL || "http://localhost:8080";
axios.defaults.baseURL = BACKEND_URL;
axios.defaults.withCredentials = false;
axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
export default axios;
