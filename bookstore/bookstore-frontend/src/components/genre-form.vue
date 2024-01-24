<template>
  <div>
    <form @submit.prevent>
      <div class="form-group">
        <label for="name"> Title </label>
        <input name="name" class="form-control" v-model="form.name" type="text" />
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
  props: ["genre"],
  data() {
    return {
      form: {
        name: undefined,
      },
    };
  },

  watch: {
    genre: {
      deep: true,
      immediate: true,
      handler(v) {
        if (v) {
          this.form = this.genre;
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
          await axios.put(`/api/genres/${this.form.id}`, this.form);
        } else {
          await axios.post(`/api/genres`, this.form);
        }
        this.$swal({
          icon: "success",
          title: "Success",
          text: "Genre Saved",
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
