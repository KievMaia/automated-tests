package br.com.kiev.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

class ListWithBDDTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        //Given /Arrange
        var list = mock(List.class);
        given(list.size()).willReturn(10);
        //When /Act & //Then
        assertThat(list.size(), is(10));
    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        //Given /Arrange
        var list = mock(List.class);
        given(list.size()).willReturn(10).willReturn(20);
        //When /Act & //Then
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(20));
    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnKiev() {
        //Given /Arrange
        var list = mock(List.class);
        given(list.get(0)).willReturn("Kiev");
        //When /Act & //Then
        assertThat(list.get(0), is("Kiev"));
        assertThat(list.get(1), nullValue());
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnKiev() {
        //Given /Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willReturn("Kiev");
        //When /Act & //Then
        assertThat(list.get(anyInt()), is("Kiev"));
        assertThat(list.get(anyInt()), is("Kiev"));
    }

    @Test
    void testMockingList_When_ThrowsAnException() {
        //Given /Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willThrow(new RuntimeException("Foo Bar"));
        //When /Act & //Then
        assertThrows(RuntimeException.class, 
                () -> list.get(anyInt()),
                "Should have thrown an RunTimeException");
    }
}