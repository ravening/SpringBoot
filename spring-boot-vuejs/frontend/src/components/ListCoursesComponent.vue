<template>
  <div class="container">
    <h3>All Courses</h3>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Description</th>
            <th>Username</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="course in courses" v-bind:key="course.id">
            <td>{{course.id}}</td>
            <td>{{course.description}}</td>
            <td>{{course.username}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import CourseDataService from '../service/CourseDataService';
export default {
  data() {
      return {
          courses: [],
          message: null,
          INSTRUCTOR : "rakesh"
      };
  },
  methods: {
      getCourses() {
          CourseDataService.retrieveAllCourses()
            .then(response => {
                this.courses = response.data
            })
      },
      getCoursesByInstructor() {
          CourseDataService.retrieveCoursesByInstructor(this.INSTRUCTOR)
            .then(response => {
                console.log(response.data)
            });
      }
  },
  created() {
      this.getCourses()
      console.log("=======")
      console.log("courses by rakesh")
      this.getCoursesByInstructor()
  }
};
</script>

<style>
</style>