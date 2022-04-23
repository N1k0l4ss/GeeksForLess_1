package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalcValidatorTest {
    CalcValidator validator;
    @BeforeEach
    void SetUp(){
        validator = new CalcValidator();
    }

    @Test
    void staplesIsValidTest(){
        assertFalse(validator.staplesIsValid("((5+3)"), "Wrong actual 1");
        assertFalse(validator.staplesIsValid("((5+3"), "Wrong actual 2");
        assertTrue(validator.staplesIsValid("((5+3))"), "Wrong actual 3");
        assertFalse(validator.staplesIsValid("(5+3))"), "Wrong actual 4");
        assertTrue(validator.staplesIsValid("(5+3)"), "Wrong actual 5");
        assertTrue(validator.staplesIsValid("5+6"), "Wrong actual 6");
    }

    @Test
    void testInputNormalizer(){
        String example1 = "((5 + 3) * 2 + (5 - 10))";
        String example2 = "((5 + 3) * 3 + (5 - 10))";
        String example3 = "((5 + 3) ** 3 + (5 - 10))";
        String example4 = "((5 + 3) * 3abc ++ (5 - -10))==";
        String example5 = "((5 + 3) * -3 +* (5 - -10))";

        String expected1 = "((5+3)*2+(5-10))";
        String expected2 = "((5+3)*3+(5-10))";
        String expected3 = "((5+3)*3+(5-10))";
        String expected4 = "((5+3)*3+(5--10))";
        String expected5 = "((5+3)*-3+(5--10))";

        assertEquals(expected1, validator.inputNormalizer(example1), "Wrong actual 1");
        assertEquals(expected2, validator.inputNormalizer(example2), "Wrong actual 2");
        assertEquals(expected3, validator.inputNormalizer(example3), "Wrong actual 3");
        assertEquals(expected4, validator.inputNormalizer(example4), "Wrong actual 4");
        assertEquals(expected5, validator.inputNormalizer(example5), "Wrong actual 5");
    }

    @Test
    void testNumberCounter(){
        String example1 = "((5 + 3) * 2 + (5 - 10))";
        String example2 = "5 + 3 + 9 * 22 / 33 + 487";
        String example3 = "4 * 5 + 33 * 22";
        String example4 = "((5 + 3) * 3abc ++ (5 - -10))==";
        String example5 = "78.94 + 237 / 3 * 35";

        int expected1 = 5;
        int expected2 = 6;
        int expected3 = 4;
        int expected4 = 5;
        int expected5 = 4;

        assertEquals(expected1, validator.numberCounter(example1), "Wrong actual 1");
        assertEquals(expected2, validator.numberCounter(example2), "Wrong actual 2");
        assertEquals(expected3, validator.numberCounter(example3), "Wrong actual 3");
        assertEquals(expected4, validator.numberCounter(example4), "Wrong actual 4");
        assertEquals(expected5, validator.numberCounter(example5), "Wrong actual 5");
    }
}