package fr.norsys.stringcalculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {
    public int add(String numbers){
        if(numbers.isEmpty()) {
            return 0;
        }

        String delim = findDelimeter(numbers);
        DelimeterCleanUpResult res = clean_delimeter(delim);

        delim = res.getDelimeter();

        if(delim.isEmpty()){
            delim = "[,\n]";
        }else{
            numbers = numbers.substring(delim.length()+3+res.getRemovedCharsLen()); // +3 for '//'and'\n'
            if(delim.contains("|")){//multiple delims
                delim = Arrays.stream(delim.split("\\|"))
                        .map(Pattern::quote)
                        .collect(Collectors.joining("|"));
            }else{
                delim = Pattern.quote(delim);
            }
        }

        System.out.println(res.getRemovedCharsLen());
        System.out.println(delim);
        String[] nums_strs = numbers.split(delim);

        IntStream nums = convert_num_arr_to_int_stream(nums_strs);

        if(nums.anyMatch((i)->i < 0)){
            int[] negatives = convert_num_arr_to_int_stream(nums_strs).filter((i)-> i<0).toArray();
            throw new RuntimeException("negatives not allowed : " + Arrays.toString(negatives));
        }

        return convert_num_arr_to_int_stream(nums_strs).sum();
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

    private DelimeterCleanUpResult clean_delimeter(String delim){
        DelimeterCleanUpResult res = new DelimeterCleanUpResult();
        int oldlen = delim.length();

        delim = delim.replaceAll("^\\[", "");
        delim = delim.replaceAll("]$", "");
        delim = delim.replaceAll("]\\[", "|");

        res.setDelimeter(delim);
        res.setRemovedCharsLen(oldlen - res.getDelimeter().length());

        return res;
    }

    private IntStream convert_num_arr_to_int_stream(String[] nums){
        return Arrays.stream(nums)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .filter((i)-> i <= 1000);
    }
}
