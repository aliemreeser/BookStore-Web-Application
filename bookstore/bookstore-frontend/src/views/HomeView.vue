<template>
  <div class="container">
    <h1 class="text-center">Book Store</h1>
    <div class="row">
      <div class="col-9 border">
        <table class="table mt-4">
          <thead>
            <tr>
              <th>Title</th>
              <th>Author</th>
              <th>Genre</th>
              <th>Publisher</th>
              <th>Price</th>
              <th>quantity</th>
              <th scope="col">
                <a href="javascript:;" @click="toggleFilter">
                  <span v-if="filterEnabled" class="text-warning"> Hide Filters </span>
                  <span v-else class="text">
                    <i class="bx bxs-filter-alt"></i>
                    Show Filters
                  </span>
                </a>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-if="filterEnabled">
              <th>
                <input class="filter-input" type="text" @input="titleChanged" />
              </th>
              <th>
                <input class="filter-input" type="text" @input="authorChanged" />
              </th>
              <th>
                <input class="filter-input" type="text" @input="genreChanged" />
              </th>
              <th>
                <input class="filter-input" type="text" @input="publisherChanged" />
              </th>
              <th>
                <input style="width: 70px" type="text" @input="priceChanged" />
              </th>
            </tr>
            <tr v-for="book in books" :key="book.id">
              <td>{{ book.title }}</td>
              <td>{{ book.author.name }}</td>
              <td>{{ book.genre.name }}</td>
              <td>{{ book.publisher.name }}</td>
              <td>{{ book.price }}$</td>
              <td>
                <input style="width: 70px" type="number" v-model="book.quantity" min="1" />
              </td>
              <td>
                <p class="text-warning hand" @click="addToCart(book)"><i class="bx bx-cart-add">Add to Cart</i></p>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="col-3 border"><shopping-cart /></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions } from "vuex";
import ShoppingCart from "../components/shopping-cart";
export default {
  components: { ShoppingCart },
  data() {
    return {
      books: [],
      cartItems: [],
      totalAmount: undefined,
      filterEnabled: false,
    };
  },
  async mounted() {
    await this.fetchBooks();
  },
  methods: {
    ...mapActions(["addToCart"]),
    titleChanged(e) {
      if (!e.target.value || e.target.value == "") {
        return this.fetchBooks();
      }
      this.books = this.books.filter((b) => b.title.toLowerCase().includes(e.target.value.toLowerCase()));
    },
    authorChanged(e) {
      if (!e.target.value || e.target.value == "") {
        return this.fetchBooks();
      }
      this.books = this.books.filter((b) => b.author.name.toLowerCase().includes(e.target.value.toLowerCase()));
    },
    genreChanged(e) {
      if (!e.target.value || e.target.value == "") {
        return this.fetchBooks();
      }
      this.books = this.books.filter((b) => b.genre.name.toLowerCase().includes(e.target.value.toLowerCase()));
    },
    publisherChanged(e) {
      if (!e.target.value || e.target.value == "") {
        return this.fetchBooks();
      }
      this.books = this.books.filter((b) => b.publisher.name.toLowerCase().includes(e.target.value.toLowerCase()));
    },
    priceChanged(e) {
      if (!e.target.value || e.target.value == "") {
        return this.fetchBooks();
      }
      this.books = this.books.filter((b) => b.price.toString().toLowerCase().includes(e.target.value.toLowerCase()));
    },

    async fetchBooks() {
      try {
        const { data } = await axios.get("/api/books");

        this.books = data;
      } catch (error) {
        this.$swal({
          icon: "error",
          title: "Oops...",
          text: error?.response?.data?.message,
        });
      }
    },
    toggleFilter() {
      this.filterEnabled = !this.filterEnabled;
    },
  },
};
</script>

<style scoped></style>
