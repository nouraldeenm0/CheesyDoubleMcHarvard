public class Instruction {
    String instruction = "0000000000000000";
    String opcode;
    String name;
    String type;
    String r1;
    String r2;
    String immediate;

    public Instruction(String instruction, String type) {
        this.instruction = instruction;
        this.opcode = this.instruction.substring(0, 4);
        this.name = getNameDependingOnOpcode(opcode);
        this.type = type;
        this.r1 = instruction.substring(4, 10);

        if (type == "R") {
            this.r2 = instruction.substring(10, 16);
            this.immediate = "NOT SET, I am an instruction in the I FORMAT";
        }
        else if (type == "I") {
            this.immediate = instruction.substring(10, 16);
            this.r2 = "NOT SET, I am an instruction in the R FORMAT";
        }
    }

    public Instruction(String name, String r1, String r2_or_immediate, String type) {
        this.instruction = opcode + r1 + r2_or_immediate;
        this.opcode = getOpcodeDependingOnName(name);
        this.name = name;
        this.type = type;
        this.r1 = r1;

        if (type == "R") {
            this.r2 = r2_or_immediate;
        }
        if (type == "I") {
            this.immediate = r2_or_immediate;
        }
    }

    private String getOpcodeDependingOnName(String name) {
        switch(name) {
            case "ADD":
                return "0000";
            case "SUB":
                return "0001";
            case "MUL":
                return "0010";
            case "MOVI":
                return "0011";
            case "BEQZ":
                return "0100";
            case "ANDI":
                return "0101";
            case "EOR":
                return "0110";
            case "BR":
                return "0111";
            case "SAL":
                return "1000";
            case "SAR":
                return "1001";
            case "LDR":
                return "1010";
            case "STR":
                return "1011";
            default:
                return "WEIRD";
        }
    }

    private String getNameDependingOnOpcode(String opcode) {
        switch(opcode) {
            case "0000":
                return "ADD";
            case "0001":
                return "SUB";
            case "0010":
                return "MUL";
            case "0011":
                return "MOVI";
            case "0100":
                return "BEQZ";
            case "0101":
                return "ANDI";
            case "0110":
                return "EOR";
            case "0111":
                return "BR";
            case "1000":
                return "SAL";
            case "1001":
                return "SAR";
            case "1010":
                return "LDR";
            case "1011":
                return "STR";
            default:
                return "WEIRD";
        }
}

    public static void main(String[] args) {
        Instruction inst = new Instruction("0010000000111111", "I");
        System.out.println("Name: "+inst.name);
        System.out.println("Type: "+inst.type);
        System.out.println("Opcode: "+inst.opcode);
        System.out.println("R1: "+inst.r1);
        System.out.println("R2: "+inst.r2);
        System.out.println("Immediate: "+inst.immediate);
    }

}