public class Main {
    
    public static void main(String[] args) {
        Instruction inst = new Instruction("0010000000111111", "I");
        System.out.println("Name: "+inst.name);
        System.out.println("Type: "+inst.type);
        System.out.println("Opcode: "+inst.opcode);
        System.out.println("R1: "+inst.r1);
        System.out.println("R2: "+inst.r2);
        System.out.println("Immediate: "+inst.immediate);
        /*

        • putInstructionsIntoMainMemoryFromProgramFile()

        • fetch(): Fetches the next instruction from the main memory using the
        address in the PC (Program Counter), and increments the PC.

        • decode(): Decodes the instruction and reads any operands required from
        the register file.

        • execute(): Executes the instruction. In fact, all ALU operations are done in this stage.
        Moreover, it performs any memory access required by the current instruction. For loads, it
        would load an operand from the main memory, while for stores, it would store an operand into
        the main memory. Finally, for instructions that have a result (a destination register), it writes
        this result back to the register file. 

        */
    }
    
    static int getOpcodeFromInstruction(int instruction) {
        return instruction >>> 26;
    }

    static int getRegValueFromInstruction(int instruction, int fromBit, int bitsSize) {
        int num = instruction;
        num = num << (32 - 1 - fromBit);
        num = num >>> (32 - bitsSize);
        // num = num << 9;
        // num = num >>> (32 - 6);
        return num;
    }


}