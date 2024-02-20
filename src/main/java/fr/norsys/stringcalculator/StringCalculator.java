package fr.norsys.stringcalculator;

import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers){
        if(numbers.isEmpty()) {
            return 0;
        }

        String[] nums_strs = numbers.split(",");

        int sum = Arrays.stream(nums_strs)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .sum();
        return sum;
    }
}
