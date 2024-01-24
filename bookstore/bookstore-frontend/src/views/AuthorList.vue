<template>
  <div class="container">
    <b-button class="btn btn-success" v-b-modal.author-modal>Create New Author</b-button>

    <b-modal hide-footer id="author-modal" title="Author" @hidden="hidden">
      <author-form :author="selectedAuthor" ref="test" @onSave="onSave()"></author-form>
    </b-modal>

    <div>
      <table class="table table-sm mt-4 w-75">
        <thead>
          <tr>
            <th>Name</th>
            <th></th>
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
              <input class="filter-input" type="text" @input="nameChanged" />
            </th>
          </tr>
          <tr v-for="author in authors" :key="author.id">
            <td>{{ author.name }}</td>
            <td style="text-align: right !important">
              <span class="text-primary hand" @click="edit(author)"><i class="bx bx-edit-alt"></i> Edit</span>
            </td>
            <td class="">
              <span class="text-danger hand" @click="remove(author)"><i class="bx bx-trash"></i> Delete</span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import AuthorForm from "../components/author-form";
export default {
  components: { AuthorForm },
  data() {
    return {
      authors: [],
      selectedAuthor: undefined,
      filterEnabled: false,
    };
  },
  async mounted() {
    await this.fetchAuthors();
  },
  methods: {
    toggleFilter() {
      this.filterEnabled = !this.filterEnabled;
    },
    hidden() {
      this.selectedAuthor = undefined;
    },

    nameChanged(e) {
      if (!e.target.value || e.target.value == "") {
        return this.fetchAuthors();
      }
      this.authors = this.authors.filter((b) => b.name.toLowerCase().includes(e.target.value.toLowerCase()));
    },

    async fetchAuthors() {
      try {
        const { data } = await axios.get("/api/authors");

        this.authors = data;
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
      this.selectedAuthor = item;
      this.$bvModal.show("author-modal");
    },
    async onSave() {
      await this.fetchAuthors();
      this.$bvModal.hide("author-modal");
    },
    async remove(item) {
      try {
        await axios.delete(`/api/authors/${item.id}`);
        await this.fetchAuthors();
        this.$swal({
          icon: "success",
          title: "Success",
          text: "Author Removed",
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
