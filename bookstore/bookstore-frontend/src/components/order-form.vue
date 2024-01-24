<template>
  <div>
    <form @submit.prevent>
      <div class="form-group">
        <label for="name"> Customer Name </label>
        <input name="name" class="form-control" id="name" v-model="form.customerName" type="text" />
      </div>

      <div class="form-group mt-2">
        <label for="address">Address </label>
        <input name="address" class="form-control" id="address" v-model="form.address" type="text" />
      </div>

      <div class="text-center mt-4">
        <button class="btn btn-primary" @click="save()">save</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        customerName: undefined,
        address: undefined,
      },
    };
  },

  methods: {
    resetForm() {
      this.form = {};
    },
    async save() {
      if (!this.form.customerName || !this.form.address) {
        this.$swal({
          icon: "warning",
          title: "All Fields Are Required",
        });
        return;
      }

      this.$emit("onSave", this.form);
      this.$root.$bvModal.hide("order-modal");
    },
  },
};
</script>
