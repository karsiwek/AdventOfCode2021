package pl.karolsiwek.aoc2021.day06;


import java.util.*;
import java.util.stream.Collectors;

public class Day06b {

    public static void main(String[] args) {

        Map<Integer, Long> result = getResult(readInput(testInput), 80);;
        System.out.println(result.entrySet().stream().map(e -> e.getValue()).reduce((a,b) -> a+b));

        result = getResult(readInput(mainInput), 80);;
        System.out.println(result.entrySet().stream().map(e -> e.getValue()).reduce((a,b) -> a+b));

        result = getResult(readInput(testInput), 256);;
        System.out.println(result.entrySet().stream().map(e -> e.getValue()).reduce((a,b) -> a+b));

        result = getResult(readInput(mainInput), 256);;
        System.out.println(result.entrySet().stream().map(e -> e.getValue()).reduce((a,b) -> a+b));
    }

    private static Map<Integer, Long> readInput(String input) {
        List<Integer> list = Arrays.stream(input.split(",")).map(el -> Integer.parseInt(el)).collect(Collectors.toList());
        Map<Integer, Long> result = new HashMap<>();
        for(int i=0;i<=8;i++){
            result.put(i, 0l);
        }

        for(Integer el : list) {
            result.put(el, result.get(el) + 1);
        }

        return result;
    }

    static Map<Integer, Long> getResult(Map<Integer, Long> fish, Integer n) {
        if (n == 0) return fish;

        Map<Integer, Long> result = new HashMap<>();
        for(int i=0;i<=8;i++){
            result.put(i, 0l);
        }

        for(int i=1;i<=8;i++) {


                result.put(i-1, result.get(i)+fish.get(i));

        }

            result.put(6, result.get(6) + fish.get(0));
            result.put(8, fish.get(0));

        return getResult(result, n-1);

    }

    static String testInput = "3,4,3,1,2";



    static String mainInput = "1,3,4,1,5,2,1,1,1,1,5,1,5,1,1,1,1,3,1,1,1,1,1,1,1,2,1,5,1,1,1,1,1,4,4,1,1,4,1,1,2,3,1,5,1,4,1,2,4,1,1,1,1,1,1,1,1,2,5,3,3,5,1,1,1,1,4,1,1,3,1,1,1,2,3,4,1,1,5,1,1,1,1,1,2,1,3,1,3,1,2,5,1,1,1,1,5,1,5,5,1,1,1,1,3,4,4,4,1,5,1,1,4,4,1,1,1,1,3,1,1,1,1,1,1,3,2,1,4,1,1,4,1,5,5,1,2,2,1,5,4,2,1,1,5,1,5,1,3,1,1,1,1,1,4,1,2,1,1,5,1,1,4,1,4,5,3,5,5,1,2,1,1,1,1,1,3,5,1,2,1,2,1,3,1,1,1,1,1,4,5,4,1,3,3,1,1,1,1,1,1,1,1,1,5,1,1,1,5,1,1,4,1,5,2,4,1,1,1,2,1,1,4,4,1,2,1,1,1,1,5,3,1,1,1,1,4,1,4,1,1,1,1,1,1,3,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,5,1,2,1,1,1,1,1,1,1,1,1";

}
