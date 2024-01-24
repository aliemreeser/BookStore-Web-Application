<!-- ShoppingCart.vue -->
<template>
  <div class="container mt-4 mb-4">
    <h2>Shopping Cart</h2>
    <ul>
      <li v-for="(item, index) in cartItems" :key="index">
        {{ item.book.title }} - Quantity: {{ item.quantity }} - {{ item.quantity * item.book.price }}$
        <a class="hand" @click="removeFromCart(item.book.id)"><i class="bx bxs-trash text-danger ml-1"></i></a>
      </li>
    </ul>
    <p>Total Amount: {{ totalAmount }}$</p>
    <p class="text-success hand" @click="showOrderModal"><i class="bx bxs-shopping-bag"></i>Create Order</p>

    <b-modal hide-footer id="order-modal" title="Order">
      <order-form @onSave="orderApproved"></order-form>
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapGetters } from "vuex";
import OrderForm from "./order-form";

export default {
  components: { OrderForm },
  computed: {
    ...mapGetters(["totalAmount"]),
    cartItems() {
      return this.$store.state.cartItems;
    },
  },
  methods: {
    ...mapActions(["removeFromCart", "clearCart"]),

    showOrderModal() {
      if (!this.cartItems || !this.cartItems.length > 0) {
        this.$swal({
          icon: "warning",
          title: "Oops...",
          text: "Please select at least one item",
        });

        return;
      }
      this.$bvModal.show("order-modal");
    },
    async orderApproved(customerInfo) {
      await this.createOrder(customerInfo);
    },
    async createOrder(customerInfo) {
      try {
        const payload = {
          price: this.totalAmount,
          orderDetails: this.cartItems,
          ...customerInfo,
        };

        await axios.post("/api/orders", payload);
        this.clearCart();
        this.$swal({
          icon: "success",
          title: "Success",
          text: "Order Created",
        });
      } catch (error) {
        this.$swal({
          icon: "error",
          title: "Oops...",
          text: error?.response?.data?.message,
        });
        console.error(error);
      }
    },
  },
};
</script>
