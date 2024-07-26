package br.com.kiev;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArraysCompareTest {

    @Test
    void test() {
        int[] numbers = {25, 8, 21, 32, 3};
        int[] expectedArray = {3, 8, 21, 25, 32};

        Arrays.sort(numbers);

        assertArrayEquals(numbers, expectedArray);
    }

    @Test
    @Timeout(1)
    void testSortPerfomance() {
        int[] numbers = {25, 8, 21, 32, 3};

        for (int i = 0; i < 10000000; i++) {
            numbers[0] = i;
            Arrays.sort(numbers);
        }
    }
}
