<template>
  <div class="container">
    <b-button class="btn btn-success" v-b-modal.publisher-modal>Create New publisher</b-button>

    <b-modal hide-footer id="publisher-modal" title="publisher">
      <publisher-form :publisher="selectedPublisher" @onSave="onSave()"></publisher-form>
    </b-modal>

    <div>
      <table class="table mt-4">
        <thead>
          <tr>
            <th>Publisher</th>
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
          <tr v-for="publisher in publishers" :key="publisher.id">
            <td>{{ publisher.name }}</td>
            <td style="text-align: right !important">
              <span class="text-primary hand" @click="edit(publisher)">edit</span>
            </td>
            <td><span class="text-danger hand" @click="remove(publisher)">delete</span></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import PublisherForm from "../components/publisher-form";
export default {
  components: { PublisherForm },
  data() {
    return {
      publishers: [],
      selectedPublisher: undefined,
      filterEnabled: false,
    };
  },
  async mounted() {
    await this.fetchPublishers();
  },
  methods: {
    toggleFilter() {
      this.filterEnabled = !this.filterEnabled;
    },
    nameChanged(e) {
      if (!e.target.value || e.target.value == "") {
        return this.fetchPublishers();
      }
      this.publishers = this.publishers.filter((b) => b.name.toLowerCase().includes(e.target.value.toLowerCase()));
    },

    hidden() {
      this.selectedPublisher = undefined;
    },
    async fetchPublishers() {
      try {
        const { data } = await axios.get("/api/publishers");

        this.publishers = data;
      } catch (error) {
        console.error(error);
      }
    },
    async edit(item) {
      this.selectedpublisher = item;
      this.$bvModal.show("publisher-modal");
    },
    async onSave() {
      await this.fetchPublishers();
      this.$bvModal.hide("publisher-modal");
    },
    async remove(item) {
      try {
        await axios.delete(`/api/publishers/${item.id}`);
        await this.fetchPublishers();
        this.$swal({
          icon: "success",
          title: "Success",
          text: "Publisher Removed",
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
