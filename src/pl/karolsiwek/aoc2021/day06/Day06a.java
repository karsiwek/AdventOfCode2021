package pl.karolsiwek.aoc2021.day06;


import java.util.*;
import java.util.stream.Collectors;

public class Day06a {

    public static void main(String[] args) {

//        List<Integer> boards = getResult(readInput(testInput), 80);;
//        System.out.println(boards.size());

        List<Integer> boards = getResult(readInput(mainInput), 80);;
        System.out.println(boards.size());
    }

    private static List<Integer> readInput(String input) {
        return Arrays.stream(input.split(",")).map(el -> Integer.parseInt(el)).collect(Collectors.toList());
    }

    static List<Integer> getResult(List<Integer> fish, Integer n) {
        if(n%8==0) {
            System.out.println(n + "\t" + fish.size());
        }
        if (n == 0) return fish;

        ArrayList<Integer> result = new ArrayList<>(fish);

        for(int i=0;i<fish.size();i++) {
            result.remove(i);
            if (fish.get(i) == 0) {
                result.add(i, 6);
                result.add(8);
            } else {
                result.add(i, fish.get(i)-1);
            }
        }

        return getResult(result, n-1);

    }

    static String testInput = "3,4,3,1,2";



    static String mainInput = "1,3,4,1,5,2,1,1,1,1,5,1,5,1,1,1,1,3,1,1,1,1,1,1,1,2,1,5,1,1,1,1,1,4,4,1,1,4,1,1,2,3,1,5,1,4,1,2,4,1,1,1,1,1,1,1,1,2,5,3,3,5,1,1,1,1,4,1,1,3,1,1,1,2,3,4,1,1,5,1,1,1,1,1,2,1,3,1,3,1,2,5,1,1,1,1,5,1,5,5,1,1,1,1,3,4,4,4,1,5,1,1,4,4,1,1,1,1,3,1,1,1,1,1,1,3,2,1,4,1,1,4,1,5,5,1,2,2,1,5,4,2,1,1,5,1,5,1,3,1,1,1,1,1,4,1,2,1,1,5,1,1,4,1,4,5,3,5,5,1,2,1,1,1,1,1,3,5,1,2,1,2,1,3,1,1,1,1,1,4,5,4,1,3,3,1,1,1,1,1,1,1,1,1,5,1,1,1,5,1,1,4,1,5,2,4,1,1,1,2,1,1,4,4,1,2,1,1,1,1,5,3,1,1,1,1,4,1,4,1,1,1,1,1,1,3,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,5,1,2,1,1,1,1,1,1,1,1,1";

}
