package br.com.kiev.math;

import br.com.kiev.SimpleMath;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
class SimpleMathTestS3 {

    SimpleMath math;

    @BeforeAll
    static void setup() {
        System.out.println("Running @BeforeAll method!");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Running @AfterAll method!");
    }

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
        System.out.println("Running @BeforeEach method!");
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("Running @AfterEach method!");
    }

    @Test
    @DisplayName("Test 5 + 3 = 8")
    void testSum_When_FiveAddThree_ShouldReturnEight() {
        System.out.println("Test 5 + 3 = 8");
        // AAA Arrange, Act Assert
        // Given //Arrange
        final var firstNumber = 5.0D;
        final var secondNumber = 3.0D;
        final var expected = 8.0D;
        //When // Act
        final var result = math.sum(firstNumber, secondNumber);
        //Then // Assert
        assertNotNull(result);
        assertEquals(expected, result, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 5 - 3 = 2")
    void testSubtraction() {
        System.out.println("Test 5 - 3 = 2");
        final var firstNumber = 5.0D;
        final var secondNumber = 3.0D;

        final var result = math.subtraction(firstNumber, secondNumber);
        final var expected = 2.0D;

        assertNotNull(result);
        assertEquals(expected, result, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 5 * 3 = 15")
    void testMultiplication() {
        System.out.println("Test 5 * 3 = 15");
        final var firstNumber = 5.0D;
        final var secondNumber = 3.0D;

        final var result = math.multiplication(firstNumber, secondNumber);
        final var expected = 15.0D;

        assertNotNull(result);
        assertEquals(expected, result, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 5 / 3 = 2")
    void testDivision() {
        System.out.println("Test 5 / 3 = 2");
        final var firstNumber = 6.0D;
        final var secondNumber = 3.0D;

        final var result = math.division(firstNumber, secondNumber);
        final var expected = 2.0;

        assertNotNull(result);
        assertEquals(expected, result, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test Division By Zero")
    void testDivision_WhenFirstNumberDivideZero_ShouldReturnArithmeticException() {
        System.out.println("Test Division By Zero");
        //given
        final var firstNumber = 0.0D;
        final var secondNumber = 3.0D;

        //when & then
        final var exception = assertThrows(ArithmeticException.class, () -> {
                    math.division(firstNumber, secondNumber);
                },
                () -> "Division by zero should throw an ArithmeticException");
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    @DisplayName("Test (5 + 3)/2 = 4")
    void testMean() {
        System.out.println("Test (5 + 3)/2 = 4");
        final var firstNumber = 5.0D;
        final var secondNumber = 3.0D;

        final var result = math.mean(firstNumber, secondNumber);
        final var expected = 4.0D;

        assertNotNull(result);
        assertEquals(expected, result, () -> "(" + firstNumber + " + " + secondNumber + ")/2" + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test Square Root of 16 = 4")
    void testSquareRoot() {
        System.out.println("Test Square Root of 16 = 4");
        final var number = 16.0D;

        final var result = math.squareRoot(number);
        final var expected = 4.0D;

        assertEquals(expected, result, () -> " The sqaureroot of " + number + " did not produce " + expected + "!");
    }
}