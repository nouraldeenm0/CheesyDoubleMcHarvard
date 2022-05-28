public class Instruction {
    String name;
    String type;
    Register r1;
    Register r2;
    String immediate;

    public Instruction(String name, String r1, String r2_or_immediate) {
        this.name = name;
        this.type = this.getTypeDependingOnName(name);
        this.r1.name = r1;

        if (type == "R") {
            this.r2.name = r2_or_immediate;
        }
        if (type == "I") {
            this.immediate = r2_or_immediate;
        }
        /*
            Need to check instruction validity
        */
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