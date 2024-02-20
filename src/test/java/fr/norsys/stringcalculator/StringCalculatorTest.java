package fr.norsys.stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringCalculatorTest {

    StringCalculator calc = new StringCalculator();

    @Test
    @DisplayName("Should return 0 when passed an empty string")
    public void should_return_0_when_passed_empty_str() {
        Assertions.assertEquals(0, calc.add(""));
    }

//    @Test
//    @DisplayName("Should throw when passed more than 2 numbers")
//    public void should_throw_when_passed_more_than_2_numbers() {
//        Assertions.assertThrows(RuntimeException.class, () ->
//                calc.add("1,2,3"));
//    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2222", "33"})
    @DisplayName("Should return the passed number when passed a number")
    public void should_return_number_when_passed_a_number(String num){
        Assertions.assertEquals(Integer.parseInt(num), calc.add(num));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2', 3",
            "'-1,5', 4"
    })
    @DisplayName("Should add two numbers")
    public void should_add_two_numbers(String nums, int expected){
        Assertions.assertEquals(expected, calc.add(nums));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2', 3",
            "'-1,5', 4",
            "'-1', -1",
            "'1,1, 1,1', 4",
            "'1,1,2,5,1', 10",
    })
    @DisplayName("Should handle an unknown amount of numbers")
    public void should_handle_an_unknown_amount_of_numbers(String nums, int expected){
        Assertions.assertEquals(expected, calc.add(nums));
    }

    @ParameterizedTest
    @CsvSource({
            "'1\n2', 3",
            "'-1,5', 4",
            "'-1', -1",
            "'1\n1, 1,1', 4",
            "'1,1,2,5,1', 10",
    })
    @DisplayName("Should handle new lines")
    public void should_handle_new_lines(String nums, int expected){
        Assertions.assertEquals(expected, calc.add(nums));
    }

    @Test
    @DisplayName("Should throw when input is incorrect")
    public void should_throw_when_input_is_incorrect(){
        Assertions.assertThrows(RuntimeException.class, ()->calc.add("1,\n2"));
    }

    @ParameterizedTest
    @CsvSource({
            "'//:\n1:2', 3",
            "'//;\n-1;5', 4",
    })
    @DisplayName("Should support different delimeters")
    public void should_support_multiple_delimeters(String nums, int expected){
        Assertions.assertEquals(expected, calc.add(nums));
    }
}
