package pl.karolsiwek.aoc2021.day21;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Day21a {

    static Integer minX;
    static Integer maxX;

    static Integer minY;
    static Integer maxY;

    public static void main(String[] args) {
        diracGame(4l,8l);
        diracGame(9l,6l);
    }

    private static void diracGame(long playerA, long playerB) {
        Long totalRolls = 0l;
        long aPlace = playerA;
        long bPlace = playerB;
        playerA = 0;
        playerB = 0;
        while(true) {
            if((totalRolls/3) %2==0) {
                for(int i=0;i<3;i++) {
                    aPlace = (aPlace + (totalRolls%100 + 1)-1) %10 + 1;
                    totalRolls++;
                }
                System.out.println("A-" + (aPlace));
                playerA += aPlace;
                System.out.println(playerA);
                if(playerA>=1000) {
                    System.out.println(playerB*totalRolls);
                    return;
                }

            } else {
                for(int i=0;i<3;i++) {

                    bPlace = (bPlace + (totalRolls%100 + 1)-1) %10 + 1;
                    totalRolls++;
                }
                System.out.println("B-" + bPlace);
                playerB += bPlace;
                System.out.println(playerB);
                if(playerB>=1000) {
                    System.out.println(playerA*totalRolls);
                    return;
                }
            }
        }
    }
}
