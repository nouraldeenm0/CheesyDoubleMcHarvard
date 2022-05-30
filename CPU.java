import java.io.IOException;

public class CPU {
    Register[] general_purpose_registers = new Register[64];
    // 0 0 0 C V N S Z
    Register status = new Register("Status", 0);
    Register program_counter = new Register("PC", 0);
    int clock_cycle = 1;

    /*
     * 3 instructions representing our instruction in each phase.
     * The first phase is when the Instruction is fetched (It is still represented as a String)
     * The second phase is when the Instruction is decoded (Now it is an Instruction Object)
     * The third phase is when it is executed (it was decoded from the second phase so it will use the Instruction object)
    */
    String fetched_instruction;
    Instruction decoded_instruction;
    Instruction executed_instruction;

    CPU() throws ValueOutOfBounds {
        this.initialize_general_purpose_registers();
    }

    void fetch(String instruction_from_memory) {
        // fetching instruction from main memory
        this.fetched_instruction = instruction_from_memory;

        // incrementing program counter
        ++program_counter.value;
    }

    void decode(String fetched) throws InvalidInstructionArguments {
        // the split(" ") will the split the string on spaces so
        // "ADD R1 R2" will become {"ADD", "R1", "R2"}
        String[] divided_instruction = fetched.split(" ");
        String inst_name = divided_instruction[0];
        String r1 = divided_instruction[1];
        String r2;
        int immediate;
        try {
        	immediate = Integer.parseInt(divided_instruction[2]);
            this.decoded_instruction = new Instruction(inst_name, r1, immediate);
        }
        catch (NumberFormatException e) {
        	r2 = divided_instruction[2];
            this.decoded_instruction = new Instruction(inst_name, r1, r2);
        }

        ++program_counter.value;
    }

    void execute(Instruction decoded) {

        int first_register_operand_address = Integer.parseInt(decoded.r1.name.substring(1));
        Register r1 = general_purpose_registers[first_register_operand_address];

        if (decoded.type == "R") {
            int second_register_operand_address = Integer.parseInt(decoded.r2.name.substring(1));
            Register r2 = general_purpose_registers[second_register_operand_address];

            switch(decoded.name) {
                    case "ADD":
                        r1.value = r1.value + r2.value;
                        break;
                    case "SUB":
                        r1.value = r1.value - r2.value;
                        break;
                    case "MUL":
                        r1.value = r1.value * r2.value;
                        break;
                    case "BR":
                        program_counter.value = r1.value | r2.value;
                        break;
                    case "EOR":
                        r1.value = r1.value ^ r2.value;
                        break;
            }
        }
        else if (decoded.type == "I") {
            /*
                CHECK immediate value validiity here
            */
                switch(decoded.name) {
                    case "MOVI":
                        r1.value = decoded.immediate;
                        break;
                    case "BEQZ":
                        if (r1.value == 0) {
                            program_counter.value += 1 + decoded.immediate;
                        }
                        break;
                    case "ANDI":
                        r1.value = r1.value & decoded.immediate;
                        break;
                    case "SAL":
                        r1.value = r1.value << decoded.immediate;
                        break;
                    case "SAR":
                        r1.value = r1.value >> decoded.immediate;
                        break;
                    case "LDR":
                        r1.value = Memory.data[decoded.immediate];
                        break;
                    case "STR":
                        Memory.data[decoded.immediate] = r1.value;
                        break;

                }
        }
        ++program_counter.value;
    }


    void initialize_general_purpose_registers() throws ValueOutOfBounds {
        for (int i = 0; i < general_purpose_registers.length; ++i) {
            general_purpose_registers[i] = new Register("R"+i, 0);
        }   
    }

    public static void main(String[] args) throws ValueOutOfBounds, IOException, InvalidInstructionArguments {
        CPU cpu = new CPU();

        /* ONE INSTRUCTION's full story, NO PIPELINE IMPLEMENTED YET */
        // reading first instruction in our program into main memory
        String programFileContent = ProgramFileParser.readFile("Program.txt");
        Memory.instructions[0] = ProgramFileParser.getNthLineFromString(1 ,programFileContent);

        // Before fetching we would read the user Program File and store the Instructions in memory
        cpu.fetch(Memory.instructions[cpu.program_counter.value]);

        cpu.decode(cpu.fetched_instruction);

        // cpu.execute(cpu.decoded_instruction);

        cpu.clock_cycle += 1;
    }
}
