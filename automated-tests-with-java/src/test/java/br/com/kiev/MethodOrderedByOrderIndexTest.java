package br.com.kiev;

import org.junit.jupiter.api.*;

//@Order(3)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderedByOrderIndexTest {
    StringBuilder actualValue = new StringBuilder();

    @AfterEach
    void afterEach() {
        System.out.println("The actual value is " + actualValue);
    }

    @Test
    @Order(1)
    void testB() {
        System.out.print("Running test B \n");
        actualValue.append("1");
    }

    @Test
    @Order(3)
    void testA() {
        System.out.print("Running test A \n");
        actualValue.append("3");
    }

    @Test
    @Order(2)
    void testC() {
        System.out.print("Running test C \n");
        actualValue.append("2");
    }
}
