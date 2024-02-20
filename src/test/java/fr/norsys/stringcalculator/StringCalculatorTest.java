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
    @ValueSource(strings = {"1", "222", "33"})
    @DisplayName("Should return the passed number when passed a number")
    public void should_return_number_when_passed_a_number(String num){
        Assertions.assertEquals(Integer.parseInt(num), calc.add(num));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2', 3",
            "'1,5', 6"
    })
    @DisplayName("Should add two numbers")
    public void should_add_two_numbers(String nums, int expected){
        Assertions.assertEquals(expected, calc.add(nums));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2', 3",
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
            "'//;\n1;5', 6",
    })
    @DisplayName("Should support different delimeters")
    public void should_support_multiple_delimeters(String nums, int expected){
        Assertions.assertEquals(expected, calc.add(nums));
    }

    @ParameterizedTest
    @CsvSource({
            "'-1, 1, 22', '-1'",
            "'-1, 0, -5', '-1, -5'",
            "'-133, -1',  '-133, -1'"})
    @DisplayName("Should throw when called with negatives")
    public void should_throw_when_called_with_negatives(String input, String negatives){
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->calc.add(input));
        System.out.print(exception.toString());
        Assertions.assertEquals("negatives not allowed : ["+negatives+"]", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "'1\n2200', 1",
            "'1\n1, 1001,1', 3",
            "'1,1,2,5000,1', 5",
    })
    @DisplayName("Should ignore numbers bigger than 1000")
    public void should_ignore_numbers_bigger_than_1000(String nums, int expected){
        Assertions.assertEquals(expected, calc.add(nums));
    }

    @Test
    @DisplayName("should accept delimeters of any length")
    public void should_accept_delimeters_of_any_length(){
        Assertions.assertEquals(6, calc.add("//[***]\n1***5"));
    }

    @Test
    @DisplayName("Should allow multiple delimeters")
    public void should_accept_multiple_delimeters(){
        Assertions.assertEquals(9, calc.add("//[***][:]\n1***5:3"));
    }

    @Test
    @DisplayName("Should allow multiple delimeters with length > 1 char")
    public void should_accept_multiple_delimeters_with_more_than_1_char(){
        Assertions.assertEquals(9, calc.add("//[***][???]\n1***5???3"));
    }

}
