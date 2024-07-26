package br.com.kiev.math;

import br.com.kiev.SimpleMath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
class SimpleMathTestS4 {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
        System.out.println("Running @BeforeEach method!");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pelé", "Senna", "Shadow Moon"})
    void testValueSource(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }

    @DisplayName("Test Method Source")
    @ParameterizedTest
//    @MethodSource("testDivisionInputParameters")
    @MethodSource()
    void testDivision(Double firstNumber, Double secondNumber, Double expected) {
        System.out.println("Test " + firstNumber + "/" + secondNumber + "= " + expected);

        final var result = math.division(firstNumber, secondNumber);

        assertNotNull(result);
        assertEquals(expected, result, 2D, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }

    //    public static Stream<Arguments> testDivisionInputParameters() {
    public static Stream<Arguments> testDivision() {
        return Stream.of(
                Arguments.of(6.0D, 3.0D, 2.0D),
                Arguments.of(71D, 14D, 5.07D),
                Arguments.of(18.3D, 3.1D, 5.90D)
        );
    }

    @DisplayName("Test Division CSV Source")
    @ParameterizedTest
    @CsvSource({
            "6.2, 2, 3.1",
            "71.4, 14, 5.07",
            "18.3, 3.1, 5.90"
    })
    void testDivisionCsv(Double firstNumber, Double secondNumber, Double expected) {
        System.out.println("Test " + firstNumber + "/" + secondNumber + "= " + expected);

        final var result = math.division(firstNumber, secondNumber);

        assertNotNull(result);
        assertEquals(expected, result, 2D, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }

    @DisplayName("Test Division CSV File Source")
    @ParameterizedTest
    @CsvFileSource(resources = "/testDivision.csv")
    void testDivisionFileCsv(Double firstNumber, Double secondNumber, Double expected) {
        System.out.println("Test " + firstNumber + "/" + secondNumber + "= " + expected);

        final var result = math.division(firstNumber, secondNumber);

        assertNotNull(result);
        assertEquals(expected, result, 2D, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }
}