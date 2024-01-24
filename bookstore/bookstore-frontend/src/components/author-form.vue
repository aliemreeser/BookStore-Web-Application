<template>
  <div>
    <form @submit.prevent>
      <div class="form-group">
        <label for="name">Author </label>
        <input type="name" v-model="form.name" class="form-control" id="name" aria-describedby="bane" placeholder="Enter Author Name" />
      </div>
      <div class="text-center">
        <button class="btn btn-primary mt-4" @click="save()">save</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: ["author"],
  data() {
    return {
      form: {
        name: undefined,
      },
    };
  },

  watch: {
    author: {
      deep: true,
      immediate: true,
      handler(v) {
        if (v) {
          this.form = this.author;
        }
      },
    },
  },

  methods: {
    async save() {
      try {
        if (!this.form.name) {
          this.$swal({
            icon: "warning",
            title: "All Fields Are Required",
          });
          return;
        }

        if (this.form.id) {
          await axios.put(`/api/authors/${this.form.id}`, this.form);
        } else {
          await axios.post(`/api/authors`, this.form);
        }
        this.$swal({
          icon: "success",
          title: "Success",
          text: "Author Saved",
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
