package pl.karolsiwek.aoc2021.day08;


import java.util.*;
import java.util.stream.Collectors;

public class Day08a {

    static Map<String, Integer> digits = Map.of(
            "abcefg", 0, "cf", 1,
            "acdeg", 2, "acdfg", 3,
            "bcdf", 4, "abdfg", 5,
            "abdefg", 6, "acf", 7,
            "abcdefg", 8, "abcdfg", 9);

    static Map<Integer, List<Integer>> digitByLength = getDigitsByLength();;

    public static void main(String[] args) {
        int counter = 0;
        for(String line : testInput.split("\n")) {
            if(!line.contains("|")) {
                counter+= Arrays.stream(line.split(" ")).map(digit ->getDigitByLength(digit.length())).filter(elem -> elem>=0).count();
            }


        }
        System.out.println(counter);


        counter = 0;
        for(String line : mainInput.split("\n")) {
                counter+= Arrays.stream(line.split("\\|")[1].split(" ")).map(digit ->getDigitByLength(digit.length())).filter(elem -> elem>=0).count();


        }
        System.out.println(counter);
    }

    static Map<Integer, List<Integer>> getDigitsByLength() {
        Map<Integer, List<Integer>> digitByLength = new HashMap<>();
        new HashMap<>();
        for(int i=0;i<10;i++) {
            digitByLength.put(i, new ArrayList<Integer>());
        }

        for(Map.Entry<String, Integer> entry : digits.entrySet()) {
            digitByLength.get(entry.getKey().length()).add(entry.getValue());
        }
        return digitByLength;
    }

    static Integer getDigit(String code) {
        return digits.getOrDefault(Arrays.stream(code.split("")).collect(Collectors.toSet()).stream().sorted().reduce((a,b) -> a+b), -1);
    }

    static Integer getDigitByLength(Integer length) {
        if(digitByLength.get(length).size()==1) {
            return digitByLength.get(length).get(0);
        } else
            return -1;
    }

    static String testInput = "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe\n" +
            "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc\n" +
            "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg\n" +
            "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb\n" +
            "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea\n" +
            "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb\n" +
            "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe\n" +
            "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef\n" +
            "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb\n" +
            "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce";


    static String mainInput = "gfeabcd adecfb gcedb cef efgdc decabg cbfg edfga fdegcb cf | fc faedg fdage fec\n" +
            "bcfegd fdceg ecgfadb fdae af cdeagf afc abdgc gcadf gfbace | cefdbg afgedc bcagd gedcf\n" +
            "gbf bagdec fgdbae cfbd bgacd fb fbcaegd dfgbac gbfca gecaf | gadcfb abgcf bdcgea gbf\n" +
            "eagcf cdgbaf cbadg fbcdgea cebfad fb fdgb abf gcdbea cgabf | bfdg dgacb gacef bafcg\n" +
            "bgface cfadb gfc gcfba febg aedcgfb bceag cdgaeb adfecg gf | acfdb fabdc efagdc facbd\n" +
            "bfegac adbfg ebagf beafgcd gfeadc age cgedbf gfceb eabc ae | acbe gfcbea aefgb abdgf\n" +
            "bfeag cbae cbg bc cbaefg cfdga fgdbec ebadgf eacgfdb gabcf | bfgcea eabc eagfb bc\n" +
            "abdcfg aebfd fbcade cdbge cfae eafcgbd dcabe bca degfba ca | ecfa fcadegb edbfag abc\n" +
            "dfcgea efcbg ecda fadgbc fac bdacegf ac feabgd afgde ceagf | fdgae abgdfc egcfa fagde\n" +
            "gfd gdcab fgdcae ecdfb bfdgc dacbef fbeg fg fdcgbe cdagbfe | bacgd cfdbea efacdg bcaefd\n" +
            "eb afbgd gafbe bef gacdfb gdecfab aecfg egdb defgab bdaefc | gacfbd bfaecd gabfdc bef\n" +
            "ecfb cbdfeg fgbadec fgcdb egf cefdg eagdc fbcdag fe gadbfe | fbgade bedfag gafbdc ecfdg\n" +
            "bafcdg gbd dbcgef ecgdf cbged cgabe faebgcd cgfeda db debf | bdg efdgc fbed gbcde\n" +
            "gdfceab afced decafb gadfc fg agdbc befcga fegd cgf cfegad | egdf ecafbg gbcda dcagf\n" +
            "eca ea efda cfbda badgcf dbcea edcfab abfcdeg bagfce cbdeg | efad fedcba fgcbea adfbce\n" +
            "gdefbca cf acbfg cegbda begca fbc gfebac afce gbdfa bdfgec | cbf bfc gecbaf cbafg\n" +
            "afcebd agebfc fbcea eadfbg bdf cdfa dfecb df cbdfgea becgd | ceagbf fcda becfd ebfac\n" +
            "ecb adgfeb fedbgca efac cfdegb egbaf ec bgcda gefbca cgaeb | afbdgce bec ec feca\n" +
            "eacdb dfcabe dg fdabgc cdg gaed cbaged gecdb ebgcf dagefbc | debgc dgaceb gcd fdebca\n" +
            "cega gdbefac gfdcb efbgca ga afg cgbaf aecfb ceabdf edgbaf | fedagb dbafce ga ga\n" +
            "efbadc acgde fagcde fe dcaebg edfg acgef cbagf fae acfebgd | ef badcef dfge gecfa\n" +
            "cbgfa cbae dabgef ba bag fcgea fdcage gfbcd cgbdaef gfecab | aefbgd bfgac gebacf bgfac\n" +
            "dgfbce fgc bfcda egdfa gc fgedab acgdf acedgf aegc fbcgade | dfagc bgdafe aegfd egfda\n" +
            "dgfa bedfc badcf agfbcd af eadbcg bfegca bfa cgabdef bgadc | bgdacf dcfba fcbda gbedafc\n" +
            "aceg gdaef fcbegda dcefab afcedg abgdfc cadef agf degbf ag | bdgfe bacfgd cadfe fcgeabd\n" +
            "bgefcd edg acdegb edcba cbdefa cfaeg beacgfd egcda dg dbga | efagc abcdegf adfceb dg\n" +
            "faedbc gabde becfgd caebf acfg eacbg cgabfe cdebfga gc gcb | cdgebf cefab abdfce gdcefb\n" +
            "de cgdfb edbg dbacfge cdfeg cebdgf agcef afbcgd dce dbfcea | beadfgc gbcadf dgcef degb\n" +
            "egcafb cgadfbe gdace fd bafce cedabf bgdefa dcfb afd ecafd | gaefbd dfcb aebcf gcabfe\n" +
            "cb beafcd bdgacf cdb bgac edgbfac cgfed bcgfd bgfdae gdabf | bafdg gcba fbgcd cegdf\n" +
            "fceba ag degafbc bfcga bcafeg agf bafedc gace bfdcg fedbga | bdefca fcgbd cbafe cgbdf\n" +
            "cafbge ce fbcea fecgdab aec adbcgf fbgca bgec baedf fegdca | defab bcfae ecgb aec\n" +
            "dgbc dagfcb acfgbde bad adcfg dabfc gacdef aebdgf cabef bd | acfdb defgca dbegfac gfdca\n" +
            "ecdf degfb adbge fcbgea dbgfec gfd df egfcb gacdebf cdfgba | cgbdfe gfd cfegab gfabce\n" +
            "defcbga fcade efbac fb gacdbe abceg abcdgf egbf fcbgae bfa | befg cabdge eafcb bf\n" +
            "cfbdae cg decbfag egfab fgdc dcbfe fegbc gce gdebca bfdgec | dcfg eadgcb ebdfc gfdcbe\n" +
            "eagcdf cfaeg dgfe bdcefa gcabfed cgbda cegfab dae cagde de | dae adbecf edfcga fdge\n" +
            "egfa gfbeac cfbag af fcgbe egbdfc abf cgbda ebfadc gbaedfc | cagefb edcbgf gebfc egcfb\n" +
            "gda ga bcadge gfea dcbgef adcfb fdebg cgbfdae gfdaeb fabdg | fegbd egaf dafgeb gda\n" +
            "gefad fegbad adecbfg db bed eacgb fcdgeb agdbe adbf acgfde | fabedcg bed gfadeb gedafc\n" +
            "fb cbefa baf dfbgca edfac gefbac eadbgc efbg gfecbad aecgb | dbacfeg degabc fgacbed febg\n" +
            "cbgfad eabcfgd cfdeg fbd adegb fb cfeb egbcfd fbegd agdefc | fdaegc febc fb ebgad\n" +
            "dbfgc badc cfdabg fgaceb degfab facegdb dgb efgcd fcagb db | gfbca cgfbd gacfbed fbgdc\n" +
            "efgcbd efbdc ef febdag dagfbc fcge bfdcg dabcefg edf ecdba | cbdegf defabg dcgabf bcade\n" +
            "ecdfbga cbdeaf bfgdc fca eagdfc eafdb bdfca ca caeb gfeadb | cbae dcbfa acf fgcbd\n" +
            "gdefcba bafd efcdab gecdbf fdc becfa gceda fd cafde baecgf | dfaec cabedf abcfeg df\n" +
            "cfb daefb bgfcde dgacb edfcagb acfe fdbcea dcafb cf febagd | fecdba dfcageb fabcd ceaf\n" +
            "adgbec fcgb badgecf dagcb bagefd fdb bf dcbfa bfadgc cfdea | fb efdac cfdba gadcb\n" +
            "afcgd bac gdebfca bcgaed afbcd ecdfgb dcfbe ba befa eafdbc | dcgfa dgbfce ab afcbed\n" +
            "gbcdaf fdgac bagde cgdbaef eabgcf bf gecdfa gbafd cdfb fgb | aecgdf bdega gafebcd aefgbc\n" +
            "gfeb cadfg bdfga aefgdb bgd bagdcfe bgeacd gb cebdfa efbda | dafebc bfadge dgb adcfg\n" +
            "cda dageb fgebda bfcdage dcbeag cgade cefbad ca cbga cfdge | gbadec febcad beadg cfbdea\n" +
            "gdabf eadgf fe defagcb abef feg gabcdf cedag dafgbe edgcbf | abgfde gedafcb cgeda adgec\n" +
            "fbd gfeba fgecad edafgbc dfcbga abdgf cadfg cgdb bd aecfbd | agfcbd ebacfd bafdg dagcf\n" +
            "cadefb cgde eg aebfcg fdbagce afdceg fbagd aeg dfage aedfc | gdaef fedca eg ecabdf\n" +
            "dgbeaf fcbdga ae agfcd egabdcf deac dcgfae acgfe fbceg gea | aecd dace gcbfe gcfea\n" +
            "dafeg fcg degbac gcdbe dcgef bdegcf gedbcfa cf ebfacg bcfd | agfebdc cegdf cf cf\n" +
            "bdfcea bdgae ecbga dbg fegbda bdefa dg dbacgf geafbdc efgd | bdg gefd cagbe debga\n" +
            "aegfdb afcbd cdaebfg dgafbc cagb ba dcefb fba cdagf fgadec | fedcb acbg bfcdga gfcaed\n" +
            "bfagcd agfb afcdg fcegadb dfgecb gb adbec gabdc efacgd gbd | eacbd adcfg fbgadc dbgca\n" +
            "fcedg dfgbea ea fcbadeg ade agefdc afcdb egca ceadf fegcdb | dfgce efcgdb dgecbf cfabd\n" +
            "dfea ed agdfebc eabfc bcdgfe edc cdbga ecbda aefdbc acfgeb | bdaec abfec gbedcf acbde\n" +
            "dgefcb fbcaeg fbcga bgfcaed bfgda dcbfag fgd cfda edbga fd | bgcfae abgdf acfgbe abegd\n" +
            "dgabfc cgdfe afd fdcae af agfe decba fcebdg dgfcbea dgfcae | fcaed fcabdg eafg fa\n" +
            "gafce debca gdfeca dfag dacbfeg fcbedg ceagbf fdc fd cadef | ecfad gcbefa begacf edbcgf\n" +
            "daceg efdcbg fcgad dbeca bdface bcfagde abge ged aecgdb ge | ecgdbf egcad eg aebg\n" +
            "fdbace debagc cgbef degba bacfedg gfda afb edfgba gbefa fa | fbdgcea befacd cdaebg fbaegd\n" +
            "fgbdea dg gdfb gfcebad abfeg afecgb dagbe edcgfa cdeba edg | egd fbgd dge eacdb\n" +
            "ge agdcfb fdcag fcbgaed degcf eadg bdcef acefdg bcfaeg gfe | aecfdg eg dcgfa caegbf\n" +
            "bcgef gfdb bgfcae fcadbge cedbg edafcg dg dcebgf bacde dge | agefbc bgefcd bedfcg dg\n" +
            "fga ga dcgfb dgeabf beadfgc eadfc gbca dcagf fadcbg bcgfed | ga dgcfba agcdf befcdg\n" +
            "dbag cafgbe cdfgaeb gabcde eabdc fdace bd bgcea gdecbf bcd | afdec abegfc gebacf edgabc\n" +
            "fcde cbadefg gdcefb bcdagf bcgef fdg abgcef edbfg df eabgd | egabd dbega gfcadb dbega\n" +
            "bfdag adbefc efdgcb fceab dbc cfdba egafbc dc ceda beacdfg | dbfcge cd fbcea efacb\n" +
            "dgaeb cgbeadf afbeg gdbfae ebadcg gdfb acgedf fg abefc gaf | fcdgbae gf bgaed fbaedg\n" +
            "cfdbeg dcfaeb cagbe cfgabde afdc fbaed cfe bfaec bgadef fc | fcaedb gbeac cfdgbe afdc\n" +
            "ceg dgecfa abecfg agcdfbe gcdae gfdcab fdec gbead ce acfdg | cgfad fgebca adbfgc dagcf\n" +
            "dcf cgebfa dc gdcaef eacgbdf cdefgb cebd dagfb cdgbf cfgeb | cd bgadf dbfga dgfba\n" +
            "gebac caefd bf bgacedf ebfdgc dbaf cfb bfeca agcedf dbcfae | debgcf adcefb befgdc gaecfd\n" +
            "abec bafgc fcebg fbaceg dcefgba degfb gec acdfeg ec fgdbca | dbgfe gdbfe ecgbf bcea\n" +
            "egafbd egdbc bdc cbfdag fcde ecbdfg fgbde ebcag debgafc dc | cdbeg fabedgc fgdecba dc\n" +
            "ecdgfa ag badcegf gaecb eabgfc adecbf cag acebf fbag dbceg | ebgac bfag dgacef edgacf\n" +
            "afgeb adgcbe efdb fgeacbd ceagf gbdefa eb bea dacfbg dabgf | eab bgfdea abfge bdfcage\n" +
            "egbdac aefdb fgdeb dabfc ea dae dbacfeg cagbdf caef eabdfc | cadbf ae dafcgb beadf\n" +
            "egcadbf gbce debac dge adfebc ecgad gdcfa dgcbea gdafeb eg | dgbfae ge adcegb bdfega\n" +
            "fecag dc fgecd dgcb edc fbedca dbcfeg gbedaf gfdbcae ebgfd | dce gbdc edgbfc fedcba\n" +
            "cafebdg adcfge debfac dgcae gec fbdegc ge cgdab faeg ceafd | gafe bagcd edfac cbagfde\n" +
            "dc bgcaf gdcebaf ecgdab cagdf efabcg dgc dcfb fgeda cgbafd | gbcfea dacebg bgfdcea dc\n" +
            "afedc ebfcg dgfbac adbe eafbdcg fdbce bd fbd bdafec afcdge | baed cfgade becdf dbcfea\n" +
            "bdae fbdac deafcb badcegf bcefga badfgc efcgd eb dcebf ecb | bcafdeg eagfcb dcbfag fcdbga\n" +
            "gb dafbce feagd bdegf bdcgafe fdgebc acdgbf cfbde ebcg bgd | adgfcb cgeb bceg dgbcfe\n" +
            "cbdfge bdceag fc edbgc dgfae bcdf fce bcefdga dgefc ebgafc | cdfge bgecaf fdgce cf\n" +
            "ba fcgae gecbfd cefbd cba afdbce aecfb beda fagdcb facdbge | ebad ecagf ba deab\n" +
            "ba baf ebacgf fcgad fbcdeg bgcef gadbecf ecfadb acbgf geab | cgdbfe ba dbceaf adbecf\n" +
            "cdfga efd efagcb egbd ed gacedfb fbdeca decgf gbcef gfbdce | cfedg bedcfg gecbf gbfce\n" +
            "fegbca ega egfdca gfeba afbec fadbg defacb gcbe eg dfagecb | ebacdf bdgeafc dfabg efabc\n" +
            "fdb efcbad bdaeg fbgade gfbe egacfdb adfgc bf dcebag badfg | dfcga bfd adfcg dgbaf\n" +
            "ead agbcd de bcgeda gedb agced cdafgeb bfcgda adefcb fecag | cbgead acdgb edacbf dfeabc\n" +
            "cagdfe egfca geacd dg gbaecf ged agfd cebda edcbgf decafbg | gbaefc ecgaf cgbaef gcfdbe\n" +
            "fd agcfedb adgfb cdaf degab bfacg cebgfa bdgfec fbd dcgbaf | abdgcef dgacbf cfda cedfgba\n" +
            "cafbd fgcb bgcad gdfcba eadbf adecgb cfa efgadc agcdefb cf | fc fdbcag fgeadc fegdca\n" +
            "dgcf eadcg fg dabfe gdefa efg cegabd fadecg gadfbce facbeg | aecbgd gceda gdecab abcgfe\n" +
            "ebfdca agbdcf gfadc bfc afgb cdgaef egcdb gbedfac fb dgbfc | ecbfadg baefdc bf dbgec\n" +
            "fabcge cafged beg abfdge aegdf dabg fgbacde bg fdebg bcfed | efgda dgefba gaefbd bagd\n" +
            "gbfda afbcgd acefdg adb bd gbfae dfcga acegdb acfdegb cbdf | dbfc abd bd dcabfg\n" +
            "ecgdf abcedf gfcdba eadb ecb fdcab fecbagd be eacgbf ebfdc | gdbcfa ecfdab deba efbagc\n" +
            "ca fcda efbdgc ebgda bgefacd cea cdebf bdefca efagbc ebcda | fgebca egabd edcbaf acfd\n" +
            "agebd dcge ed gaebc fegacb cfaebgd bcedfa gfbad dbe cdgeab | dbega fbeacd gaebcd gfcabe\n" +
            "bg edfagb daecgf bgafdce bdeag cgbdaf ebgf ebdac bga edfag | dabcgf cbaed ecdba abg\n" +
            "efdcg afbd bfdeg bgf fgdaecb dagceb fb gcbafe abgdef egbad | fbeagd bcdafge gdbfe fgdbe\n" +
            "becfa fg dgfe dfaegcb cedfbg begdca fgceb facbdg bfg gebcd | fg gebdcf dcbega cdegb\n" +
            "edgacb cb cefdba gcaefd adcbg cdb cbge efgdcab gdeca fadbg | gcabfed cb acgdbe gbafd\n" +
            "cfgbde eagdbc cegdb ebcf bdf fb fbgde facedgb eafdg dcbgfa | cdfagb cfbgde bf dfegcb\n" +
            "gedcfab ebfcda ecdbf gfced fcebdg gcd gdeaf cg cdbgae fgcb | cedafb gdfecb cg gc\n" +
            "fbe edbfca feadbgc cbefga fb dfaeb abdge eacdf gfedac fbdc | fadeb acbedf fbdae daecf\n" +
            "dgacbef efgba dgbfe fecabd bgaefd bde dfcbg de aedg bfgeca | bdfcg ebfdac eagfb gdae\n" +
            "efbgcd aedgcf acd cafbd gbfdaec bagc bcfgd ca gdafcb dbfae | bcadgf beadf ca fgcadb\n" +
            "edgfba fbged aed gadcb bdgae ea efga cdbefa cbefdg dcabgfe | dfbcae bfceda ae gbaed\n" +
            "gdc dfgbeac adcf bfecg abgfd bcdfg dabfgc cd fbgdae dbeacg | gcbfd fdgba afgdcb gbdcae\n" +
            "ca acd ecfbdg fcdeb cdfbea gcabfde afdce cbaf afedg cgadeb | cdbef ca dbecf agfed\n" +
            "gfceab ed fgecd fdgaec gdbfc eacfg cdegfba dcae deabgf efd | eagdfb ed cefag aced\n" +
            "baced dg deabfg dga adcge gcfd acgefd acegf acbdfge fabgce | egfdac becad aebcfgd gcdae\n" +
            "ed dec cdabgf acefb ecbfdga becagd gdbac cedbgf adeg edcab | bdagc acebd bdfgac gdfbcae\n" +
            "gefbca edbfc agdf bgadce fegadc ceagf gafdbce ecfgd dg gcd | gdfaec bfecagd gacefb acgbefd\n" +
            "cdega fcdbga gbfceda gafbde adgcb egfcd ceba dea badecg ae | aebgdc dfbage badfge edgcf\n" +
            "egcdfba ebcga gfe adgcef gdbf bedgaf gabfe dbefa fg cbdfea | gdfaec dfbg abedf gcdfae\n" +
            "feabc gdfeab ebgdafc fa cebga cadgeb gafc fbgeac ebcfd fea | gcabe cbgea febadg fcag\n" +
            "afdb agdfe bdcafge fdaegb aegbd gefdc abdecg aecfbg afe af | fae adfgbe af degab\n" +
            "fdg cdafgb fg cdbef bgfde gfea edbag ebadgc bgaefd adfcegb | befgd dfbgca baedcg gedba\n" +
            "aedgfc afegbcd ag bcedga bedafc aedbc agd gbcda baeg gfcdb | edabc gaeb afedgbc agfdce\n" +
            "eafdc db fcgbe dbac dcbfaeg ecfbad bdf bfced beafgd aedcfg | cdba bd cbafed db\n" +
            "cf fcebgad adfeb afdbce afgcde debgc cbdfe cafb cfd dafgeb | bdecg aegdfc cf dbcegfa\n" +
            "dfceab cebgad fgcbade dae ecdgb bgae dagec fdbgce ea dcagf | eagb ceabdf dcfeab badgce\n" +
            "dgf agcfed gacf afcde dgcef dgaefb efdacb egbdc fg bdeafgc | acdef afgc gdeafcb fdegab\n" +
            "abc aegfcb bc aedgbcf abdce eagdc gdceaf bdfae dcbg aedgbc | fbacge cadgbe gcaedf fdeba\n" +
            "ebdagf ac gcbfe agc aebgc gcbfeda cbdgfa edabg dcae acbdge | cbgfad cag dgfbca abfegd\n" +
            "cdafbge bgf afgd adcebg acefb gf dbgca agdcbf gacfb cfdgeb | dagf dacgb cfabe dbgfce\n" +
            "ebgfacd eafgcd dbcafg ebcafd gdeac afeg eac bcgde afgcd ea | fdbgac dfgca ceafbd gfbacd\n" +
            "gfeb gcdbfea dbecg edacgf fbdac efcdb fbdgec acbedg efc fe | cedbg bcfad ef fec\n" +
            "gfbecad faegb gcdfea cfg gc bceg bagfc fagdeb bacdf fcabeg | aefgcb cfg dafbge agbfdce\n" +
            "bfcedg decba agcf abegfd bdceagf bgacfe cg cbgae efgab ceg | fbceag eabgfc gc abgfec\n" +
            "adebcf agbef abegdc bedgf cfbadeg bedcf fgcd gd bcdfeg gdb | cbafgde dbefc gdb abefg\n" +
            "aeg cdabeg cgfadb aebgd ge ecafgdb edbfa acgdb gafedc gecb | cbge agefdbc cgdafb fdabe\n" +
            "febag ceba ceagf dabfge gce cabedfg cadfg efbcga ec fbdceg | edgcbf bgefa fecag afcgd\n" +
            "faecdb cea dabfgc dfagceb baed dgfcae caebf ebgcf ae bdacf | ae fbaecd ebcgf bcfda\n" +
            "gefc efbdga ec bdgfe fcbeda ecadgbf cbgde cedgbf bce bcgda | ce bec ecdbg gcbad\n" +
            "dbaegcf dbgaf agbde dcebfa gfdc fcabg gcfbda df ceafbg dfb | becdfa dbf egafcb bfagd\n" +
            "gfdae gbfec egcdf afbceg bcdf acgdbfe dabcge cfegbd dc cde | fcdgeb dbagec cafbeg gacebd\n" +
            "fg edcgafb fcadb bdgae fgbda ebdfac fabcgd gecfdb gfd gafc | cefgadb fg cbfged gf\n" +
            "bdfea fceba fc agbce ecdgfab gcefad fec aebdgc bcegfa cfgb | dbfae efc ecbfga gdceaf\n" +
            "cdfgb gefcda abdgce beagd ec bcae dbgfeca ecg ebdgc abfedg | dgabe adgcfe fdcbg ec\n" +
            "fbedc egfabd gadcf dcfeg aecdbgf gcea eg dafgec fcdagb fge | bafedgc eacg ecdfg deafgb\n" +
            "faegdb fdb bgfdec bcgda agfebc gcebdaf cedf gdfcb cfbge fd | dbegacf cfaegb gfcaeb befgac\n" +
            "gbacdfe fdgcba befgc fabe bcf edcbg cgfeab cfaeg fb acdfeg | cfeag bcgde ebaf fgcdab\n" +
            "gef dceg edfbc baedcf bdagef eg cfegb bfacg afgbced decfbg | dfegba cfegdb eg gacfb\n" +
            "cba adgcfeb abfdce dgecbf ca ebdacg becdf adfc fbeac fabge | fbaeg gfbceda fceba bdaecf\n" +
            "cb adefc egcb abc bedgac ecdab bdfgca eabgcfd edagb dabfge | bfdega fagcbd abcde agdeb\n" +
            "dgfbae efgbacd gcdf bdg gd bcgdfe bgcde bedac cgbefa bcgfe | gbd aedcb ecfbg ecdbg\n" +
            "gcafedb ecdbfa fedcg dcefga dfgca gbcde gfae ef fec bdfcga | fdgcabe cbadef fcgda ebdgc\n" +
            "eca ea fbadec debcg agef edcga caedgbf fadgc dgafec cfbgda | cbdeg cedgbaf aecdfb dcgea\n" +
            "afc gbafec egbdfca ca eagbfd aecgf abfeg gcba abedfc dfgec | gafbe cedafb afbeg cagfe\n" +
            "agbde eabcgd fe dcfgb efd becdgfa dfageb fdebg fdcage bfea | def edgfb acfedg bgead\n" +
            "gbdec facbgd ag gba bgceda aedcbgf eagd cgabe bface fbdecg | eagbc gcbdfa bafcgde eacbf\n" +
            "dbacfge cagfdb geba cefgda bgc eacgf caebfg cbdef bg fcbge | bg abcegf efgca cfdeb\n" +
            "gaefc cgefdab fdcaeg aegfdb gb badfc gbf bcfag gbfeac gceb | bdafc aefgdb gfcae dfcab\n" +
            "dbceg bcafgd cfabde cfabdge aed ae deacb face egfbda bfdac | cgabdf fgeadb ade dcbge\n" +
            "dfbgcea fbeca cefbg dfgc bgaefd dbecga gfe fg efdgcb cdbge | gcbfed cfbeg gdfecba gecbd\n" +
            "gaced bd edfcag aecdfgb bgad edcbg debfca deagbc bdc egfbc | bdc cdb efcbad dagb\n" +
            "egfca bgea adebfc gfceab cfebg cdefag dgfbc bgadecf ceb eb | dcefag facgeb edbfca eacfg\n" +
            "fged bcaeg fcgbde gdfacb fcadbe bfcge ebfcd fcg edabgcf gf | gfbdac efcdb gfceb fgdceb\n" +
            "fgbdeac facdb agcf fc bfegcd bedfa gbcda bcdfga fcb degacb | bgecad cf dcgbfea gfca\n" +
            "dafbgc cfdga dgafeb db cfdage bcfaedg fbgec bdf dcab cdbgf | cfdgb befadg ebgfc acdbfg\n" +
            "eadgf gdefab ebacd fc eafgcd caf deafc egcf dacbegf bfdagc | egadcfb agfdcb debca fdcagb\n" +
            "dgaecb efd gbecdf fgdcae geaf faecd dfcab bcfagde egcda ef | fe ecagd efd fed\n" +
            "dbaf bf bfg ebfcga degba cedfg adfcgeb edgfb begafd daecbg | cbegda cbeadg fbg cgfde\n" +
            "gdebcfa cbfad ecgfd fbeg dgfbc bg gfcade gdb gdbecf gdbeac | fdcgbe begf acgedb cbdfg\n" +
            "acfdge cadgbf gecfd dbefc fg agcebfd cfg gedac adcbge efag | egaf cbdgeaf gfc bdfgca\n" +
            "fbgad eabg bcdafg bde edcfab fdgec degbfca badgfe dbgfe be | egbdf bgadf bfdge edgfc\n" +
            "gfceab acf ceafbd cagdbe bdaf dcfae af febacgd fgdec daebc | cegfd dacfbe faebcd adfecb\n" +
            "bgefcda facgb bcg fgec cgebad gc efagcb bdgaf fecba dcfbea | bfeac acgfb ebcaf bgadf\n" +
            "eagbd abcgde afedc gcbe bac debcafg bgacdf bc dbcae gadbef | cba adcfbeg efdac cb\n" +
            "fbead gdebfa cabge adfbgce dcb dc aebdc dacfeb dcfa gbfecd | decfba cd dc edcgfb\n" +
            "gcbae ecfbag gebcf edabg efagcd cga ac beagcdf bcfgde fbac | fgecb gdabe agc ca\n" +
            "dfgacb fadbg afebdg acbgefd gdbc cgafd efgac cd beafcd fdc | daebfgc cebfad bgdeaf dcf\n" +
            "cfdeab efgadc cfbag ef cagbed dbcea ecfab cfe befd gecdfab | fgecdba cabde abefc dbcae\n" +
            "fadec cbgade acbfe df cdf acfdeg afdg gabcfed acged bfdcge | efcda ebfca ebgcfad dcage\n" +
            "cdfgeb eabgcdf faegc facgbd gdc efbdac fdbca fdgca gd badg | dfbceg cfdba cfgda bcadf\n" +
            "cdeafg ecfbgd cb eabc aegdcfb cdfba bfc cdfeba edfac dagbf | ceafdg feadc fbegdc beacdfg\n" +
            "acfbde agc cabgf cfdba gfecb bgadce bcgfda fadg cebdgfa ag | cbfad cebfagd agdcbf dcabf\n" +
            "dfeag fgbedca dfceg fcgaed egdcfb edbcga aefc gfadb aeg ae | gefda gabfd egacdf dfbgec\n" +
            "gfbec bdcaegf cdb bdacgf dgcbae gdcaf cfdage bd badf dbfcg | gaedbc dcb fcgdb afdbgc\n" +
            "cgdef geafc fd fdgbac degbc dgcefba cfeabg dfae cafdge dcf | egbcfa defa df dcf\n" +
            "cafed gdbfa cbfade cfgead cb gecbfd gdbcfea bcea cfb bacfd | bcea afdbg cbegdfa afdcbge\n" +
            "ac fcdgeba gdfec bdgefa fedab eac fadce aecbgf dbac aedcfb | efgabdc cafde bacefd cfeagb\n" +
            "begcf eagcdbf cfg bdcfea bfeca cfdagb bfged caeg cg acfegb | bgfde dgafceb dcfeab dgacfb\n" +
            "gbdfca fadbg fbc cb bdfgae abgcf feacg dfabceg dabc cfgdeb | cbf fcgdeb eafgc bdfgea\n" +
            "fcaegbd abgdce fdcgab gf bfgcae bfg gbfea abgce fgce ebadf | fdabgc gcfabe aebgdfc gbdcae\n" +
            "cdagbe gedbacf efbcad ebdag be gbcfad gbdac ebcg ebd egfda | efgda ecgb gbecda bdfgca\n" +
            "bfdgac gdecb abcde daefc efbgcd ecdbga agbe dab ab cfgabde | ebga ab cgbfad eacdb\n" +
            "efgda ac acf dfgbec dfeac edfbc ecfdgba fceabd bfdcga aecb | ecadfgb abcedf cefad cabe";
}
