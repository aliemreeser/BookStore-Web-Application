<template>
  <div class="container">
    <b-tabs content-class="mt-3">
      <b-tab title="Author" active><author-list></author-list></b-tab>
      <b-tab title="Publisher"><publisher-list></publisher-list></b-tab>
      <b-tab title="Genre"><genre-list></genre-list></b-tab>
      <b-tab title="Book"><book-list></book-list></b-tab>
    </b-tabs>
  </div>
</template>
<script>
import AuthorList from "./AuthorList.vue";
import BookList from "./BookList.vue";
import GenreList from "./GenreList.vue";
import PublisherList from "./PublisherList.vue";

export default {
  components: { AuthorList, PublisherList, GenreList, BookList },
};
</script>
