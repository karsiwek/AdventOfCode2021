package pl.karolsiwek.aoc2021.day18;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day18b {

    public static void main(String[] args) {

        Element root = new Element(null);
        List<String> lines = Arrays.stream(testInput.split("\n")).filter(Objects::nonNull).map(el -> el.trim()).collect(Collectors.toList());
        readElements(lines.get(0), 0, root);
        process(root);
        System.out.println("LEFT:");
        System.out.println(root);

        for (int i = 1; i < lines.size(); i++) {
            Element left = root;
            root = new Element(null);
            left.parent = root;

            Element right = new Element(null);
            readElements(lines.get(i), 0, right);
            process(right);
            System.out.println("RIGHT:");
            System.out.println(right);
            right.parent = root;

            root.left = left;
            root.right = right;

            process(root);
            System.out.println("CHUUUJ");
            System.out.println(root);
        }

        System.out.println(root.getMag());

        Long maxMag = 0l;

        for(int i=0;i<lines.size();i++) {
            System.out.println(i + "/" + lines.size());
            for(int j=0;j<lines.size();j++) {
                if(i==j) continue;
                Element root2 = new Element(null);
                Element right = new Element(null);
                Element left = new Element(null);
                readElements(lines.get(i), 0, left);
                process(left);
                readElements(lines.get(j), 0, right);
                process(right);
                left.parent = root;
                right.parent = root;
                root.left = left;
                root.right = right;
                process(root);
                Long magCan = root.getMag();
                if(magCan>maxMag) {
                    maxMag = magCan;
                    System.out.println(maxMag);
                }
            }
        }

        System.out.println(maxMag);

    }


    private static void process(Element node) {
        boolean something = true;
        while (explode(node, 0) || split(node)) {
             //   System.out.println(node);

        }
    }

    private static boolean split(Element node) {
        if (node.value != null) {
            if (node.value > 9) {
                Element left = new Element(node);
                left.value = node.value / 2;
                Element right = new Element(node);
                right.value = node.value - left.value;
                node.left = left;
                node.right = right;
                node.value = null;
                return true;
            } else {
                return false;
            }
        }
        return split(node.left) || split(node.right);
    }

    private static boolean explode(Element node, int depth) {
        if (node.value != null) {
            return false;
        }

        if (node.left.value != null && node.right.value != null) {
            if (depth > 3) {
                addToFirstLeft(node, node.left.value);
                addToFirstRight(node, node.right.value);
                node.left = null;
                node.right = null;
                node.value = 0;
                return true;
            }
        }
        return explode(node.left, depth + 1) || explode(node.right, depth + 1);
    }

    private static void addToFirstLeft(Element node, Integer value) {
        if (node == null || node.parent == null) return;

        if (node == node.parent.left) {
            addToFirstLeft(node.parent, value);
        } else {
            if (node.value != null) {
                node.value += value;
            } else {
                addToLastRight(node.parent.left, value);
            }
        }
    }

    private static void addToFirstRight(Element node, Integer value) {
        if (node == null || node.parent == null) return;

        if (node == node.parent.right) {
            addToFirstRight(node.parent, value);
        } else {
            if (node.value != null) {
                node.value += value;
            } else {
                addToLastLeft(node.parent.right, value);
            }
        }
    }

    private static void addToLastRight(Element node, Integer value) {
        if (node.value != null) {
            node.value += value;
        } else {
            addToLastRight(node.right, value);
        }
    }

    private static void addToLastLeft(Element node, Integer value) {
        if (node.value != null) {
            node.value += value;
        } else {
            addToLastLeft(node.left, value);
        }
    }


    private static Integer readElements(String input, Integer idx, Element toReturn) {
        if (idx > input.length()) {
            return input.length();
        }

        if (input.substring(idx, idx + 1).equals("[")) {
            Element left = new Element(toReturn);
            idx = readElements(input, idx + 1, left);
            if (!input.substring(idx, idx + 1).equals(",")) {
                throw new IllegalStateException(idx.toString());
            }
            idx++;
            Element right = new Element(toReturn);
            idx = readElements(input, idx, right);
            if (!input.substring(idx, idx + 1).equals("]")) {
                throw new IllegalStateException(idx.toString());
            }
            idx++;
            toReturn.left = left;
            toReturn.right = right;
        } else {
            toReturn.value = Integer.parseInt(input.substring(idx, idx + 1));
            idx++;
        }
        return idx;
    }

    static class Element {
        Integer value;
        Element left;
        Element right;
        Element parent;

        public Element(Element parent) {
            this.parent = parent;
        }

        public Long getMag() {
            if(value != null) return Long.parseLong(value.toString());
            return 3* left.getMag() + 2*right.getMag();
        }
        @Override
        public String toString() {
            if (value != null) {
                return value.toString();
            } else {
                return "[" + left.toString() + "," + right.toString() + "]";
            }
        }
    }

//    static  String testInput = "[1,1]\n" +
//            "[2,2]\n" +
//            "[3,3]\n" +
//            "[4,4]\n" +
//            "[5,5]\n" +
//            "[6,6]";

    static String testInput = "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]\n" +
            "[[[5,[2,8]],4],[5,[[9,9],0]]]\n" +
            "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]\n" +
            "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]\n" +
            "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]\n" +
            "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]\n" +
            "[[[[5,4],[7,7]],8],[[8,3],8]]\n" +
            "[[9,3],[[9,9],[6,[4,9]]]]\n" +
            "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]\n" +
            "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]";


    static String mainInput = "[[[4,[8,0]],[[6,9],[0,3]]],[[[2,9],[6,3]],[1,[9,9]]]]\n" +
            "[[[2,[5,0]],[[5,6],4]],[[[2,0],[4,8]],[7,8]]]\n" +
            "[[[5,[0,6]],[9,6]],4]\n" +
            "[[9,[5,3]],[[4,9],[8,2]]]\n" +
            "[[5,[6,2]],[[9,3],[4,[3,2]]]]\n" +
            "[[[9,[9,8]],[2,9]],[[4,[6,5]],[[6,2],9]]]\n" +
            "[[[9,8],7],[[0,[2,1]],[[9,5],[4,5]]]]\n" +
            "[[4,[0,1]],[[[3,6],5],[4,9]]]\n" +
            "[[0,9],[[3,1],3]]\n" +
            "[[[3,[7,4]],[9,8]],5]\n" +
            "[[6,[2,8]],[0,[[6,9],[8,1]]]]\n" +
            "[[1,[[1,1],[9,4]]],[[3,[2,9]],[7,[5,4]]]]\n" +
            "[[[4,[9,3]],[4,[3,8]]],[[[1,3],[7,1]],5]]\n" +
            "[[[[2,9],8],7],[4,[5,4]]]\n" +
            "[[8,[[1,3],5]],[[6,5],6]]\n" +
            "[[[[5,5],[3,1]],[[6,8],2]],[[8,5],3]]\n" +
            "[[[7,[7,3]],7],0]\n" +
            "[[7,[[1,0],0]],[2,[9,2]]]\n" +
            "[[3,[1,7]],7]\n" +
            "[[2,[6,3]],[[[1,6],7],[9,3]]]\n" +
            "[5,5]\n" +
            "[[[[5,1],[1,8]],[[3,0],2]],[[8,2],[9,0]]]\n" +
            "[[[[4,1],[0,8]],[9,[9,7]]],[[[2,4],3],[8,9]]]\n" +
            "[[3,[8,2]],9]\n" +
            "[[7,1],[[[5,8],[9,9]],2]]\n" +
            "[[[[2,3],[7,7]],[[6,3],7]],[8,[[1,8],[1,5]]]]\n" +
            "[[0,[0,5]],[[[1,5],6],0]]\n" +
            "[8,[[[7,9],[2,9]],6]]\n" +
            "[[5,[3,[8,7]]],[3,4]]\n" +
            "[[[8,2],[3,[5,2]]],4]\n" +
            "[[[[9,6],[3,3]],[3,[1,8]]],[[[6,1],4],[[1,3],2]]]\n" +
            "[[[[5,7],[3,6]],[0,[6,4]]],[[[0,2],8],3]]\n" +
            "[[[2,4],[3,[9,1]]],6]\n" +
            "[[[[9,6],[2,0]],[4,0]],[[5,[0,9]],[[5,3],[6,6]]]]\n" +
            "[[[3,5],9],[[7,[8,1]],[[2,6],[0,6]]]]\n" +
            "[[[9,2],[[3,2],8]],[4,4]]\n" +
            "[[[4,[5,6]],5],[[7,[8,7]],2]]\n" +
            "[[[4,8],[3,[7,1]]],1]\n" +
            "[8,[[1,[9,4]],7]]\n" +
            "[[[[2,3],5],[7,[4,9]]],[[4,8],[[8,1],[3,1]]]]\n" +
            "[[[[4,2],4],[1,[0,7]]],[[1,[4,5]],[9,[3,6]]]]\n" +
            "[[[[7,2],[4,9]],[6,2]],[[6,7],[2,[0,2]]]]\n" +
            "[[4,5],[[[4,1],3],5]]\n" +
            "[[[9,[2,2]],[[0,1],[3,2]]],2]\n" +
            "[[2,[[7,5],3]],[[[1,0],[7,4]],0]]\n" +
            "[6,[[3,[7,2]],[[6,5],[4,7]]]]\n" +
            "[[4,[[5,3],[1,8]]],[[[6,0],3],[2,7]]]\n" +
            "[[[3,[4,3]],[8,1]],[8,[3,[0,7]]]]\n" +
            "[[[[8,5],[0,5]],[[8,0],9]],[[[7,7],[3,0]],[4,[2,7]]]]\n" +
            "[[4,[[2,0],[5,7]]],[8,2]]\n" +
            "[[[[6,3],[8,9]],[[7,5],[4,3]]],[9,[2,4]]]\n" +
            "[9,[[[2,1],[9,7]],[5,[3,8]]]]\n" +
            "[[0,[[6,3],[7,8]]],[[7,[8,2]],3]]\n" +
            "[[7,[[2,4],4]],6]\n" +
            "[[3,[2,8]],[[[1,8],1],0]]\n" +
            "[[1,[[5,0],1]],6]\n" +
            "[[5,[2,[5,4]]],[[[8,5],9],[8,[7,2]]]]\n" +
            "[[[[5,0],[6,1]],0],[[7,9],[2,3]]]\n" +
            "[[[[9,6],[0,0]],[0,[5,2]]],5]\n" +
            "[[[[6,6],6],[0,[9,4]]],[[[0,7],[3,8]],[8,5]]]\n" +
            "[6,8]\n" +
            "[[6,[7,1]],5]\n" +
            "[[[[2,9],1],[[7,6],5]],[[9,2],[[9,5],2]]]\n" +
            "[[2,[6,5]],[2,[0,8]]]\n" +
            "[[[4,9],8],0]\n" +
            "[[3,[[8,4],[3,5]]],[[0,3],[2,8]]]\n" +
            "[[[3,[1,6]],1],[3,[[7,4],4]]]\n" +
            "[[[1,[0,8]],6],[[2,5],[6,[1,2]]]]\n" +
            "[[7,[[5,9],5]],[[7,9],7]]\n" +
            "[[[3,[9,9]],[[5,0],2]],[[8,[6,6]],9]]\n" +
            "[[9,4],[[2,[6,1]],6]]\n" +
            "[[2,[[3,2],5]],[9,8]]\n" +
            "[[[1,[5,7]],4],[9,[[7,2],3]]]\n" +
            "[[[[4,0],[3,9]],[[2,4],[9,4]]],0]\n" +
            "[[[6,5],8],[[[1,7],3],7]]\n" +
            "[[[[5,9],4],6],[[[3,3],[0,4]],[3,[2,2]]]]\n" +
            "[[[[3,5],[7,4]],[[7,2],[3,2]]],1]\n" +
            "[[[0,9],[1,[4,6]]],[3,[[6,9],9]]]\n" +
            "[[[[3,8],4],[8,[5,6]]],[6,[[0,1],8]]]\n" +
            "[[5,[[4,3],5]],[[2,[2,8]],[5,[5,7]]]]\n" +
            "[[[4,[2,7]],0],[[7,6],[[5,8],[4,4]]]]\n" +
            "[[[2,[3,3]],[6,[1,7]]],[[[2,8],[9,1]],[[2,7],[9,2]]]]\n" +
            "[[9,[3,5]],[[[9,4],[1,8]],[[7,2],[9,6]]]]\n" +
            "[[5,[4,[4,0]]],[[5,5],[[8,0],7]]]\n" +
            "[0,[[[1,9],9],[7,[0,3]]]]\n" +
            "[[[[5,3],8],1],[[[7,3],[5,4]],9]]\n" +
            "[[[[4,0],4],[9,[1,9]]],[[[8,9],7],[[5,9],[0,3]]]]\n" +
            "[[[0,8],[[7,2],7]],[[1,[8,4]],[[8,3],2]]]\n" +
            "[[[[2,9],[0,0]],[0,[2,2]]],[6,9]]\n" +
            "[2,[6,2]]\n" +
            "[[[[9,9],[8,1]],5],6]\n" +
            "[[4,1],[[[5,9],[3,2]],[0,[4,0]]]]\n" +
            "[[[[8,9],3],[8,0]],[[[4,6],[2,3]],1]]\n" +
            "[[[0,5],[3,[8,2]]],[3,2]]\n" +
            "[[[[3,3],[1,8]],[[5,8],[2,7]]],[[[1,5],9],[4,2]]]\n" +
            "[[[[7,0],5],2],[1,[[5,1],6]]]\n" +
            "[3,[[9,[9,3]],[1,[2,8]]]]\n" +
            "[[5,[[7,4],[0,3]]],[4,[[4,4],[6,8]]]]\n" +
            "[[[8,7],[5,1]],[[4,5],[7,[3,8]]]]\n" +
            "[[[[4,5],[5,5]],[[2,7],[0,5]]],[[5,[7,0]],[[9,6],5]]]";
}
