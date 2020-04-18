import axios from "axios";

// const INSTRUCTOR = "rakesh";
const COURSE_API_URL = "http://localhost:8080";
// const INSTRUCTOR_API_URL = `${COURSE_API_URL}/api/courses/${INSTRUCTOR}`;
const COURSES_API_URL = `${COURSE_API_URL}/api/courses`;

class CourseDataService {
  retrieveAllCourses() {
    return axios.get(`${COURSES_API_URL}`);
  }

  retrieveCoursesByInstructor(name) {
    return axios.get(`${COURSES_API_URL}/${name}`)
  }
}

export default new CourseDataService();