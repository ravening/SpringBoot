<template>
  <!-- <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div> -->

  <div class="container">
    <InstructorApp />
  </div>
</template>

<script>
import InstructorApp from './components/InstructorApp.vue'

export default {
  // name: 'App',
  // components: {
  //   HelloWorld
  // }

  name: 'Rakesh Venkatesh',
  components: {
    InstructorApp
  }
}
</script>

<style>
@import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css)
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */
</style>
