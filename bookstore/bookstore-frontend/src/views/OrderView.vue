<template>
  <div class="container">
    <h1 class="text-center">Orders</h1>

    <div class="table table-responsive" v-if="orders.length > 0">
      <table class="table mt-4">
        <thead>
          <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Price</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in orders" :key="order.id">
            <td>{{ order.customerName }}$</td>
            <td>{{ order.address }}$</td>
            <td>{{ order.price }}$</td>
            <td>{{ order.at | date(true) }}</td>
            <td>
              <p class="text-warning hand" @click="showDetail(order)"><i class="bx bx-plus"></i>Details</p>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else>There is no order yet</div>

    <div v-if="selectedOrder">
      <div class="table table-responsive">
        <table class="table mt-4">
          <thead>
            <tr>
              <th>Book</th>
              <th>Quentity</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in selectedOrder.orderDetails" :key="index">
              <td>{{ `${item.book.author.name} | ${item.book.title} | ${item.book.publisher.name} | ${item.book.price}$` }}</td>
              <td>{{ item.quantity }}</td>
              <td>{{ item.price }}$</td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="2"></td>
              <td class="text-right">
                <strong>Total Price:</strong> <span id="totalPrice">{{ selectedOrder.price }}$</span>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      orders: [],
      selectedOrder: undefined,
    };
  },
  async mounted() {
    await this.fetchOrders();
  },
  methods: {
    async fetchOrders() {
      try {
        const { data } = await axios.get("/api/orders");

        this.orders = data;
      } catch (error) {
        this.$swal({
          icon: "error",
          title: "Oops...",
          text: error?.response?.data?.message,
        });
      }
    },

    showDetail(order) {
      this.selectedOrder = order;
    },
  },
};
</script>

<style scoped></style>
