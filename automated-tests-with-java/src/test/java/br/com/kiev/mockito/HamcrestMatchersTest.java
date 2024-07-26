package br.com.kiev.mockito;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class HamcrestMatchersTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        //Given /Arrange
        var scores = List.of(99, 100, 101, 105);

        //When /Act & //Then
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(99, 100));
        assertThat(scores, everyItem(greaterThan(98)));
        assertThat(scores, everyItem(lessThan(106)));

        //Check String
        assertThat("", is(emptyString()));
        assertThat(null, is(emptyOrNullString()));

        //Arrays
        Integer[] myArray = {1, 2, 3};
        assertThat(myArray, arrayWithSize(3));
        assertThat(myArray, arrayContaining(1, 2, 3));
        assertThat(myArray, arrayContainingInAnyOrder(2, 3, 1));
    }
}