public class Instruction {
    String name;
    String type;
    Register r1;
    Register r2;
    int immediate;

    public Instruction(String name, String r1_name, String r2_name) throws InvalidInstructionArguments {
        this.name = name;
        this.type = this.getTypeDependingOnName(name);
        this.r1.name = r1_name;

        if (this.type != "R") {
            throw new InvalidInstructionArguments("Invalid Instruction Arguments");
        }
        this.r2.name = r2_name;
    }
    
    public Instruction(String name, String r1_name, int immediate_value) throws InvalidInstructionArguments, ValueOutOfBounds {
        this.name = name;
        this.type = this.getTypeDependingOnName(name);
        this.r1.name = r1_name;
 
        if (type != "I") {
            throw new InvalidInstructionArguments("Invalid Instruction Arguments");
        }
        if (this.immediate > Math.pow(2, 8) - 1 || this.immediate < Math.pow(2, 8) - 1) {
                throw new ValueOutOfBounds("Immediate value is out of bounds");
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
                    return "WEIRD";
            }
        }


}
