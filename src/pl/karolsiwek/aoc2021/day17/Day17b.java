package pl.karolsiwek.aoc2021.day17;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static pl.karolsiwek.aoc2021.day15.Day15a.*;

public class Day17b {

    public static void main(String[] args) {

//        Integer maxx = 30;
//        Integer miny = -10;
//
//        Integer targetx = 20;
//        Integer targety = -5;
//
//        Integer maxVy = 9;

        Integer maxx = 262;
        Integer miny = -78;

        Integer targetx = 236;
        Integer targety = -58;

        Integer maxVy = 78;


        Integer sum = 0;
        for (int x = 1; x <= maxx + 1; x++) {
            for (int y = maxVy; y >= miny - 1; y--) {

                int dx = x;
                int dy = y;
                int posX = 0;
                int poxY = 0;

                while (posX <= maxx && poxY >= miny) {
                    if (posX >= targetx && poxY <= targety) {
                        sum++;
                        break;
                    }
                    posX += dx;
                    poxY += dy;

                    dx = Math.max(0, dx - 1);
                    dy--;

                }


            }
        }

        System.out.println(sum);

    }
}
