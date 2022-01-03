package pl.karolsiwek.aoc2021.day11;


import java.util.*;
import java.util.stream.Collectors;

public class Day11b {

    public static void main(String[] args) {
        Map<Pair, Octopus> area= new HashMap<>();
        String[] lines = mainInput.split("\n");
        for(int y = 0;y<lines.length;y++) {
            String[] line = lines[y].split("");
            for(int x=0;x<line.length;x++) {
                area.put(new Pair(x,y), new Octopus(Integer.parseInt(line[x])));
            }
        }

        print(area);

        Integer counter = 0;
        for(int step=0;true;step++) {
            System.out.println("STEP: " +step);
            List<Pair> toTickList;
            List<Pair> toTickNewRound = new ArrayList<Pair>(area.keySet().stream().collect(Collectors.toList()));
            ;
            Set<Pair> tickedList = new HashSet<>();

            do {
                toTickList = new ArrayList<>(toTickNewRound);
                toTickNewRound = new ArrayList<>();
                for (Pair toTick : toTickList) {
                    if (area.containsKey(toTick) && area.get(toTick).tick()) {
                        counter++;
                        tickedList.add(toTick);
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (i == 0 && j == 0) continue;
                                toTickNewRound.add(new Pair(toTick.x + i, toTick.y + j));
                            }
                        }
                    }
                }
                print(area);
            } while (toTickNewRound.size() > 0);

            for(Pair ticked : tickedList) {
                area.get(ticked).clear();
            }
            if(tickedList.size()==100) {
                System.out.println(step+1);
                return;
            }
            print(area);
        }

    }

    private static void print(Map<Pair, Octopus> area) {
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                Octopus octopus = area.get(new Pair(j, i));
                if(octopus == null) continue;;
                if(octopus.energy>9 ) {
                    System.out.print("X");
                } else {
                    System.out.print(octopus.energy);
                }


            }
            System.out.println();
        }
        System.out.println();
    }

    static class Octopus {
        int energy;

        public Octopus(int energy) {
            this.energy = energy;
        }

        public boolean tick() {
            energy++;
            if(energy==10) {
                return true;
            }
            return false;
        }

        public void clear() {
            energy = 0;
        }
    }

    static class Pair {
        Integer x,y;

        public Pair(int x, int y) {
            this.x=x;
            this.y=y;
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

//    static String testInput = "5483\n" +
//            "2745\n";

    static String testInput = "5483143223\n" +
            "2745854711\n" +
            "5264556173\n" +
            "6141336146\n" +
            "6357385478\n" +
            "4167524645\n" +
            "2176841721\n" +
            "6882881134\n" +
            "4846848554\n" +
            "5283751526";


    static String mainInput = "5421451741\n" +
            "3877321568\n" +
            "7583273864\n" +
            "3451717778\n" +
            "2651615156\n" +
            "6377167526\n" +
            "5182852831\n" +
            "4766856676\n" +
            "3437187583\n" +
            "3633371586";
}
