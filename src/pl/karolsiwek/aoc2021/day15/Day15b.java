package pl.karolsiwek.aoc2021.day15;


import java.util.*;
import java.util.stream.Collectors;

import static pl.karolsiwek.aoc2021.day15.Day15a.*;

public class Day15b {

    public static void main(String[] args) {
        Map<Day15a.Pair, Day15a.Tile> floor = new HashMap<>();
        String[] lines = mainInput.split("\n");
        for (int y = 0; y < lines.length; y++) {
            String[] line = lines[y].split("");
            for (int x = 0; x < line.length; x++) {
                floor.put(new Day15a.Pair(x, y), new Day15a.Tile(Integer.parseInt(line[x])));
            }
        }

        Map<Day15a.Pair, Day15a.Tile> newFloor = new HashMap<>();
        int sizeX = floor.keySet().stream().map(key -> key.x).max(Comparator.naturalOrder()).get() +1;
        int sizeY = floor.keySet().stream().map(key -> key.y).max(Comparator.naturalOrder()).get()+1;

        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                for(Day15a.Pair pair : floor.keySet()) {
                    newFloor.put(new Day15a.Pair(pair.x + i*sizeX, pair.y + j*sizeY), new Day15a.Tile((floor.get(pair).risk+i+j-1)%9+1));
                }

            }
        }
        newFloor.get(new Day15a.Pair(0, 0)).setTotalRisk(0);

        System.out.println(getShortestPath(newFloor));


    }

}
