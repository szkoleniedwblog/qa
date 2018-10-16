package pl.testowy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Parameterized test")

public  class ParamTest {

    @ParameterizedTest
    @DisplayName("First Parameterized test")
    @ValueSource(ints = {5, 15, 25})
    public void parameterizedTest(int number) {
        assertTrue( number%5 == 0);
    }

    @ParameterizedTest
    @DisplayName("Second Parameterized test")
    @ValueSource(strings = {"Hello", "HelloWorld", "HelloCos"})
    public void parameterizedTest2(String text) {
        assertTrue( text.contains("Hello"));
    }

    @ParameterizedTest
    @DisplayName("Third Parameterized test")
    @CsvSource(delimiter = ',', value = {"Hello, 5", "HelloJUnit 5, 15", "'Hello, Junit 5!', 25"})
    public void parameterizedTest3(String param1, int param2) {
        assertTrue( param1.contains("Hello"));
        assertTrue( param2 % 5 == 0);
    }

    @ParameterizedTest
    @DisplayName("Fourth Parameterized test")
    @CsvFileSource(resources = "/Test.csv", delimiter = ',')
    public void parameterizedTest4(String param1, int param2) {
        assertTrue( param1.contains("Hello"));
        assertTrue( param2 % 5 == 0);
    }

    @Test
    public void exceptionTest() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    GamePlay.play(0);
                }
        );
    }

}
