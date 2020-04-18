import axios from "axios";

const BACKEND_URL = "http://localhost:8080";
const COURSES_API_URL = `${BACKEND_URL}/api/courses`;

class CourseDataService {
  retrieveAllCourses() {
    return axios.get(`${COURSES_API_URL}`);
  }

  retrieveCoursesByInstructor(name) {
    return axios.get(`${COURSES_API_URL}/${name}`)
  }

  deleteCourse(id) {
      return axios.delete(`${COURSES_API_URL}/${id}`)
  }

  getCourseById(id) {
      return axios.get(`${COURSES_API_URL}/id/${id}`)
  }

    updateCourse(id, course) {
        return axios.put(`${COURSES_API_URL}/${id}`, course);
    }

    createCourse(course) {
        return axios.post(`${COURSES_API_URL}`, course);
    }
}

export default new CourseDataService();