package fr.norsys.stringcalculator;

public class Main {
    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();

        System.out.println(calculator.extract_delimeter_regex("1, 2"));
    }
}
