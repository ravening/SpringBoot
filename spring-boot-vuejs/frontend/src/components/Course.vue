<template>
  <div>
    <h3>Course</h3>
    <div class="container">
      <form @submit="validateAndSubmit">
        <fieldset class="form-group">
          <label>Id</label>
          <input type="text" class="form-control" v-model="id" disabled>
        </fieldset>
        <fieldset class="form-group">
          <label>Description</label>
          <input type="text" class="form-control" v-model="description">
        </fieldset>
        <fieldset class="form-group">
          <label>Username</label>
          <input type="text" class="form-control" v-model="username">
        </fieldset>
        <button class="btn btn-success" type="submit">Save</button>
      </form>
    </div>
  </div>
</template>

<script>
import CourseDataService from '../service/CourseDataService';
export default {
  name: "courseDetails",
  data() {
    return {
      description: "",
      username: "",
      errors: []
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    }
  },
  methods: {
    refreshCourseDetails() {
        CourseDataService.getCourseById(this.id)
          .then(res => {
            this.description = res.data.description;
            this.username = res.data.username;
          });
    },
    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if(!this.description) {
          this.errors.push("Enter valid values");
      } else if(this.description.length < 5) {
          this.errors.push("Enter atleast 5 characters in Description");
      }
      if(this.errors.length == 0) {
          if (this.id == -1) {
              CourseDataService.createCourse({
                  username: this.username,
                  description: this.description
              })
              .then(() => {
                  this.$router.push('/courses');
              });
          } else {
              CourseDataService.updateCourse(this.id, {
                  id: this.id,
                  username: this.username,
                  description: this.description
                  
              })
              .then(() => {
                  this.$router.push('/courses');
              });
          }
      }

      console.log({
        id: this.id,
        description: this.description,
        username: this.username
      })  
    }
  },
  created() {
    this.refreshCourseDetails();
  }
};
</script>

<style>
</style>