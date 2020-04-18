<template>
  <div class="container">
    <h3>All Courses</h3>
    <div v-if="message" class="alert alert-success">
        {{message}}
    </div>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Description</th>
            <th>Username</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="course in courses" v-bind:key="course.id">
            <td>{{course.id}}</td>
            <td>{{course.description}}</td>
            <td>{{course.username}}</td>
            <td>
                <button class="btn btn-success" v-on:click="updateCourse(course.id)">
                    Update
                </button>
            </td>
            <td>
                <button class="btn btn-warning" v-on:click="deleteCourse(course.id)">
                    Delete
                </button>
            </td>
          </tr>
        </tbody>
      </table>
      <br>
      <div class="row">
            <button class="btn btn-success" v-on:click="addCourse()">Add</button>
        </div>
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
          dump: null
      };
  },
  methods: {
      getCourses() {
          CourseDataService.retrieveAllCourses()
            .then(response => {
                this.courses = response.data
            })
      },
      deleteCourse(id) {
        CourseDataService.deleteCourseById(id)
            .then(response => {
                this.message = `Delete of course ${id} Successful`;
                this.getCourses();
                this.dump = response
            });
      },
      updateCourse(id) {
          this.$router.push(`/course/${id}`);
      },
      addCourse() {
          this.$router.push(`/course/-1`);
      }
  },
  created() {
      this.getCourses()
  }
};
</script>

<style>
</style>