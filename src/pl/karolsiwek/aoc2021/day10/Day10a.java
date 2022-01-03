package pl.karolsiwek.aoc2021.day10;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Day10a {

    static List<String> types = Arrays.asList("(", "[", "{", "<");
    static Map<String, String> closings = Map.of("(",")","[","]","{","}","<",">");
    static Map<String, Integer> scoring = Map.of(")",3,"]",57,"}",1197,">", 25137);



    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList(testInput.split("")));

        Integer score = 0;
        for(String line : mainInput.split("\n")) {
            try {
                System.out.println(line);
                parseLine(new ArrayList<>(Arrays.asList(line.trim().split(""))));
            } catch (IllegalArgumentException e) {
                score+=scoring.get(e.getMessage());
            }

        }

        System.out.println(score);

    }

    static Chunk parseLine(List<String> input) {

        if(input.isEmpty()) return null;

        if(!types.contains(input.get(0))) {
            throw new IllegalArgumentException(input.get(0));
        }
        
        String type = input.get(0);
        Chunk chunk = new Chunk(new ArrayList<>(), type);
        input.remove(0);

        while(input.size()>0) {
            if(input.get(0).equals(closings.get(type))) {
                input.remove(0);
                break;
            }
            Optional.ofNullable(parseLine(input)).ifPresent(elem -> chunk.getChildren().add(elem));
        }
        
        return chunk;
        
    }

    public static class Chunk {
        public Chunk(List<Chunk> children, String type) {
            this.children = children;
            this.type = type;
        }

        public List<Chunk> getChildren() {
            return children;
        }

        public void setChildren(List<Chunk> children) {
            this.children = children;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        List<Chunk> children;
        String type;
    }

    static String testInput = "[({(<(())[]>[[{[]{<()<>>\n" +
            "[(()[<>])]({[<{<<[]>>(\n" +
            "{([(<{}[<>[]}>{[]{[(<()>\n" +
            "(((({<>}<{<{<>}{[]{[]{}\n" +
            "[[<[([]))<([[{}[[()]]]\n" +
            "[{[{({}]{}}([{[{{{}}([]\n" +
            "{<[[]]>}<{[{[{[]{()[[[]\n" +
            "[<(<(<(<{}))><([]([]()\n" +
            "<{([([[(<>()){}]>(<<{{\n" +
            "<{([{{}}[<[[[<>{}]]]>[]]";


    static String mainInput = "[(([{<{(<{{[({{}{}}{[]()})<{{}()}>]}}(([{{{}[]}[[]()]}[<{}[]]{()()}]](({{}{}}{{}()}))){[{({}())[[\n" +
            "<(({[<([{({[{{<>()}}[{<>()}({}{})]]<{<()<>>{[]()}}(((){}>[[][]])>}([{<[]{}>(<>[])}]))<[[[[[][]\n" +
            "(<<(<{{{{<<<[(()<>){()<>}][[()()]]>{<{[]{}}<<>()>>}>{(<{<>}([]{})><(<>())<(){}>>)<(([]{})(()()))<<()[]>{{}[]}\n" +
            "[[[[<[{[(<{{{({}<>)((){})}((()())[()()])}}><[([((){})]<[()[]]{{}<>}>)[[{[]<>}][([]{})[{}()]]]]>)<{(<\n" +
            "[<(<[[((<{((<<<>[]>><<<>{}>>){<[{}<>][<>[]]><<<>()>[(){}]>})[<{[{}<>][(){}]}<[[]<>][{}[]])>{([<>[]][\n" +
            "(([[[[<([[{([{<>()}{()<>}][((){})]){[{[]<>}({}<>)][(<><>)[()[]]]}}<{{({}{}){[]{}}}<{<><>}({}{})>}>\n" +
            "{{{[<(<([<{({{[]()}[{}()]}{<()<>>(()<>)})}><<[{<()()>(()[])}<<<>[]]>][<{()}{<><>}>({{}[]})]>>](\n" +
            "(((<<({<{{<[{[{}[]][()<>]}({{}{}}[{}[]])]>}<<((<{}[]>{<>{}})[{{}[]}])>{<({<>}<<>[]>){[{}{}][()[\n" +
            "<[{{[{[[(<{({{<>[]}<{}{}>}<([]())<[]()>>)}({{[[]<>]]<<<><>>[<>]>}<{([]){(){}}}<{[]<>}{[]<>}\n" +
            "[(<([{[{<{{[<<()()>{<>}>(({}<>)<()()>)]({{{}{}}[{}()]}[[()<>](<><>)]))}(<<{{()<>}([]())}[<{}\n" +
            "(((((<<{<({{<[{}()]({}<>)>(<{}[]>([]()))}<{<()<>>([]<>)]>}[<{<()<>><[]<>>}[{[][]}([]{})]>{[[()\n" +
            "(<(([<[[(<{(({(){}}<[]<>>)[(()[])(()[])])({((){})}{<{}{}>(<>[])})}((({<>()}<<>{}>)(<{}<>>{[][]})))>[<[{<{}(\n" +
            "<(<{([(({<<(<{()<>}[(){}]><{[]{}}<[][]>>)((([]{}){<>{}}){({}<>){<>{}}})>>(<{<({}<>)<{}<>>>({[]()}{<>{}})}>[[\n" +
            "<{{(<<<[(([[[[{}{}]]<[[]][()()]>][(({}[]){[]})<{(){}}{{}{}}>]][({[(){}]([]())}([()<>][{}()]))({([]<>)}[<<\n" +
            "(([{{<([[[{{[<<>[]>{()()}][{<>()}{{}}]}([<[]><[]{}>]<<[]<>>>)}][{({[()()]<{}{}>})((<[]<>><\n" +
            "{{<{<[{<[{<[({{}()}([]{})){(<>[])([][])}][[{()[]}][<[]{}>]]>({(<{}{}}<[]{}>)[[()<>][{}{}]]}[<<[]()>[{}{}]\n" +
            "((<([([(({[([({}{})]({[]<>}<<>()>))({<()()>[<><>]}({<>{}}<{}[]>))]{{[{{}[]}][[()()](()())]}[\n" +
            "([<(<[{[<<(<<{<>[]}[[]{}]>([{}][<><>])>[{<<>()><<>()>}])>>]}[(({<{(<()<>>{{}{}})[{[][]}]}<{\n" +
            "([(<{{[[[[{{(<{}<>>([]())){{[]<>}{<>()}}}[{[{}<>]<(){}>}]}[<<[(){}]([]<>)><[[][]][<>[]]>>({(\n" +
            "(<{[{{<{<<{{<(<>{}){()[]}><(<><>){[]<>}>}{<[<><>]{[]<>}>[[<><>]{[]}]}}>[([<[{}[]]>(<[]()>([]<>))]<[<[]{\n" +
            "<{([[<<{[([<<{[]{}}<<><>>><<<>{}>[[]()]>>({<[]<>>({}{})}({()()}(<>[])))][{(<(){}>[<><>])<<{}<>><[][]>>}[[\n" +
            "{(<[[{<{[[[{[([][])(()())]}[<[[]<>]{{}()}>{<{}[]>[(){}]}]]]{[<({[][]}(<><>))[([][])<<>[]>]>({{{}[]}[<>{\n" +
            "[({{{(({[<(([<(){}>[[]{}]]){{{{}()}}[<[]()>({})]}){<[<{}{}>{[]()}](({}[]>{<>[]})>[([()<>][[\n" +
            "{{<{{{<([<<[{[[][]]({}())}[<<>[]><<>[]>]><{(<><>)({}{})}{(()<>)([][])}>>{<[<{}{}>{()()}]{<{}{}><[]>\n" +
            "([<<[((({{{<([{}[]](()()))<(()()){{}{}}>>{[{<>[]}({}())>}}}<{[<<{}<>>(<>{})>(({}<>)[()[]])](<{()<>}\n" +
            "{(<<<[[<[({{[{{}{}}][[[]{}](<><>)]}<{([]{})<[][]>}[{{}{}}<()()>]>}<[{{()[]}{()[]}}[[[][]]{(){}}]](<({\n" +
            "<{{((<{[<({{([<><>]([]<>))}(<<()<>><(){}>>{([]<>)([][])})}{[{({}())[()()]}[<<>()>{[][]}]](\n" +
            "<<<[({({<({[<<<>{}>(<>{})>]((<<>()>[()])(({}{}){()()}))})>{{<(([()<>]<<>[]>)({()}))>}}})}<{({<<(<<{}>(\n" +
            "<(<{<[((<(<[([()<>][[]()])]<((<>)<{}{}>]({()()}[[]{}])>><<{{<><>}{{}<>}}([[][]]<<>>)><(<{}{}>({}[]))\n" +
            "[[([{[{[{({([<()>]{(<>())(()())})[[{<>()}{()<>}][[<><>][[][]]]]}{(({{}{}})[{{}()}[{}<>]]){[<()()>{()[]}]\n" +
            "[<<[{<({([<(<({}())[[]{}]><[[]{}]{<>()}>)>(<(<()()>)(<()<>>[{}[]])>)]<<[{<{}[]>([]{})}<((){})<\n" +
            "{[{{<{[{({{<[<<>{}><[][]>]<<(){}>[[]<>]>>([[<>[]]<()[]>](({}{})(()<>)))}}{<<{<(){}><()[]>}[(<>[])(\n" +
            "[<<[[([[{{[({<<><>><<>[]>})({<{}()>({}[])}((()<>)[<>()]))](<([()()])<<<>[]><()()>>>)}<{<{(()<>)[()[]]}<({}())\n" +
            "[{([{<([<(<<(([][])<<>[]>)<{<><>}(<><>)>>(<(<>[])([]{})>{<()<>><{}<>>})>((<[<>[]][[][]]><{<><>}(<>[])>)))[\n" +
            "<{{([{[([[{<[(()())[{}{}]]><[<[]>[(){}]](([]<>)(()()))>}{([{[]()}[{}[]]]<[<>[]][<>{}]>)}]][<[<<(<>{})[{}{}]>>\n" +
            "{{[<<{{({({<<{<>()}<{}[]>>>{<[<>()](()<>)>{(()<>)<{}[]>}}}<((<<>[]>{()[]))<{[]()}{<>[]}>)<[[{}][{}[\n" +
            "[<<[<<{[[<({[[{}[]]([][])]<({}[])>}<{{<>{}}<[]()>}{([]<>){()<>}}>)<{(<()<>>(()<>))(<<>><()<>>)}>>]<{<<\n" +
            "<{({(<<(<{({<({}<>)>({{}}{<>()})}{<(()<>){<>{}}>{{[][]}({}[])}})}>)>>([<(({(({<><>}({}[])){{()<>}[[]]})<((<><\n" +
            "(<{[[([[<{[<{<[]()>([]())}((()<>>{{}{}})>]}>]]{<<<[(([[]<>]<{}()>)({<>{}}<()[]>)){<{{}()}<[]<>>>[\n" +
            "[[{[<[[<<{<([{()[]}{{}{}}]<<[]{}>>)<[[{}<>]{{}[]}]{[{}()]{(){}}}>>({([()()]<[]<>>)<{{}()}(\n" +
            "((([{({[{<{({{[]<>}[{}()>}[({}[])[[]()]])[[{<>[]}{{}[]}]<[(){}]{()()}>]}{<{([]<>)}>([{<>{}}]{\n" +
            "[(<(<({{{((<[<<>[]>{()<>}][[[][]]<<>[]>]>[[<[]{}>[{}{}]]{([]()){<>())}]))([(<<[]{}>>)][[{(()<>)([][])}]{<(<>\n" +
            "{<[[<<{({[(<(({}{})(<>{}))<{<>[]}<<>()>>><(<()>({}()))[{[]{}}(<>[])}>)]}){(<{(({{}[]})<(<><>)([]())>){[([][\n" +
            "[[{[<{[((<[<<<<><>>[<><>]>[<<>>{<><>}]>][{{<()>{{}[]}}{({})[{}()]}}[<[[]{}]{()()}>({[]()}(<>{}))]}>{{<([\n" +
            "{<<({<[<(([[[[[]()]][<{}<>><()()>]](<<{}[]>[<>()]>[{<>{}}[()]])])({(({{}[]}<<>()})(<<>()>)){[{()<>}{(){}}]\n" +
            "<[[<<[{([(({{{<>[]}([]{})}<[[]()]<()()>>}[<({}[])[[]<>]>[<{}()>[()()]]])[{(<[][]>{{}<>}){{<>()\n" +
            "([[<(<({({(<([<>[]][[][]])[<()[]>[[]()]]>{((()())([]{})){<{}()>{<>{}}}})}[<[<[()<>]{[]{}}>({[][]}(\n" +
            "[<{<([{<[{<[<([][])}([[][]]<<><>>)]><(<(()[])<()<>>>({<>{}}[<><>]))>}<(<(({}()){{}[]})[<<>[]>]>){\n" +
            "{{[<{<{([<<[{[()()]{[][]}}[[[]{}]{{}[]}]]{<<{}()>[[][]]>{{()[]}(()())}}>{[{[(){}][[]()]}<<()>\n" +
            "<{[<{[{{(<<(<<{}>>{{()[]}({}{})})<{[<>{}][<>{}]}((<>{}))>>([[(<><>){()<>}][(()[]){[]<>}]])>)}<<{{([<<>\n" +
            "{<({<<{((<{<(<{}>[()])[[(){}]]><<[<>[]]>(([]())([][]))>}<[{<{}()>[[]{}]}[[<><>]([]{})]]>)<[[<{[]}[()[]\n" +
            "[{<{[[[([[((<[[]{}][<>{}]>[((){})([][])]]({[[]<>][{}()]}[(<><>){[]<>}])){[<[{}]><[()()]>]<<([]<>)(<>())>{<<>[\n" +
            "[{[{<([[[(<{<<[]{}>([]{})>[[<>{}]]}>[<[[{}]<<>{}>]{<{}<>>{<>{}}}>([<{}[]><(){}>]<(()())(()<>)>)])<<<[(()<>)(\n" +
            "[(([<{(<({<<<(()()){(){}}>[{<>}(<>{})]>[{([]()>(<>())}<(())[()<>]>]>(([<{}{}>]{[[]{}]({}())})(([<>{}]<()<\n" +
            "{[{([([[[{<{[<{}()>{{}{}}]<{<>[]}([]<>)>}[(<<>{}>({}{}))<<{}()>{{}[]}>]>}{(<[[()()]}>{[[()()]<{}[]>][[()[]]]\n" +
            "[<([[{[<<[{[{({}[]){{}{}}}]}]([({{()()}}<[[]()][{}[]]>)([[()()>])]{<{<()[]>[<>[]]}<[{}{}]{<>()}>>\n" +
            "<<(<[<((((((<{(){}}[<><>]>)[<<()[]><{}[]>><<()[]>{{}[]}>])[(<<()}>([{}<>]))({<{}><[][]>}{([]())[{}<>]})])){<\n" +
            "{[([{[[(<{<{{<{}{}>(())}}>}(({{{[]()}<{}[]>}{([]{}>{<>[]}}}<{<[]()><()[]>}<[<>{}][[]<>]>>)((<[{}[]]<[]<>>\n" +
            "<{{{(([[([{{{<(){}>{{}()}}{({})<()[]>}}[{(()[])({}<>)}[{[]{}}{()<>}]]}]<(<([{}[]](<>{}))[<()[]\n" +
            "<({({[<<([{{({<><>}[{}[]])}{(<<>>([]<>))<{(){}}{<>()}]}}])({{{[{(){}}[(){}]]<[()<>][{}{}]>}}}{\n" +
            "(({[(({({((<({[]<>}{[]<>}){[[][]][(){}]}><<<{}[]>(<><>)>(({}<>)({}<>))>)<{{[[]<>][<>()]}}<[(()\n" +
            "<([{{[({([([<{()()}<{}>>{((){}){{}[]}}]<<[[][]]({}<>)>((()())[[][]})>){[<[<>()]<{}<>>>(<<>{}>[[]{}])]{[[\n" +
            "{[[[[<{([<[({[[]()]<{}{}>})((<<>{}>[[]<>])<(()())([]())>)]({{[[]<>][{}[]]>[[[]<>]([]{})]}({{(\n" +
            "[<{<[[<[[({([{{}<>}{<>}][{{}[]}{(){}}])[{<<>()>)([<>()](<>[]))]})]][({[[(([]<>)[{}()])[({})(<>\n" +
            "([<{{{<([{[[([<>[]]<{}{}>)[({})[<>{}]]]]{<<({}[]){[]()}>[<{}()>]>[([{}[]][()<>]){[()()]<<>()>}]}}<{({{()()}[\n" +
            "{(({[({<<[{[(([]{})<{}{}>)({[]<>}[<><>])]}({({()[]})(({}<>)[{}]}}([{[]{}}(()())]))]>(<[[[{[]{\n" +
            "[{[((<{[{<[([{<>[]}<[]{}>]({()<>}[[]{}]))<[[<>{}]<[][]>]<{(){}}{[]{}}>>]<({(()()){[][]}}{{[]{}}{()<>}}){\n" +
            "{{<{({{<<({{(([]())<<><>>)((<>())[{}{}])}{((<>{})[()<>])[(()<>){{}{}}]]})<({[[<>{}]<{}<>>]([<><>\n" +
            "{<[<[{[(({((<[<><>]([][])>{{<>{}}<{}()>})(<{{}{}}([])><{()<>}(<>{})>))(<{{()<>}{<><>}}[{{}{}}([]<>)]>)}[\n" +
            "[<[[[(({(([({{<>[]}<()<>>})([[{}()]([]<>)]({<>[]}[{}()]))])<{(<[[]<>]{[]<>}>){{{<>[]}}(([]{}))}}<[<{<><>}{\n" +
            "[<{{(<[(<<([<<{}[]>[<>()]>({<>[]}{<>})]<[{[][]}[()]]>)({{[()<>]<<>{}>}<(<>[])({}[])>})>((<{{()}}{<()<\n" +
            "[[(([[<{<<({<(()<>)[{}[]]>[<()[]>[()<>]]}(((()())[{}{}])))>[([({{}[]}([]<>)){({}<>)([][])}]\n" +
            "<{<<<<[([([[(<[]<>>){{<>{}}[()[]]}]]{[<<()[]><{}[]>>[<()<>>([]())]][{<<>[]>[{}()]}]})][<[<[\n" +
            "({{<[<[(([(([<()()>{()[]}][(()())[{}]])(((()[]){{}[]})({[]}{[][]})))]<[{[[[]()>]<[<><>]>}[(\n" +
            "<[<[[[{(([<(({{}{}}<()[]>))>{[<({}<>}([][])>]<[(()()){()()}]<<{}[]>({}())>>}]<<[([<>]([]<>))[{<\n" +
            "{[[<[(<(<{({[[[]}{<>{}}]}({{{}()}<{}[]>}<<()[]>{[]()}>))}[[{<{{}<>}([][])>[{()()}]}(([{}<>])({[][]}<<>[\n" +
            "(<({[{[<<<({[[()[]]]})>>{[{{{[()()][[]{}]}{{<>{}}(<>[])}}<{<{}{}>(<>())}<{[][]}[{}[]]>]}<<[<<>()>\n" +
            "[[(<(<{<(<{<<{{}<>}<<>{}>>{{()<>}(<>())}>}>){[[{<<<>{}>[{}<>]><{[]}((){})>}[[<<>[]>{<>()}][{{}{}\n" +
            "{[((((({<({({(()())}([{}<>]<()[]>))([<[]>[[]()]]<[[][]](<><>)>)}[<[{{}<>}[[]<>]]>{<<()[]>{{}()}>[{<>}{()[]\n" +
            "<[{[(<<[{[<<<<[]()>[<><>]>[<{}<>>]>>]}[([[<({}())[{}()]>{({}{})(<>)}]]([<{[][]}((){})>[{{}()}\n" +
            "[[{[[({([<<{[{<><>}<[]{}>][[<><>](()[])]}>[(({{}{}}([][])))((<{}{}>[{}{}])({[]}{{}}))]>]{(\n" +
            "{<[(({{<[(<[{({}{}){(){}}}<(<>{})({})>]<(<<>{}>)<{{}()}<<>[]>>>>{[<<[]{}>{[]()}>{[[][]][()[]]\n" +
            "({[<[{[<[{[(([{}[]]<{}[])){{{}[]}<{}()>}){[{()[]}(<>{})][{<><>}]}]{{({{}<>}([]{}))}{((<><>)([][\n" +
            "[[(<[{({({({<{{}[]}<()<>>>[{(){}}{<>[])]}<<<[]()>>{<()[]>({}())}>)(<[{(){}}([]<>)]{[()()]<{}>}>\n" +
            "<{[{[(({{<{(([<>[]]{[]{}})({{}[]}[<>()]))[[{()<>}<{}{}>]<[()<>]<[]()>>]}<(<(()<>)[()[]]><[<>()}{[]{}\n" +
            "(([(<[{[{[({<[[]{}]{{}{}}>}([<[]()>(()())]))<(<(()[])[{}[]]><[()<>][{}<>]>)<(<[]{}>]{({}())[<>\n" +
            "([{<[<[{{((<<{{}{}}[[]{}]><[{}{}]<{}{}>>><({<>{}}([][]))((<>[]){{}<>})>)[{[([][])[[]{}]]<[{}][<>()]>}])([[<[\n" +
            "[{({((<({(({({{}<>})({()()}(()()))}(<<[]{}>><(<>())({}<>)>))<[[(<><>)[[][]]]<{()<>}[[]{}]>]<((\n" +
            "{({{({({[{([[{[][]}([]())]<([]())<{}{}>>][[([])[{}<>]]{{<>[]}<<>{}>}])[<<{<><>}{{}<>}>>{<{\n" +
            "{{(<<(([<{<((<<>{}>{<><>})[([]()){[][]}]){{{[][]}[()]}[({}[])]}>}[<[<{()<>}<<>()>>]<(<(){}><()[]>){[()[]](<>\n" +
            "{((<(<[[[<(<<[()()]<[]<>>>)({(())[{}[]]}))<{<{{}<>}>[{[]}[()]]}<<(()<>)[[]()]>(<(){}>[<>[]]\n" +
            "<<{<<{({({({({{}()})({{}<>}([]()))}(({()[]}[()<>])[<()[]><[][]>)))(<<[[][]](<><>)>(((){})([\n" +
            "{<<{(<{{{{((([()[]][[]{}])[{()<>}{<>[]}]){{{<><>}}(([])[[]()])})[((<{}()>[[]<>])({[][]}{()(\n" +
            "[((<{{(<({[<<[<>{}]{(){}}>([{}<>](<>()))>][[([[]()][[][]])<{[]<>}{[]}>]<((()<>))>]}((<[([][\n" +
            "(<<([(({(([[([[]<>]{<>{}})(<{}[]>[{}<>])]<([[][]][[]{}])>][<<{<>{}}<<>[]>>[<<>{}>{()()}]><(<[]())({}())\n" +
            "(({[{([({<<{{{{}{}}(<>())}[([]<>)(<><>)]}<{{()}}<<{}<>><(){}>>>>>}>([<((<{{}<>}[[][]]>{{<>[]}[()\n" +
            "([{(<([<[<([({{}<>}<{}[]>)][{[{}()][[][]]}[(()())(<><>)]>)><([<[()<>]<(){}>>{[<><>]<{}<>>}]{\n" +
            "[<<(<[({((((<(()<>)[[]{}]>[{<>[]}{()<>}])(<<()[]>(<><>)>{(()[]){[]<>}}))({[{()<>]{()<>}]{{()()}(<>{}\n" +
            "([<<{({({(<{{<()<>>(<>{})}[<(){}>([]())]}[({{}<>}([]))]>([<({}{})[<>()]><(<>[]][(){}]>]<[<<>[]>({}<>)][{<>}\n" +
            "[<[{[[{[[{[({([][])([][])})[([{}[]][<>[]))<[[][]]<()<>>>]][<([()[]]{[]<>})>[{({}<>)[{}()]}]]}][(\n" +
            "{<<<<[[<{{[{<[[]{}][{}{}]>}(<<{}<>>(<>{})>({<>()}<()<>>))]<<<(<>())<()<>>>><[<(){}>[<><>]>>>}}[(({(<<><>>({\n" +
            "<[<(<([((<[<(<{}[]>(()<>)){({}[])([]<>)}><[{[]<>}{<>()}]>][<<{<>[]}{(){}}><[()<>]{[]}>][<({}<>)>]]>)[[[((";
}