package pl.karolsiwek.aoc2021.day21;


import pl.karolsiwek.aoc2021.day15.Day15a;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Day21b {

static Map<Game, Pair> results = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(diracGame(new Game(0l, 4l, 8l, 0l, 0l)));
        results = new HashMap<>();
        System.out.println(diracGame(new Game(0, 9l, 6l, 0l, 0l)));
    }

    private static Pair diracGame(Game game) {
        Game game1;
        Game game2;
        Game game3;

        long aScore = game.aScore;
        long bScore = game.bScore;
        long aPlace = game.aPlace;
        long bPlace = game.bPlace;
        long totalRolls = game.totalRolls;
        if(totalRolls%3 ==0 && totalRolls!=0) {
            if((totalRolls/3)%2 == 1) {
                aScore += aPlace;
                if(aScore>=21) {
                    return new Pair(1,0);
                }
            } else {
                bScore +=  bPlace;
                if(bScore>=21) {
                    return  new Pair(0,1);
                }
            }
        }

        if(totalRolls%6<3) {
            game1 = new Game(game.totalRolls + 1, (aPlace+0)%10 + 1, bPlace, aScore, bScore);
            game2 = new Game(game.totalRolls + 1, (aPlace+1)%10 +1, bPlace, aScore, bScore);
            game3 = new Game(game.totalRolls + 1, (aPlace+2)%10 + 1, bPlace, aScore, bScore);
        } else {
            game1 = new Game(game.totalRolls + 1, aPlace, (bPlace+0)%10 + 1, aScore, bScore);
            game2 = new Game(game.totalRolls + 1, aPlace, (bPlace+1)%10 +1,  aScore, bScore);
            game3 = new Game(game.totalRolls + 1, aPlace, (bPlace+2)%10 + 1, aScore, bScore);
        }



        if(!results.containsKey(game1)) {
            results.put(game1, diracGame(game1));
        }
        if(!results.containsKey(game2)) {
            results.put(game2, diracGame(game2));
        }
        if(!results.containsKey(game3)) {
            results.put(game3, diracGame(game3));
        }


        Pair pair1 = results.get(game1);
        Pair pair2 = results.get(game2);
        Pair pair3 = results.get(game3);

        return new Pair(pair1.a + pair2.a + pair3.a, pair1.b  + pair2.b + pair3.b);
    }

    static class Game {

        long totalRolls = 0;
        long aPlace;
        long bPlace;

        long aScore = 0;
        long bScore = 0;

        public Game(long totalRolls, long aPlace, long bPlace, long aScore, long bScore) {
            this.totalRolls = totalRolls;
            this.aPlace = aPlace;
            this.bPlace = bPlace;
            this.aScore = aScore;
            this.bScore = bScore;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Game)) return false;
            Game game = (Game) o;
            return totalRolls == game.totalRolls && aPlace == game.aPlace && bPlace == game.bPlace && aScore == game.aScore && bScore == game.bScore;
        }

        @Override
        public int hashCode() {
            return Objects.hash(totalRolls, aPlace, bPlace, aScore, bScore);
        }
    }


    static class Pair {

        public Pair(long a, long b) {
            this.a = a;
            this.b = b;
        }

        long a;
        long b;

        @Override
        public String toString() {
            return "Pair{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}
