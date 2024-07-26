package br.com.kiev.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        //Given /Arrange
        var list = mock(List.class);
        when(list.size()).thenReturn(10);
        //When /Act & //Then
        assertEquals(10, list.size());
    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        //Given /Arrange
        var list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);
        //When /Act & //Then
        assertEquals(10, list.size());
        assertEquals(20, list.size());
    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnKiev() {
        //Given /Arrange
        var list = mock(List.class);
        when(list.get(0)).thenReturn("Kiev");
        //When /Act & //Then
        assertEquals("Kiev", list.get(0));
        assertNull(list.get(1));
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnKiev() {
        //Given /Arrange
        var list = mock(List.class);
        when(list.get(anyInt())).thenReturn("Kiev");
        //When /Act & //Then
        assertEquals("Kiev", list.get(anyInt()));
        assertEquals("Kiev", list.get(anyInt()));
    }

    @Test
    void testMockingList_When_ThrowsAnException() {
        //Given /Arrange
        var list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar"));
        //When /Act & //Then
        assertThrows(RuntimeException.class, () -> {
            list.get(anyInt());
        }, "Should have thrown an RunTimeException");
    }
}