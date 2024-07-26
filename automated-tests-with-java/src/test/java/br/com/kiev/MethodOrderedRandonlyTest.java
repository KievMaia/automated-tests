package br.com.kiev;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@Order(1)
@TestMethodOrder(MethodOrderer.Random.class)
class MethodOrderedRandonlyTest {
    @Test
    void testA() {
        System.out.print("Running test A \n");
    }

    @Test
    void testB() {
        System.out.print("Running test B \n");
    }

    @Test
    void testC() {
        System.out.print("Running test C \n");
    }
}