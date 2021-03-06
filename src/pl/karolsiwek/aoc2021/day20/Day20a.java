package pl.karolsiwek.aoc2021.day20;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Day20a {

    static Integer minX;
    static Integer maxX;

    static Integer minY;
    static Integer maxY;

    public static void main(String[] args) {
        String input = mainInput.replace(".", "0").replace("#", "1");

        Map<Pair, Integer> floor = new HashMap<>();
        String[] lines = input.split("\n");

        String code = lines[0];
        for (int y = 2; y < lines.length; y++) {
            String[] line = lines[y].split("");
            for (int x = 0; x < line.length; x++) {
                floor.put(new Pair(x, y - 2), Integer.parseInt(line[x]));
            }
        }
        minX = floor.keySet().stream().map(e -> e.x).min(Comparator.naturalOrder()).get() - 100;
        maxX = floor.keySet().stream().map(e -> e.x).max(Comparator.naturalOrder()).get() + 100;

        minY = floor.keySet().stream().map(e -> e.y).min(Comparator.naturalOrder()).get() - 100;
        maxY = floor.keySet().stream().map(e -> e.y).max(Comparator.naturalOrder()).get() + 100;

        for (int i = 0; i < 2; i++) {
            floor = enchance(code, floor);
            printFloor(floor);
        }


        Integer nb = Math.toIntExact(floor.values().stream().filter(el -> el == 1).count());

        System.out.println(nb);
        // 5569 too high


        System.out.println(nb - 2 * (maxX - minX) - 2 * (maxY - minY - 2));

        // 5650 - too high

        countItems(floor);
    }

    private static void countItems(Map<Pair, Integer> floor) {
        Integer sum = 0;
        for (int j = minY + 2; j <= maxY - 2; j++) {
            for (int i = minX + 2; i <= maxX - 2; i++) {
                sum += floor.get(new Pair(i, j));
            }
        }

        System.out.println(sum);
    }

    private static Map<Pair, Integer> enchance(String code, Map<Pair, Integer> floor) {
        Map<Pair, Integer> result = new HashMap<>();


        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                String input = "";
                for (int l = -1; l <= 1; l++) {
                    for (int k = -1; k <= 1; k++) {

                        input += floor.getOrDefault(new Pair(i + k, j + l), 0).toString();
                    }
                }
                result.put(new Pair(i, j), enchanced(code, input));
            }
        }
        return result;
    }

    private static Integer enchanced(String code, String input) {
        Integer idx = Integer.parseInt(input, 2);

        return Integer.parseInt(code.substring(idx, idx + 1));
    }

    private static void printFloor(Map<Pair, Integer> floor) {
        for (int j = minY; j <= maxY; j++) {
            for (int i = minX; i <= maxX; i++) {
                System.out.print(floor.get(new Pair(i, j))==1? "#": " ");
            }
            System.out.println();
        }
    }

    static class Pair {
        Integer x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return Objects.equals(x, pair.x) && Objects.equals(y, pair.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static String testInput = "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##" +
            "#..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###" +
            ".######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#." +
            ".#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#....." +
            ".#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.." +
            "...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#....." +
            "..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#\n" +
            "\n" +
            "#..#.\n" +
            "#....\n" +
            "##..#\n" +
            "..#..\n" +
            "..###";


    static String mainInput = "#.#..#..####....##.##..#.#.###..###..######.#..#####.#..###..#.####.#..###.##..#.......##....###...#####....#.##..#.#.#...###..#.####.##.#.###...#.###.##.#..#.#..#....#.#.#...#...#.#....###.#.#.#....###.##..#.##...##.#..##...##########.####....#..##..###.#..###..#.#...########.#.##.##...##....#.#..####.###....#.....#.##.#.##.......#....###......###..#.###.#...######..###..#..#.....##.###..##.###....##..#..#..##..##.#.###.#.#.#...#.#####.....#.##.....#..####.###.#.#.######.###.....##.#...#.###..#####...##...\n" +
            "\n" +
            "#.#....###...###.#.######.#..#.####.#..#..#####.#..###.#..##..####....#####..#.#......##..#.##...#..\n" +
            ".#.........#..##.####.##...####.###.##.##.#.##..##........##.##.#....###..###.#.##...#.#.#.##...#.#.\n" +
            ".###...#...#.#...#.##......#..#.#.#####..........#..#.##.#..#.#.##.###.##..........####.#.#.###...#.\n" +
            "##.#.#.#.######.##..####.####.##.#..##..#####..##.#..######.#..##..####.####.##.##....#.#.#.........\n" +
            "####.#.#.######....##.##..#..#...#.##.##.##..#..#.##.##.....##.#....####.###...#...##.##.#..#.#.####\n" +
            "..##..##...#..##.#.##....##..#####.#.#....#.##.###....#.##.##..####..#.#.###.#.#.....##.##.##.......\n" +
            "...#...#.######.#..##.##...#....##.##.###....###.#..#.#.#..##...#.#...#.#.#.....#..##....#..#.##...#\n" +
            ".....#....##..#.##.#.###....#.#..###.#..##..####..#..###..##.#.#.#..###..#.#.#.#..#.###.#.#..##..#..\n" +
            "###...##.##....#..##..##.#.#.##.#...####.##.#.#.#.#.#.#.#.#.#...##.#####.#...###.#..#..#########.#.#\n" +
            "####..##....##.#..#..#.##.####.##...#...##..#####...#.#.#.#..####.#..#..##.....##..##...##.##..###.#\n" +
            "..#.#...####....####.#..#.##...#..##.#####....###.#.##.####.####.######.#.####.###..##.##...#.#.#..#\n" +
            ".#.#.#..###############...##.###########...#.#..#..##.#...##..#.#.#####.###.##..###..#####...#.#.#..\n" +
            "...##...##.######.#..##..#..####......#.###..####.###..##..#.#.#..##...##.#.##.##.#..##.#.##..##..#.\n" +
            "#####..##.##.######...#.##..#....###.###..#.#.###..#.####.###.#...#.#.....##############.#.##.......\n" +
            ".##.#..####..##.#####.#.#...#.....##..#.###.....#..##.##..#.#...##..##...#.#.##...##.......##..#.#.#\n" +
            "#####.##.##...#..#..#.#.##.#...#...#.##.....#.###.#...#..##.##.#...####...#..##..##...#...###.##..#.\n" +
            "#.#####..###..######..##...#..###.#####..#.##.#.#.##..##.#.##..##..#...#.##.##.##.#.#.###..###....#.\n" +
            "#...#....##.###.#...##.#..#..#.#..#..####.#....#...#.##.....#.....#....##.###.##.#.####.#.#...##....\n" +
            "#.#....##..###.#..##....#.......#.#.#####....##..#.#..#..#..#..#..##.#..#.....##.##.#.####..#.##.##.\n" +
            "##...###.#...######..#.#.#.#.##.#.#####..#.#.##.##.#....#.#.###.#.#.#.#...###.....##.........#..#.##\n" +
            "...###..#.##.###..####.###...#.#.##..#.####....##..####.##...#..#.#.#..#..#.##..###.##.#.#...#...#.#\n" +
            "#.####.....#.#..##..###.#...###.##...##..#..##...#.#..###.##..#...##.#...#.#.#..###..##..###.#..#..#\n" +
            "..##.###.#####.####.#.#.....###........#..#.##########..#....#..#....#.#.###.##...##..###..#.#.###..\n" +
            "#.#..#...#.#.#.###.#####..#...#.#....#.####..#..#...#..#...##.##.##.##..#########.#......###....#...\n" +
            "...##..#.#.###..##..##.###.##.#.#...#..##...####.....##..#.##.#.#..####..#..##.#..####.##.####.#..#.\n" +
            "##.#.##.###.#...#.##.#.#.#...##...####.#.....#..#.#.###.#.###....##.#.##...##.#.##..#........#....##\n" +
            "..###.#.#.#.....###.....##...##..#.###..#..##.#......####.####....#.###.#.#.....##..#.##..##....##.#\n" +
            ".#..####.###...####.#...##...#..###.##....#.######..#.###.#...#.#####.#.###..#.......#..#.###..#.#..\n" +
            "#...####..#...#..#...#.#...#.#.#.##.#..###..#..#.#.#.###.#.............##..#.###.#.#...#..#.#...#.#.\n" +
            "..##.###.#.#..###..##......###.#...#.###.########.#......##..##....#.###.##.#.....####.#.#........##\n" +
            "##..#.##.##.###..#.##.####.#..#####.......#..#.#####..##.#####.###....#..#.#..#..#...#.###.##.#..##.\n" +
            ".#..#...#..#.#..###.###.#.....##.###.#....#.##.#.##.#.#..#..##...#...#.###.#..#......###..#.#.##..#.\n" +
            "##..####.##.###.#..#..##..##.###.##.#.###.#...###.##....##.#..##.....#.#....##.#...##..........##.##\n" +
            "##.#...#.#..#.#.##.#.#.#..##.##...#..#####.#..#.#.#.#....#..#..#.######.#..#..#.#######.#....#.....#\n" +
            ".#.....#####.###.###...#.####..#####...##...#..#...#..##...#.##.#..#.#........#.#.#..#.#.#.###..##..\n" +
            "###...#.##..#......#.##.#..###.####.#..##.#..#..#..##..#...#.##.##......###.#.#.#.###........##.###.\n" +
            ".#...##.##..#.#.....##.####.........###.##..##.#......#...#.#..#..##.....#######.##.###...#....##.##\n" +
            "#....#.#...#..#.###..##.....##....####.#####.##.#..#.#...#......#...#....#.##.#.....#..#..#..####..#\n" +
            "###.##.####....#.#.....#..#...#.....###.########....##..##.........##..#..####.#..#..#.#.##...#.##..\n" +
            ".....##.#.####.####..#...##.##.#........####.###....#..####.#.##.#.#.#####..#....##..#......##.#..#.\n" +
            "###.#...###..#..#.###..##.##....###.#.....###.##...#.######.#..#......####.#.#..###.##.###....#...#.\n" +
            "##..#.......#...##.###..###.#####.##....#####.#####...##........####....#...##....#.###..##.#..##..#\n" +
            "..##.##.#.#.#.#.##...##.#.#.####..##...##..###.##.#####.#...#####..#.#.#..#.#.##.##.#....###..#...#.\n" +
            ".#..#.###..##..#.#.##.###...###.######.##....#.....##.#.###.....##.#####..##.####...#.##.#..##..#...\n" +
            "#...##....##.#.#####.####.###..#.....#..#.#.#.###..#####.##.....##.##....#.#.#.#.####..##.###..##...\n" +
            "###....#....#..###..#..#..##.#..###...##...##..###..#.#.##..#.##.#....#..#.##.##..####.#.#####...###\n" +
            "#.#..##...#.#.######..##...#.#.#..##.##.....##.#.#......#..####.....#.#....##.###..####.#..#####.#..\n" +
            ".###.##...##..###...#.#..###..##....###.###..##.#..#.####...#...#####.##.##.##....#....##..#.##...##\n" +
            "..#.#.###.....##...#..##.#...#####.##.###.##..####.###...#.#####.#....##...#...#..####...#..##..##.#\n" +
            "##.#..#..#..###..#.#..##...####.###..###.#....####...#.###.#..##..#..##.##.####.#...#.#.##...#.....#\n" +
            ".#...###.#....##.#..#.......#.#.###..###..##.###..#.#...##..#...#...##...#.#.###.##.#.#.##....#.##.#\n" +
            ".#####...#####...#...#.#.####.#.....####.......#.###.#..#...####.#.#..##...#....###.#.##..#..#..#.##\n" +
            ".#.###.#....#...###.#.#..#..####.#..#..#.#...###.###.####...#...#.#.#.#..##..#..#...##...##.....#..#\n" +
            "...##..#...#.##..#.##.####...####.#.###..###..#####.##..#.####.#.###.#.#.###..#...#..###.....#.##...\n" +
            ".########...##.##.##.....#...#####..####..###..#...#.####..#...###.#..###.#...#..#.#...#.#..#...#...\n" +
            ".##.#####.#..#...###...##.....##.#...####.###.##.#.#...#.#.#.#..#.#...##...#...#.##.####.####.####..\n" +
            "##.###.##..#####...#..####....#.###..####..#..#.#.###...##.##.##..###.#.#.##.#.#.#.##..#.#..#....#..\n" +
            "....##..##...###..#.#.....##...#####.#.#...####..##.######.#...#...#.###.##.#.#.#.###.#.###.#...####\n" +
            "#.####...##.#.#..##.#####.##..##.###...#..#......##...#.#.#.###...#.##...#..#...##.#..###.#...#...##\n" +
            "..#..##.#.##.#.#.##..#####.....#.#.###.#.#.#.###..#####.#.....#.##..####.##.#.#.#.##...###....#.#..#\n" +
            "#.#.#..#.##.#.#.##.#...#....#..##.#.##.#.#.#.####.###.###.#.#.####..#..####.#..#....##..####.....###\n" +
            "##...#..#.####....##..#####.###....#..####.#.####..#.#..#.##.##......#..#####...#.##..####....#.#.##\n" +
            ".#.....##....#...###.######.#..#.###...#.#.#..#...#..##...#####...##..#...#####.#.#.###.###..##....#\n" +
            ".###......###.#..#..#...#.##...#.#...#.#..#..##........#..#.####.#.#..##...##.#....###.##..###.#.#..\n" +
            "..###.....#.#....#..#######.####..###...#...#....#...#..##.#..#.##.##..#.##..###...###.##...##....##\n" +
            "#.##..#####..##.####.#.#.#....#.#.#.#..#.#...####.#..#.#.#.##.##.....#..##...#.##....#..#..#..#.#...\n" +
            "##.##.###.#...#.#.#...#...#..###.###.###...###.#...##....#.###.##........#..###..#.#..###..####.....\n" +
            "##.##.##.###..##...#.#.#.#...#...##.#.##..#.#.####..##..#####....#.#####..#####..#.######.#.#.##.##.\n" +
            "..#...##..##..##...#......#..#..#.#..##...#...#....#.#.#...#.#.#..##.#.#...#...#.#.###....###...#...\n" +
            "...####..#########...#.#.#.##.#.#.#.##.#.##...#.####.#.##.#.####.#......##.#.####.#.##.#..###.#.#..#\n" +
            "#.###.##.###.#.#..###...#.##.######.#.#..#.#####.#.###.#..##..#....###..#.#..#......##.#.###...#.###\n" +
            "##..#.#..###.#.#.#.##.##.##..##..#.#.....##.#.#...#.####.##..##.....##..#.####...###...#.###..##.##.\n" +
            "..#.#...#.#.#.##...#..#....######...#.#.##.###.#..####...#.##.#..#..###........#.....###.#..##..##..\n" +
            "..#.#..#.#....#.#..#....###..###.#...###..####...####.#.#.#..#.....#.###.#.#..#.######.###########..\n" +
            "..#...#..#..##.#.##.#.#.##......####.##.#.###.##..##.#.##.#..#.#.##.#...####.#.#.#..#####.#..######.\n" +
            "##.......#####.....#....#..##.#.##..#.###.#..#######.#.#.#....#.#...##.##..##..##..#....###.#....##.\n" +
            "##...##...##..##.#.#..###.###..###..#######.....####..#.##..######....#..#.#.#.#######..#.####.#####\n" +
            "...#.###..##....##.##..#..#...#####...#..##...#.###...###.##....##..#.##.####..##.#...#.##..##..#.#.\n" +
            "......##.###.#.##..#####.#.##.#.#..##.######.##.#..#.#...#....##.##...#.#####.###.####..#.#.###.#.##\n" +
            "#.#.#..#....#.#..#.#..#.####..#.#.###..###.######..#.####.###..##.#.##...###..##.#######.#..##.###..\n" +
            "#...#...######.#.##.......#..##.#..###..#####.#..##...##.#..#.###.###..##.#..#.###.#..#.#.#..#....##\n" +
            "#######..#.#.###..#...##..#...#..##...####..#.####...##.#..#....####.##.#.#..###.#.##.#..#..#.#.##..\n" +
            ".##.##.##.#..###..####.#..##.....#..###...#.#.###.##..###.#..#.###..###..#.####..#.#....#....####.#.\n" +
            ".#..##.#..##.#.#.##.#.###.#..#..###.#.#..#.#...#####...###.#....#..#.#.#..#.#.##.#..#...##.#.###.#..\n" +
            "#.###..####..###.#.#.##.#.#.#......####.#...#..##.#....#...#.######...#########.#...##.###.#..###.##\n" +
            "##.########....##...###..##.#.#..#.####..#..###.......##..#.####.###...#..###...###......####...#...\n" +
            ".#..########.#.##.#.####..#..##..###.#.##.#..##.##..##.#..##....#...#...###.#.#.#.###.#..#.##.#.###.\n" +
            "##....########..........#.#.###.###..##.##.#....#.#..####.######.#.##.#.#..###...#.##.#.#..##.....##\n" +
            ".####......###..#.##.#.#....##.#...#..##.##.#######.#..###...###..###...#.....###.#.##....##...#.#..\n" +
            "####....#.#..######..#.######..#####..#.##.##..##.#.#.#.....##.###..#.###.##.###.#......##.#..####.#\n" +
            ".#...###..#..#.###...##.#..####..#...#.###.#####..#..#.#..###.#####.##.#..##.#.#.##.....#...###....#\n" +
            "..###..##..#..##..####..##.#.#......#..##.#.##.#.####....##.##.###..##..##.#.##..#####.#.##....#.#.#\n" +
            ".##...##.#.#.###..#.##.#..##..#..#....###....#.##.#...##...#######.###..##...##.##.##.#######....###\n" +
            "##..#.#.###..###...###.##..#.##..#.#.#.###.##...###....#.#..#.#..##.#..##.##....#..#...#....###...#.\n" +
            "#.#..###.##.######.#.#####.#...##..##.....##...##...##..#.###.#...#.#.#..#....##..##.###.##.#.##....\n" +
            "...###.######...##..#.####.###..#...#..#..#..#..##.#.#....#....#####.#.##..#.##.#.########..###.##..\n" +
            ".#...#####.#..#..##.##.#.###......#..#..#.###......#...#.#.#######..#...####.#.########...#..##.#.#.\n" +
            "#..#####.#.#.#....##.#..#.......##..#.##.##......#..##..#..#..##.#.###..#.#.#.#..#..#.##.####.#.#..#\n" +
            "####...#.....#.#..#..#.##########..#..#...######.######.#.#.....#....#.###.####.##..#..#.####..###..\n" +
            ".####.###.####...#...####.##..###..#######.###.#....##.###.#..#.#####...##.#..#.....#.####..#..#####";
}
