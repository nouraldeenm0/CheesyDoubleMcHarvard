public class Instruction {
    // String instruction_as_binary = "0000000000000000";
    // String opcode;
    String name;
    String type;
    Register r1;
    Register r2;
    String immediate;

    /*
    public Instruction(String instruction_as_binary, String type) {
        this.instruction_as_binary = instruction_as_binary;
        this.opcode = this.instruction_as_binary.substring(0, 4);
        this.name = getNameDependingOnOpcode(opcode);
        this.type = type;
        this.r1.name = "R"+binToDec(Integer.parseInt(instruction_as_binary.substring(4, 10));

        if (type == "R") {
            this.r2.name = "R"+binToDec(Integer.parseInt(instruction_as_binary.substring(10, 16)));
            this.immediate = "NOT SET, I am an instruction in the I FORMAT";
        }
        else if (type == "I") {
            this.immediate = "R"+binToDec(Integer.parseInt(instruction_as_binary.substring(10, 16)));
            this.r2.name = "NOT SET, I am an instruction in the R FORMAT";
        }
    }
    */

    public Instruction(String name, String r1, String r2_or_immediate) {
        //this.instruction_as_binary = opcode + r1 + r2_or_immediate;
        // this.opcode = getOpcodeDependingOnName(name);
        this.name = name;
        this.type = this.getTypeDependingOnName(name);
        this.r1.name = r1;

        if (type == "R") {
            this.r2.name = r2_or_immediate;
        }
        if (type == "I") {
            this.immediate = r2_or_immediate;
        }
    }

    String getOpcodeDependingOnName(String name) {
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

    String getNameDependingOnOpcode(String opcode) {
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

    private String getTypeDependingOnName(String name) {
            switch(name) {
                case "ADD": case "SUB": case "MUL": case "EOR": case "BR":
                    return "R";
                case "MOVI": case "BEQZ": case "ANDI": case "SAL": case "SAR":
                case "LDR": case "STR":
                    return "I";
                default:
                    return null;
            }
        }
}