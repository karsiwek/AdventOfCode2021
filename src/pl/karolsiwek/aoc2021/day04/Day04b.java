package pl.karolsiwek.aoc2021.day04;


import java.util.*;
import java.util.stream.Collectors;

import static pl.karolsiwek.aoc2021.day04.Day04a.*;

public class Day04b {

    public static void main(String[] args) {

        List<Day04a.BingoBoard> boards = readBoards(mainInput);;
        List<Integer> numbers = readNumbers(mainInput);

        System.out.println(getScoreOfLastBoard(boards, numbers));
    }

    static Integer getScoreOfLastBoard(List<Day04a.BingoBoard> boards, List<Integer> numbers) {
        List<BingoBoard> oldBoards = new ArrayList<>(boards);
        for(Integer number : numbers) {
            List<BingoBoard> newBoards = new ArrayList<>(oldBoards);
            for(BingoBoard board : oldBoards) {
                if(board.mark(number)) {
                    if(newBoards.size()==1) {
                        return board.getSum()*number;
                    } else {
                        newBoards.remove(board);
                    }
                }
            }
            oldBoards = new ArrayList<>(newBoards);
        }
        return 0;
    }

}
