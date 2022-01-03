package pl.karolsiwek.aoc2021.day16;


import java.util.ArrayList;
import java.util.List;

public class Day16a {

    public static void main(String[] args) {

        Packet result = new Packet();
        readBinary(parseHex(mainInput), 0, result);

        System.out.println(result);
        System.out.println(sumVersions(result));
    }

    private static Integer sumVersions(Packet result) {
        Integer sum = result.version;

        for(Packet sub : result.subPackets) {
            sum += sumVersions(sub);
        }
        return sum;
    }

    private static String parseHex(String s) {
        StringBuilder sb = new StringBuilder();
        for(String n : s.split("")) {
            String binary = Integer.toBinaryString(Integer.parseInt(n, 16));
            String padding = String.format("%4s", binary).replace(' ', '0');

            sb.append(padding);
        }
        return sb.toString();
    }

    private static Integer readBinary(String input, Integer idx, Packet toReturn) {
        if(idx > input.length()) {
            return input.length();
        }
        Integer index = idx;
        Integer version = Integer.parseInt(input.substring(index,index+3), 2);

        index += 3;
        System.out.println("version");
        System.out.println(version);
        toReturn.version = version;

        Integer typeId = Integer.parseInt(input.substring(index,index+3), 2);
        index += 3;
        System.out.println("typeId");
        System.out.println(typeId);
        toReturn.typeId = typeId;

        String data = "";

        if(typeId == 4) {
            data = "";
            String toParse = input.substring(index);
            String chunk;
            do {
                chunk = toParse.substring(0,5);
                toParse = toParse.substring(5);

                data+=chunk.substring(1);
                index += 5;
            } while (chunk.substring(0,1).equals("1"));
            toReturn.data = data;
        } else {
            Integer lengthTypeId = Integer.parseInt(input.substring(index,index+1), 2);
            index += 1;

            if(lengthTypeId == 0 ){
                Integer totalLength = Integer.parseInt(input.substring(index, index+15), 2);
                index += 15;

                int subIdx = 0;
                do {
                    Packet newPacket = new Packet();
                    subIdx = readBinary(input.substring(index, index+totalLength), subIdx, newPacket);
                    if(newPacket.version != null ) {
                        toReturn.subPackets.add(newPacket);
                    } else {
                        throw new IllegalStateException();
                    }
                }  while (subIdx<totalLength);

                index += totalLength;
            } else {
                int numberOfSubpackets = Integer.parseInt(input.substring(index, index + 11), 2);
                index += 11;

                for(int i=0;i<numberOfSubpackets;i++) {
                    Packet newPacket = new Packet();
                    index = readBinary(input, index, newPacket);
                    toReturn.subPackets.add(newPacket);
                }
            }
        }
        return  index;
    }

    static class Packet {
        Integer version;
        Integer typeId;
        String data;
        List<Packet> subPackets = new ArrayList<>();

        public Packet() {

        }
        public Packet(Integer version, Integer typeId, String data, List<Packet> subPackets) {
            this.version = version;
            this.typeId = typeId;
            this.data = data;
            this.subPackets = subPackets;
        }

        Integer getDataInt() {
            return Integer.parseInt(data, 2);
        }
    }

    static String testInput = "110100101111111000101000";


    static String mainInput = "620D7800996600E43184312CC01A88913E1E180310FA324649CD5B9DA6BFD107003A4FDE9C718593003A5978C00A7003C400A70025400D60259D400B3002880792201B89400E601694804F1201119400C600C144008100340013440021279A5801AE93CA84C10CF3D100875401374F67F6119CA46769D8664E76FC9E4C01597748704011E4D54D7C0179B0A96431003A48ECC015C0068670FA7EF1BC5166CE440239EFC226F228129E8C1D6633596716E7D4840129C4C8CA8017FCFB943699B794210CAC23A612012EB40151006E2D4678A4200EC548CF12E4FDE9BD4A5227C600F80021D08219C1A00043A27C558AA200F4788C91A1002C893AB24F722C129BDF5121FA8011335868F1802AE82537709999796A7176254A72F8E9B9005BD600A4FD372109FA6E42D1725EDDFB64FFBD5B8D1802323DC7E0D1600B4BCDF6649252B0974AE48D4C0159392DE0034B356D626A130E44015BD80213183A93F609A7628537EB87980292A0D800F94B66546896CCA8D440109F80233ABB3ABF3CB84026B5802C00084C168291080010C87B16227CB6E454401946802735CA144BA74CFF71ADDC080282C00546722A1391549318201233003361006A1E419866200DC758330525A0C86009CC6E7F2BA00A4E7EF7AD6E873F7BD6B741300578021B94309ABE374CF7AE7327220154C3C4BD395C7E3EB756A72AC10665C08C010D0046458E72C9B372EAB280372DFE1BCA3ECC1690046513E5D5E79C235498B9002BD132451A5C78401B99AFDFE7C9A770D8A0094EDAC65031C0178AB3D8EEF8E729F2C200D26579BEDF277400A9C8FE43D3030E010C6C9A078853A431C0C0169A5CB00400010F8C9052098002191022143D30047C011100763DC71824200D4368391CA651CC0219C51974892338D0";
}
