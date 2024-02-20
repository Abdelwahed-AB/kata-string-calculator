package fr.norsys.stringcalculator;

import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers){
        if(numbers.length() == 0) {
            return 0;
        }

        String nums_strs[] = numbers.split(",");
        if(nums_strs.length > 2){
            throw new RuntimeException("Cannot perform operation on more than 2 numbers");
        }else if(nums_strs.length == 1){
            return Integer.parseInt(nums_strs[0]);
        }

        return Integer.parseInt(nums_strs[0]) + Integer.parseInt(nums_strs[1]);
    }
}
