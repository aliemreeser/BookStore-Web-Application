<template>
  <div>
    <form @submit.prevent>
      <div class="form-group">
        <label for="title"> Title </label>
        <input name="title" class="form-control" id="title" v-model="form.title" type="text" />
      </div>

      <div class="form-group mt-2">
        <label for="price">Price </label>
        <input name="price" class="form-control" id="price" v-model="form.price" type="number" />
      </div>

      <div class="form-group mt-2">
        <label for="author">Author </label>

        <select class="form-control" v-model="form.author" id="authorSelect">
          <option v-for="author in authorList" :key="author.id" :value="author">{{ author.name }}</option>
        </select>
      </div>

      <div class="form-group mt-2">
        <label for="genre">Genre </label>

        <select class="form-control" v-model="form.genre" id="genreSelect">
          <option v-for="genre in genreList" :key="genre.id" :value="genre">{{ genre.name }}</option>
        </select>
      </div>

      <div class="form-group mt-2">
        <label for="publisher">Publisher </label>

        <select class="form-control" v-model="form.publisher" id="publisherSelect">
          <option v-for="publisher in publisherList" :key="publisher.id" :value="publisher">{{ publisher.name }}</option>
        </select>
      </div>

      <div class="text-center mt-4">
        <button class="btn btn-primary" @click="save()">save</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: ["book"],
  data() {
    return {
      form: {
        title: undefined,
        author: undefined,
        genre: undefined,
        publisher: undefined,
        price: 0,
      },

      authorList: [],
      genreList: [],
      publisherList: [],
    };
  },

  watch: {
    book: {
      deep: true,
      immediate: true,
      handler(v) {
        if (v) {
          this.form = this.book;
        }
      },
    },
  },

  async mounted() {
    await this.fetchAuthor();
    await this.fetchGenre();
    await this.fetchPublisher();
  },

  methods: {
    resetForm() {
      this.form = {};
    },
    async fetchAuthor() {
      try {
        const { data } = await axios.get("/api/authors");

        this.authorList = data;
      } catch (error) {
        this.$swal({
          icon: "error",
          title: "Oops...",
          text: error?.response?.data?.message,
        });
        console.error(error);
      }
    },
    async fetchGenre() {
      try {
        const { data } = await axios.get("/api/genres");

        this.genreList = data;
      } catch (error) {
        this.$swal({
          icon: "error",
          title: "Oops...",
          text: error?.response?.data?.message,
        });
        console.error(error);
      }
    },
    async fetchPublisher() {
      try {
        const { data } = await axios.get("/api/publishers");

        this.publisherList = data;
      } catch (error) {
        this.$swal({
          icon: "error",
          title: "Oops...",
          text: error?.response?.data?.message,
        });
        console.error(error);
      }
    },
    async save() {
      try {
        if (!this.form.title || !this.form.author || !this.form.genre || !this.form.publisher) {
          this.$swal({
            icon: "warning",
            title: "All Fields Are Required",
          });
          return;
        }

        if (this.form.id) {
          await axios.put(`/api/books/${this.form.id}`, this.form);
        } else {
          await axios.post(`/api/books`, this.form);
        }
        this.$swal({
          icon: "success",
          title: "Success",
          text: "Book Saved",
        });
        this.$emit("onSave");
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
