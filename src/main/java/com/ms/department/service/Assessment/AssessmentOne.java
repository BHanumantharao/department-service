package com.ms.department.service.Assessment;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class AssessmentOne {

    public static void main(String[] args) {
        assessmentImpl();
    }

    public static void assessmentImpl(){
        /*
        1. Using Java 8 Map Functional, Stream and Collectors, Create a Java program for the
        following:

        a. Convert string to uppercase and join them with comma(,)
        */
        String[] strArray = {"Convert", "string", "to", "uppercase", "and", "join", "them", "with", "comma"};
        String result = Arrays.stream(strArray).map(String::toUpperCase).collect(Collectors.joining(","));
        log.info("Assessment1 - a - Result: {}", result);

        /*
        b. Create a List with String more than 2 characters and count number of String which
        starts with “a” and count empty string.
        Example of Strings: “abc”, “an”, “”, “apple”, “bcd”, “”, “jk”
        */
        List<String> strList = Arrays.asList("abc","an", "", "apple", "bcd", "", "jk");
        Map<String, Integer> duplicateCountMap = strList.stream()
                .filter(str -> (str.startsWith("a") && str.length() > 2) || str.isEmpty())
                .collect(Collectors.toMap(Function.identity(), str -> 1, Math::addExact));

        log.info("Assessment1 - b - Result: {}", duplicateCountMap);
    }
}
