<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link> | <router-link to="/order">Orders</router-link> |
      <router-link to="/panel">Panel</router-link>
    </nav>
    <router-view />
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

nav {
  text-align: center;
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
.hand {
  cursor: pointer;
}
.filter-input {
  width: 100px;
}
</style>
