package pl.karolsiwek.aoc2021.day12;


import java.util.*;

public class Day12b {

    public static void main(String[] args) {

        Map<String, List<String>> connections = getConnections(mainInput);

        List<String> visited = new ArrayList<>();
        visited.add("start");
        Integer nb = getNumberOfPaths("start", connections, visited,false);


        System.out.println(nb);

    }

    private static Integer getNumberOfPaths(String node, Map<String, List<String>> connections, List<String> visited, boolean smallCaveVisited) {
        int nb = 0;
        visited.add(node);
        boolean smallCaveMark = false;
        if(node.equals("end")) {
            System.out.println(visited);
            return 1;
        }

        for(String nextNode : connections.get(node)) {
            if(nextNode.toLowerCase().equals(nextNode) && visited.contains(nextNode) ){
                if(smallCaveVisited || visited.stream().filter(el -> el.equals(nextNode)).count()>1){
                    continue;
                } else {
                    nb += getNumberOfPaths(nextNode, connections, new ArrayList<>(visited), true);
                }
            } else {
                nb += getNumberOfPaths(nextNode, connections, new ArrayList<>(visited), smallCaveVisited);
            }

        }

        return nb;

    }

    private static Map<String, List<String>> getConnections(String testInput) {
        Map<String, List<String>> result = new HashMap<>();

        for (String line : testInput.split("\n")) {
            String a = line.trim().split("-")[0];
            String b = line.trim().split("-")[1];

            if (!result.containsKey(a)) {
                result.put(a, new ArrayList<>());
            }
            result.get(a).add(b);

            if (!result.containsKey(b)) {
                result.put(b, new ArrayList<>());
            }
            result.get(b).add(a);
        }
        return result;
    };

//
//    static String testInput = "start-A\n" +
//            "start-b\n" +
//            "A-c\n" +
//            "A-b\n" +
//            "b-d\n" +
//            "A-end\n" +
//            "b-end";

//    static  String testInput = "dc-end\n" +
//            "HN-start\n" +
//            "start-kj\n" +
//            "dc-start\n" +
//            "dc-HN\n" +
//            "LN-dc\n" +
//            "HN-end\n" +
//            "kj-sa\n" +
//            "kj-HN\n" +
//            "kj-dc";

    static String testInput = "fs-end\n" +
            "he-DX\n" +
            "fs-he\n" +
            "start-DX\n" +
            "pj-DX\n" +
            "end-zg\n" +
            "zg-sl\n" +
            "zg-pj\n" +
            "pj-he\n" +
            "RW-he\n" +
            "fs-DX\n" +
            "pj-RW\n" +
            "zg-RW\n" +
            "start-pj\n" +
            "he-WI\n" +
            "zg-he\n" +
            "pj-fs\n" +
            "start-RW";


    static String mainInput = "start-YY\n" +
            "av-rz\n" +
            "rz-VH\n" +
            "fh-av\n" +
            "end-fh\n" +
            "sk-gp\n" +
            "ae-av\n" +
            "YY-gp\n" +
            "end-VH\n" +
            "CF-qz\n" +
            "qz-end\n" +
            "qz-VG\n" +
            "start-gp\n" +
            "VG-sk\n" +
            "rz-YY\n" +
            "VH-sk\n" +
            "rz-gp\n" +
            "VH-av\n" +
            "VH-fh\n" +
            "sk-rz\n" +
            "YY-sk\n" +
            "av-gp\n" +
            "rz-qz\n" +
            "VG-start\n" +
            "sk-fh\n" +
            "VG-av";
}
