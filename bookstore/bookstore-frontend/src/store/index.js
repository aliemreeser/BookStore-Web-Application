import Vue from "vue";
import Vuex from "vuex";

import VuexPersist from "vuex-persist";

const vuexLocalStorage = new VuexPersist({
  key: "vuex",
  storage: window.localStorage,
});

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [vuexLocalStorage.plugin],
  state: {
    cartItems: [],
  },
  mutations: {
    addToCart(state, book) {
      if (!book.quantity || book.quantity == 0) {
        book.quantity = 1;
      }

      const existingCartItem = state.cartItems.find((item) => item.book.id === book.id);

      if (existingCartItem) {
        existingCartItem.quantity += Number(book.quantity);
        existingCartItem.price = Number(existingCartItem.quantity) * Number(book.price);
      } else {
        const cartItem = { book, quantity: Number(book.quantity), price: Number(book.quantity) * Number(book.price) };
        delete cartItem.book.quantity;
        state.cartItems.push(cartItem);
      }
    },
    removeFromCart(state, bookId) {
      state.cartItems = state.cartItems.filter((item) => item.book.id !== bookId);
    },
    clearCart(state) {
      state.cartItems = [];
    },
  },
  actions: {
    addToCart({ commit }, item) {
      commit("addToCart", item);
    },
    removeFromCart({ commit }, bookId) {
      commit("removeFromCart", bookId);
    },
    clearCart({ commit }) {
      commit("clearCart");
    },
  },
  getters: {
    totalAmount: (state) => state.cartItems.reduce((total, item) => total + item.book.price * item.quantity, 0),
  },
});
