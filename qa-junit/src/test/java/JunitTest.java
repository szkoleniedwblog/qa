import org.junit.After;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("To jest nasza klasa testowa")

public class JunitTest {

    @BeforeEach
    void before() {
        System.out.println("Before --------- ");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll --------- ");
    }

    @AfterEach
    void after() {
        System.out.println("After --------- ");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll --------- ");
    }

    // Pierwszy test za tym poleceniem sie powtorzy taka ilosc razy
    @RepeatedTest(5)

    @Test
    @DisplayName("To jest nasz pierwszy test")
    public void firstTest() {
        System.out.println(0.2 * 0.2);
//        assertTrue( (0.2 * 0.2) == 0.04);
        assertTrue( new BigDecimal("0.2").multiply(new BigDecimal("0.2")).doubleValue() == 0.04);
    }

    String testowyString = "testowyString";

    @Test
    @DisplayName("To jest nasz drugi test")
    public void testStringa() {
        assertEquals("testowyString", testowyString);

        assertThat(testowyString, containsString("String"));
        assertThat(testowyString, equalTo("testowyString"));
        assertThat(testowyString, endsWith("String"));

    }

    @Test
    @DisplayName("To jest nasz trzeci test")
    public void testStringa2() {

        assertAll(() -> {
            assertEquals("testowyString", testowyString);
            assertThat(testowyString, containsString("String"));
            assertThat(testowyString, equalTo("testowyString"));
            assertThat(testowyString, endsWith("String"));

                }

        );

    }
}
