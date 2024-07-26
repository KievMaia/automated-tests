package br.com.kiev.business;

import br.com.kiev.service.CourseService;
import br.com.kiev.service.stubs.CourseServiceStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseBusinessStubTest {

    @Test
    void testCoursesRelatedToSpring_When_UsingAStub() {
        //Given /Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        //When /Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Kiev");
        //Then
        assertEquals(4, filteredCourses.size());
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingFooBarStudent() {
        //Given /Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        //When /Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Foo Bar");
        //Then
        assertEquals(0, filteredCourses.size());
    }
}