package br.com.kiev.service;

import java.util.List;

public interface CourseService {
    List<String> retrieveCourses(String student);

    void deleteCourse(String course);
}
