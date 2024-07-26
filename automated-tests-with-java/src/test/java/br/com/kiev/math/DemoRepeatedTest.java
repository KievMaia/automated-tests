package br.com.kiev.math;

import br.com.kiev.SimpleMath;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
class DemoRepeatedTest {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
        System.out.println("Running @BeforeEach method!");
    }

    @RepeatedTest(value = 3, name = "{displayName}. Repetition " + "{currentRepetition} of {totalRepetitions}")
    @DisplayName("Test Division By Zero")
    void testDivision_WhenFirstNumberDivideZero_ShouldReturnArithmeticException(RepetitionInfo repetitionInfo,
                                                                                TestInfo testInfo) {
        System.out.println("Repetition Nº" + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        System.out.println("Running " + testInfo.getTestMethod().get().getName());
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
}