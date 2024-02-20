package fr.norsys.stringcalculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers){
        if(numbers.isEmpty()) {
            return 0;
        }

        String delim = findDelimeter(numbers);
        if(delim.isEmpty()){
            delim = "[,\n]";
        }else{
            numbers = numbers.substring(delim.length()+3); // +3 for '//'and'\n'
        }

        String[] nums_strs = numbers.split(delim);

        int sum = Arrays.stream(nums_strs)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .sum();
        return sum;
    }

    private String findDelimeter(String input){
        Pattern pattern = Pattern.compile("//(.*)\n");
        Matcher matcher = pattern.matcher(input);

        if(matcher.find()){
            return matcher.group(1);
        }else {
            return "";
        }
    }
}
