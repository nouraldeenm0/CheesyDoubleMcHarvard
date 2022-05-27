public class Instruction {
    String instruction = "0000000000000000";
    Name name;
    Type type = Type.I_FORMAT;
    String opcode;
    String r1;
    String r2;
    String immediate;

    public Instruction(String instruction, Type type) {
        this.instruction = instruction;
        // this.opcode = (name == null) ? instruction.substring(0, 4) : getOpcodeFromInstruction();
        this.r1 = instruction.substring(4, 10);

        // if (type == Type.R_FORMAT) {
        //     this.r2 = instruction.substring(10, 16);
        // }
        // else if (type == Type.I_FORMAT) {
        //     this.immediate = instruction.substring(10, 16);
        // }
    }

    public static void main(String[] args) {
        Instruction inst = new Instruction("11110000001111", Type.I_FORMAT);
        System.out.println(inst.name);
        System.out.println(inst.type);
        System.out.println(inst.opcode);
        System.out.println(inst.r1);
        System.out.println(inst.r2);
        System.out.println(inst.immediate);
    }

}