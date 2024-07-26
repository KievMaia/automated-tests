package br.com.kiev.mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class SpyTest {

    @Test
    void test() {
        //Given /Arrange
        var mockArrayList = spy(ArrayList.class);

        //When /Act & //Then
        assertEquals(0, mockArrayList.size());

        when(mockArrayList.size()).thenReturn(5);
        mockArrayList.add("Foo Bar");

        assertEquals(5, mockArrayList.size());
    }

    @Test
    void testV2() {
        //Given /Arrange
        var spyArrayList = spy(ArrayList.class);

        //When /Act & //Then
        assertEquals(0, spyArrayList.size());

        spyArrayList.add("Foo Bar");
        assertEquals(1, spyArrayList.size());

        spyArrayList.remove("Foo Bar");
        assertEquals(0, spyArrayList.size());
    }

    @Test
    void testV3() {
        //Given /Arrange
        var spyArrayList = spy(ArrayList.class);

        //When /Act & //Then
        assertEquals(0, spyArrayList.size());

        when(spyArrayList.size()).thenReturn(5);

        assertEquals(5, spyArrayList.size());
    }

    @Test
    void testV4() {
        //Given /Arrange
        var spyArrayList = spy(ArrayList.class);
        spyArrayList.add("Foo Bar");

        //When /Act & //Then
        verify(spyArrayList, atMostOnce()).add("Foo Bar");
        verify(spyArrayList, never()).remove("Foo Bar");
        verify(spyArrayList, never()).remove(anyString());
        verify(spyArrayList, never()).clear();
    }
}