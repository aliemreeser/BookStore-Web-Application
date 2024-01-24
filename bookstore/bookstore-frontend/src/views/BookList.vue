<template>
  <div class="container">
    <b-button class="btn btn-success" v-b-modal.book-modal>Create New book</b-button>

    <b-modal hide-footer id="book-modal" title="book" @hidden="hidden">
      <book-form :book="selectedbook" @onSave="onSave()"></book-form>
    </b-modal>

    <div>
      <table class="table mt-4">
        <thead>
          <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Publisher</th>
            <th>Price</th>
            <th scope="col">
              <a href="javascript:;" @click="toggleFilter">
                <span v-if="filterEnabled" class="text-warning"> Hide Filters </span>
                <span v-else class="text">
                  <i class="bx bxs-filter-alt"></i>
                  Show Filters
                </span>
              </a>
            </th>
            <th></th>
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
            <td style="text-align: right !important">
              <span class="text-primary hand" @click="edit(book)"><i class="bx bx-edit-alt"></i>Edit</span>
            </td>
            <td>
              <span class="text-danger hand" @click="remove(book)"><i class="bx bx-trash"></i> Delete</span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import BookForm from "../components/book-form";
export default {
  components: { BookForm },
  data() {
    return {
      books: [],
      selectedbook: undefined,
      filterEnabled: false,
    };
  },
  async mounted() {
    await this.fetchBooks();
  },
  methods: {
    toggleFilter() {
      this.filterEnabled = !this.filterEnabled;
    },
    hidden() {
      this.selectedbook = undefined;
    },
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
        console.error(error);
      }
    },
    async edit(item) {
      this.selectedbook = item;
      this.$bvModal.show("book-modal");
    },
    async onSave() {
      await this.fetchBooks();
      this.$bvModal.hide("book-modal");
    },
    async remove(item) {
      try {
        await axios.delete(`/api/books/${item.id}`);
        await this.fetchBooks();
        this.$swal({
          icon: "success",
          title: "Success",
          text: "Book Saved",
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
