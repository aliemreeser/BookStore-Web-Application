<template>
  <div class="container">
    <b-button class="btn btn-success" v-b-modal.genre-modal>Create New genre</b-button>

    <b-modal hide-footer id="genre-modal" title="genre" @hidden="hidden">
      <genre-form :genre="selectedGenre" @onSave="onSave()"></genre-form>
    </b-modal>

    <div>
      <table class="table mt-4">
        <thead>
          <tr>
            <th>Genre</th>
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
              <input class="filter-input" type="text" @input="nameChanged" />
            </th>
          </tr>
          <tr v-for="genre in genres" :key="genre.id">
            <td>{{ genre.name }}</td>
            <td style="text-align: right !important">
              <span class="text-primary hand" @click="edit(genre)"><i class="bx bx-edit-alt"></i> Edit</span>
            </td>
            <td>
              <span class="text-danger hand" @click="remove(genre)"><i class="bx bx-trash"></i> Delete</span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import GenreForm from "../components/genre-form";
export default {
  components: { GenreForm },
  data() {
    return {
      genres: [],
      selectedGenre: undefined,
      filterEnabled: false,
    };
  },
  async mounted() {
    await this.fetchGenres();
  },
  methods: {
    toggleFilter() {
      this.filterEnabled = !this.filterEnabled;
    },
    nameChanged(e) {
      if (!e.target.value || e.target.value == "") {
        return this.fetchGenres();
      }
      this.genres = this.genres.filter((b) => b.name.toLowerCase().includes(e.target.value.toLowerCase()));
    },

    hidden() {
      this.selectedGenre = undefined;
    },
    async fetchGenres() {
      try {
        const { data } = await axios.get("/api/genres");

        this.genres = data;
      } catch (error) {
        this.$swal({
          icon: "error",
          title: "Oops...",
          text: error?.response?.data?.message,
        });
        console.error(error);
      }
    },
    async edit(item) {
      this.selectedGenre = item;
      this.$bvModal.show("genre-modal");
    },
    async onSave() {
      await this.fetchGenres();
      this.$bvModal.hide("genre-modal");
    },
    async remove(item) {
      try {
        await axios.delete(`/api/genres/${item.id}`);
        await this.fetchGenres();
        this.$swal({
          icon: "success",
          title: "Success",
          text: "Genre Removed",
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
