package br.com.kiev.mockito;

import br.com.kiev.business.CourseBusiness;
import br.com.kiev.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseBusinessMockitoInjectMocksTest {

    @Mock
    CourseService mockService;

    @InjectMocks //business = new CourseBusiness(mockService);
    CourseBusiness business;

    List<String> courses;

    @Captor
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

    @BeforeEach
    void setup() {
        //Given /Arrange
        courses = List.of("REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker");
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAMock() {
        //Given /Arrange
        given(mockService.retrieveCourses("Kiev")).willReturn(courses);
        //When /Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Kiev");
        //Then
        assertThat(filteredCourses.size(), is(4));
    }

    @DisplayName("Delete Courses not Related to Spring Using Mockito when call Method")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingAMockito_Should_CallMethod_DeleteCourse() {
        //Given /Arrange
        given(mockService.retrieveCourses("Kiev")).willReturn(courses);
        //When /Act
        business.deleteCourseNotRelatedToSpring("Kiev");
        //Then
        verify(mockService).deleteCourse("REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker");
        verify(mockService, times(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockService, atLeast(1)).deleteCourse("Spotify Engineering Culture Desmistificado");
        verify(mockService, atLeastOnce()).deleteCourse("Docker para Amazon AWS Implante Apps Java e .NET com Travis CI");
        verify(mockService, never()).deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker");
    }

    @DisplayName("Delete Courses not Related to Spring Using Mockito when call Method V2")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingAMockito_Should_CallMethod_DeleteCourseV2() {
        //Given /Arrange
        given(mockService.retrieveCourses("Kiev")).willReturn(courses);
        //When /Act
        business.deleteCourseNotRelatedToSpring("Kiev");
        //Then
        then(mockService).should().deleteCourse("REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker");
        then(mockService).should().deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        then(mockService).should().deleteCourse("Spotify Engineering Culture Desmistificado");
        then(mockService).should().deleteCourse("Docker para Amazon AWS Implante Apps Java e .NET com Travis CI");
        then(mockService).should(never()).deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker");
    }

    @DisplayName("Delete Courses not Related to Spring Capturing Argument when call Method")
    @Test
    void testDeleteCoursesNotRelatedToSpring_CapturingArguments_Should_CallMethod_DeleteCourseV2() {
        //Given /Arrange
        var courses = List.of("Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker");

        given(mockService.retrieveCourses("Kiev")).willReturn(courses);

        var agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";

        //When /Act
        business.deleteCourseNotRelatedToSpring("Kiev");
        //Then
        then(mockService).should().deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(agileCourse));
    }
}