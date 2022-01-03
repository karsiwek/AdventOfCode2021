package pl.karolsiwek.aoc2021.day07;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static pl.karolsiwek.aoc2021.day07.Day07a.readInput;

public class Day07b {

    public static void main(String[] args) {

        List<Integer> input = readInput(mainInput);
        Integer min = Integer.MAX_VALUE;
        for(int i=0;i<2000;i++) {
            Integer x = getFuel(input, i);
            if(x<min) {
                min = x;
            }
        }
        System.out.println(min);

    }

    static Integer getFuel(List<Integer> positions, Integer toPosition) {
        return  positions.stream().map(pos ->getSingleFuel( Math.abs(pos - toPosition))).reduce((a,b) -> a+b).get();
    }

    private static Integer getSingleFuel(Integer distance) {
        return ((distance+1)*distance)/2;
    }

    static String testInput = "16,1,2,0,4,2,7,1,2,14";



    static String mainInput = "1101,1,29,67,1102,0,1,65,1008,65,35,66,1005,66,28,1,67,65,20,4,0,1001,65,1,65,1106,0,8,99,35,67,101,99,105,32,110,39,101,115,116,32,112,97,115,32,117,110,101,32,105,110,116,99,111,100,101,32,112,114,111,103,114,97,109,10,494,43,989,562,667,505,3,630,175,9,1115,348,1135,186,676,122,776,19,1303,9,263,199,628,352,951,31,589,535,975,1153,331,1253,528,1408,972,660,6,3,130,1057,1061,368,535,198,3,472,341,212,560,231,1384,79,265,99,1748,88,741,129,882,173,51,289,987,12,18,318,167,998,1165,255,113,29,279,608,32,395,118,623,1136,1067,220,213,324,386,392,136,316,311,84,145,7,414,772,636,536,217,221,230,719,221,35,923,75,432,12,629,33,681,830,164,514,272,1780,217,1037,123,216,297,44,4,439,1297,990,31,1084,182,708,873,83,265,224,286,910,486,228,1220,420,10,1197,771,384,564,96,332,855,682,924,983,1579,702,627,469,31,55,40,525,897,194,264,1357,40,892,161,738,503,530,1295,1180,901,683,1217,1446,353,40,31,930,225,1343,1064,167,650,200,878,446,44,185,354,841,43,545,682,196,433,148,71,1020,506,7,579,138,126,513,1232,580,808,507,97,420,217,683,1376,423,559,1372,1077,150,1268,366,93,30,228,538,1405,272,547,1044,38,31,281,287,785,327,391,480,70,206,49,492,27,389,79,37,1068,396,366,217,540,1123,998,298,455,726,33,925,175,41,731,1112,53,513,638,471,397,4,241,271,365,46,35,72,438,151,219,1071,1781,748,157,355,1186,151,926,271,222,1201,1060,34,119,260,266,1276,847,835,343,151,832,189,96,650,785,314,79,1355,129,205,569,865,375,190,126,413,73,14,291,98,43,1058,1375,7,809,6,719,60,258,412,439,619,15,82,407,222,1746,790,535,1221,181,515,615,757,904,58,921,689,205,653,282,41,1840,333,1459,1532,789,228,401,96,429,42,23,35,32,118,900,410,421,240,101,873,277,489,218,173,132,1161,426,1516,187,669,457,59,647,30,232,237,1158,39,815,1756,787,131,814,47,35,993,383,1459,117,101,637,84,1952,213,261,233,1238,76,821,866,314,236,417,951,473,370,187,484,225,199,472,140,1456,106,113,966,60,1543,49,13,6,102,519,111,670,991,325,124,269,28,126,894,781,597,1142,61,534,763,542,327,829,1558,524,15,703,44,643,98,435,54,164,624,387,1047,382,326,517,31,1575,938,1054,544,647,828,322,154,1021,82,1373,5,58,926,556,89,94,150,115,572,75,110,133,1508,273,230,561,80,1839,345,716,1003,1060,226,651,168,79,80,893,819,423,94,15,185,507,88,911,588,1320,249,863,295,447,146,756,317,38,18,179,115,727,316,1472,556,169,111,59,534,34,75,993,10,880,1364,675,1575,36,333,1268,1072,1538,862,746,1149,236,871,737,89,13,692,416,762,26,373,644,1299,256,252,512,900,1011,208,1205,94,820,444,925,1144,530,206,613,142,1543,216,77,279,600,128,148,120,8,24,1680,441,34,433,251,360,1636,205,113,610,1631,765,423,289,463,893,261,568,527,815,24,791,55,16,533,569,121,418,443,479,31,404,592,444,579,691,1560,1480,14,346,599,608,1199,193,84,1097,120,932,1517,244,430,21,1288,82,24,990,721,234,891,410,446,327,547,1410,755,1766,474,159,71,1326,358,597,768,450,392,122,44,971,384,99,406,729,453,1293,323,18,842,766,665,17,57,170,298,0,23,431,377,755,236,6,205,1441,293,163,66,963,1521,765,33,53,295,239,382,1146,100,128,1031,293,87,990,90,1469,168,319,1487,1090,39,250,57,308,277,284,214,1583,832,139,1785,544,674,288,836,482,93,631,786,663,239,791,23,38,897,13,468,81,139,648,189,363,32,962,494,52,603,284,935,305,110,2,1,109,543,354,689,265,333,973,350,618,154,789,848,5,30,223,540,74,8,544,911,197,246,96,562,168,118,384,167,1147,68,867,1041,1082,777,985,96,96,251,33,580,1066,17,135,212,433,355,617,1092,244,166,853,183,145,325,92,138,863,255,556,1420,638,57,119,1081,650,13,984,540,94,727,896,1070,1731,849,255,26,768,1134,540,363,211,657,686,831,168,136,241,398,86,572,191,542,12,1039,57,47,1317,498,390,77,605,267,42,474,313,95,758,823,265,924,540,93,1329,1214,573,263,827,8,140,121,1132,566,37,1604,67,65,8,132,663,1224,6,424,482,631,583,119,1285,91,403,387,472,888,121,236,41,277,481,103,104,1300,44,504,851,277,528,990,457,568,1093,79,34,1001,782,585,688,265,1006,166,293,870,653,41,345,957,607,649,938,381,200,46";

}
