package br.com.kiev.business;

import br.com.kiev.service.CourseService;

import java.util.Collections;
import java.util.List;

// SUT - System Under Test
public class CourseBusiness {
    private final CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }


    public List<String> retrieveCoursesRelatedToSpring(String student) {
        if (!student.equals("Kiev")) {
            return Collections.emptyList();
        }
        var allCourses = service.retrieveCourses(student);
        return allCourses.stream().filter(s -> s.contains("Spring")).toList();
    }

    public void deleteCourseNotRelatedToSpring(String student) {
        var allCourses = service.retrieveCourses(student);
        for (var course : allCourses.stream().filter(s -> !s.contains("Spring")).toList()) {
            service.deleteCourse(course);
        }
    }
}
