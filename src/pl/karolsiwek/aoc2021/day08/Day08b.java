package pl.karolsiwek.aoc2021.day08;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.karolsiwek.aoc2021.day08.Day08a.*;

public class Day08b {

    static Map<String, Integer> digits = Map.of(
            "abcefg", 0,                //?6
            "cf", 1,            //ok
            "acdeg", 2,                 //?5    //sorted
            "acdfg", 3,                 //?5   //sorted
            "bcdf", 4,          //ok
            "abdfg", 5,                 //?5    //sorted
            "abdefg", 6,                //?6
            "acf", 7,           //ok
            "abcdefg", 8,       //ok
            "abcdfg", 9);               //?6    //sted

    static Map<String, Integer> digitsSorted = new HashMap<>();
    static Map<Integer, List<Integer>> digitByLength = getDigitsByLength();
    ;

    public static void main(String[] args) {
        Integer sum = 0;
        for (String line : mainInput.split("\n")) {
            Map<String, Integer> codeForLine = getCodeForLine(line);

            //veryfyiung codes
            for (int i = 0; i < 10; i++) {
                if (codeForLine.values().contains(i)) continue;
                System.out.println("NOT HAVING - " + i);
            }
            String[] output = line.split("\\|");

            Integer number = 0;

            for (String digit : line.trim().split(" ")) {
                if(digit.trim().equals("|")) {
                    System.out.print("|");
                } else {
                    System.out.print(getDigit(digit, codeForLine));
                }

            }
            System.out.println();

            for (String digit : output[1].trim().split(" ")) {
                number *= 10;
                number += getDigit(digit, codeForLine);

            }

          //  System.out.println(number);
            sum += number;
        }

        System.out.println(sum);

        //not 1014552 - too high
        //not 1014052 - too high
        //not 1004552 - too low
        //not 1010552
        //1009098 - uhhhhh
    }

    private static Map<String, Integer> getCodeForLine(String line) {
        Map<String, Integer> results = new HashMap<>();

        String[] digitsInLine = line.split(" ");
        for (String digitInLikne : digitsInLine) {
            if (digitInLikne == "|") continue;

            if (getDigitByLength(digitInLikne.length()) >= 0) {
                results.put(Arrays.stream(digitInLikne.split("")).sorted().collect(Collectors.joining()), getDigitByLength(digitInLikne.length()));
            }
        }


        // sorting 2,3,5
        List<String> candidates = getCandidates(digitsInLine, 5);
        //only 2 and 5 gives 8
        String threeCode = null;
        if(results.getOrDefault(sum(candidates.get(0), candidates.get(1)), -1) == 8) {
            threeCode = candidates.get(2);
        } else         if(results.getOrDefault(sum(candidates.get(1), candidates.get(2)),-1) == 8) {
            threeCode = candidates.get(0);
        } else {
            threeCode = candidates.get(1);
        }
        candidates.remove(threeCode); //now it's 2 or 5
        results.put(threeCode, 3);

        results.put(sum(threeCode, getCode(4, results)), 9);

        if(getDigit(sum(candidates.get(0), getCode(3, results)), results) == 9) {
            results.put(candidates.get(0), 5);
            results.put(candidates.get(1), 2);
        } else if(getDigit(sum(candidates.get(1), getCode(3, results)), results) == 9) {
            results.put(candidates.get(1), 5);
            results.put(candidates.get(0), 2);
        }else {
            throw new IllegalStateException();
        }

        results.put(sum(threeCode, getCode(4, results)), 9);

        //omly 0 and 6 is left
        candidates = new ArrayList<>();
        for(String candidate : getCandidates(digitsInLine, 6)) {
            if(!results.containsKey(candidate)){
                candidates.add(candidate);
            }
        }

        if (results.getOrDefault(sum(candidates.get(0), getCode(5, results)), -1) == 8) {
            results.put(candidates.get(0), 0);
            results.put(candidates.get(1), 6);
        } else if (results.getOrDefault(sum(candidates.get(1), getCode(5, results)), -1) == 8) {
            results.put(candidates.get(1), 0);
            results.put(candidates.get(0), 6);
        } else {
            throw new IllegalStateException();
        }

        return results;
    }

    private static List<String> getCandidates(String[] digitsInLine, int i) {
        List<String> result = new ArrayList<>();
        for(String digit: digitsInLine) {
            if(digit.length()==i) {
                result.add(Arrays.stream(digit.split("")).sorted().collect(Collectors.joining()));
            }
        }
        return result.stream().collect(Collectors.toSet()).stream().sorted().collect(Collectors.toList());
    }

    private static String getCode(Integer integer, Map<String, Integer> results) {
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if(entry.getValue() == integer) {
                return entry.getKey();
            }
        }
        return null;
    }

    static String sum(String a, String b) {
        return Stream.concat(Arrays.stream(a.split("")), Arrays.stream(b.split(""))).collect(Collectors.toSet()).stream().sorted().collect(Collectors.joining());
    }

    static Integer getDigit(String code, Map<String, Integer> digits) {
        return digits.getOrDefault(Arrays.stream(code.split("")).collect(Collectors.toSet()).stream().sorted().collect(Collectors.joining()),-1);
    }

    static Integer getDigit(String code) {
        return getDigit(code, digitsSorted);
    }


}
