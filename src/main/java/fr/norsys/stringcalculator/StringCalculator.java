package fr.norsys.stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {
    public int add(String numbers){
        if(numbers.isEmpty()) {
            return 0;
        }

        String delim = extract_delimeter_regex(numbers);

        if(delim.isEmpty()){
            delim = "[,\n]";
        }else{
            numbers = numbers.split("\n", 2)[1];
        }

        String[] nums_strs = numbers.split(delim);

        IntStream nums = convert_num_arr_to_int_stream(nums_strs);

        if(nums.anyMatch((i)->i < 0)){
            int[] negatives = convert_num_arr_to_int_stream(nums_strs).filter((i)-> i<0).toArray();
            throw new RuntimeException("negatives not allowed : " + Arrays.toString(negatives));
        }

        return convert_num_arr_to_int_stream(nums_strs).sum();
    }

    private IntStream convert_num_arr_to_int_stream(String[] nums){
        return Arrays.stream(nums)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .filter((i)-> i <= 1000);
    }


    public String extract_delimeter_regex(String input){
        Pattern pattern = null;
        if(!input.contains("[")) {
            pattern = Pattern.compile("//(.*)\n");
        }else{
            pattern = Pattern.compile("\\[([^\\[\\]]+)]");
        }
        Matcher matcher = pattern.matcher(input);

        List<String> delimeters = new ArrayList<>();
        while (matcher.find()){
            delimeters.add(matcher.group(1));
        }

        return delimeters.stream().map(Pattern::quote).collect(Collectors.joining("|"));
    }
}
