package pl.karolsiwek.aoc2021.day14;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14b2 {

    public static void main(String[] args) {
        solve("NNCB", testInput);
        solve("HBHVVNPCNFPSVKBPPCBH", mainInput);

//not 1865428 - too low
        //    4301014262631 -- too low
        //    4301014262632 -- too low
        // 4807056953866 -- ok
    }

    private static void solve(String poly, String rulesText) {
        Map<String, String> rules = readRules(rulesText);
        Map<String, Long> input = new HashMap<>();

        for(int i=0;i<poly.length()-1;i++){
            String key = poly.substring(i,i+2);
            input.put(key, input.getOrDefault(key, 0l ) + 1l);
        }

        Map<String, Long> pairs = growPolymer(input, rules, 40);

        System.out.println(pairs);

        Map<String, Long> quantities = getStats(pairs, poly.substring(poly.length()-1));
        quantities .entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue()).sorted()
                .forEach(System.out::println);
        System.out.println(quantities.values().stream().max(Comparator.naturalOrder()).get()-quantities.values().stream().min(Comparator.naturalOrder()).get());

    }

    private static Map<String, Long> getStats(Map<String, Long> pairs, String lastLetter) {
        Map<String, Long> quantities = pairs.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey().substring(0,1), e -> e.getValue(), (a, b) -> a + b));
        quantities.put(lastLetter, quantities.get(lastLetter)+1);
        return quantities;
    }

    private static Map<String, Long> growPolymer(Map<String, Long> pairs, Map<String, String> rules, int n) {

        if(n==0) return pairs;

        Map<String, Long> result = new HashMap<>();

        for(String pair : pairs.keySet()) {
            if(rules.containsKey(pair)) {
                String first = pair.substring(0, 1) + rules.get(pair);
                String second = rules.get(pair) + pair.substring(1);

                result.put(first, result.getOrDefault(first, 0l) + pairs.get(pair));
                result.put(second, result.getOrDefault(second, 0l) + pairs.get(pair));
            } else {
                result.put(pair, result.getOrDefault(pair,0l) + pairs.get(pair));
                if(!result.containsKey(pair)) {
                    result.put(pair, 0l);
                }
            }
        }
        return growPolymer(result, rules, n-1);
    }

    private static Map<String, String> readRules(String testInput) {
        Map<String, String > result = new HashMap<>();
        for(String line : testInput.split("\n")) {
            if(!line.contains("->")) continue;

            result.put(line.split("->")[0].trim(), line.split("->")[1].trim());

        }

        return result;
    }


    static String testInput = "CH -> B\n" +
            "HH -> N\n" +
            "CB -> H\n" +
            "NH -> C\n" +
            "HB -> C\n" +
            "HC -> B\n" +
            "HN -> C\n" +
            "NN -> C\n" +
            "BH -> H\n" +
            "NC -> B\n" +
            "NB -> B\n" +
            "BN -> B\n" +
            "BB -> N\n" +
            "BC -> B\n" +
            "CC -> N\n" +
            "CN -> C";


    static String mainInput = "HBHVVNPCNFPSVKBPPCBH\n" +
            "\n" +
            "HV -> B\n" +
            "KS -> F\n" +
            "NH -> P\n" +
            "OP -> K\n" +
            "OV -> C\n" +
            "HN -> O\n" +
            "FF -> K\n" +
            "CP -> O\n" +
            "NV -> F\n" +
            "VB -> C\n" +
            "KC -> F\n" +
            "CS -> H\n" +
            "VC -> F\n" +
            "HF -> V\n" +
            "NK -> H\n" +
            "CF -> O\n" +
            "HH -> P\n" +
            "FP -> O\n" +
            "OH -> K\n" +
            "NN -> C\n" +
            "VK -> V\n" +
            "FB -> F\n" +
            "VP -> N\n" +
            "FC -> P\n" +
            "SV -> F\n" +
            "NO -> C\n" +
            "VN -> S\n" +
            "CH -> N\n" +
            "FN -> N\n" +
            "FV -> P\n" +
            "CN -> H\n" +
            "PS -> S\n" +
            "VF -> K\n" +
            "BN -> S\n" +
            "FK -> C\n" +
            "BB -> H\n" +
            "VO -> P\n" +
            "KN -> N\n" +
            "ON -> C\n" +
            "BO -> S\n" +
            "VS -> O\n" +
            "PK -> C\n" +
            "SK -> P\n" +
            "KF -> K\n" +
            "CK -> O\n" +
            "PB -> H\n" +
            "PF -> O\n" +
            "KB -> V\n" +
            "CC -> K\n" +
            "OK -> B\n" +
            "CV -> P\n" +
            "PO -> O\n" +
            "SH -> O\n" +
            "NP -> F\n" +
            "CO -> F\n" +
            "SS -> P\n" +
            "FO -> K\n" +
            "NS -> O\n" +
            "PN -> H\n" +
            "PV -> V\n" +
            "KP -> C\n" +
            "BK -> B\n" +
            "BP -> F\n" +
            "NB -> C\n" +
            "OF -> O\n" +
            "OC -> O\n" +
            "HO -> C\n" +
            "SC -> K\n" +
            "HC -> C\n" +
            "HS -> B\n" +
            "KH -> N\n" +
            "FS -> N\n" +
            "PH -> O\n" +
            "PC -> V\n" +
            "BS -> O\n" +
            "KO -> F\n" +
            "SP -> K\n" +
            "OB -> O\n" +
            "SF -> K\n" +
            "KV -> F\n" +
            "NC -> B\n" +
            "SO -> C\n" +
            "CB -> S\n" +
            "VH -> V\n" +
            "FH -> F\n" +
            "SN -> V\n" +
            "SB -> P\n" +
            "PP -> B\n" +
            "BF -> K\n" +
            "HB -> O\n" +
            "OO -> V\n" +
            "HP -> H\n" +
            "KK -> O\n" +
            "BV -> K\n" +
            "BH -> B\n" +
            "HK -> H\n" +
            "BC -> C\n" +
            "VV -> S\n" +
            "OS -> F\n" +
            "NF -> B";
}
