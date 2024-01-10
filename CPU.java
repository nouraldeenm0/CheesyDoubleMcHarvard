import java.io.IOException;

public class CPU {
    Register[] general_purpose_registers = new Register[64];
    // 0 0 0 C V N S Z
    // C: Carry flag
    // V: Overflow flag
    // N: Negative flag
    // S: Sign flag
    // Z: Zero flag
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

    CPU() throws ValueOutOfBounds {
        this.initialize_general_purpose_registers();
    }

    public static void main(String[] args) throws ValueOutOfBounds, IOException, InvalidInstructionArguments {
        CPU cpu = new CPU();
        putInstructionsIntoMemory();

        for (String instruction : Memory.instructions) {
            if (instruction == null) break;
            cpu.fetch(instruction);
            cpu.decode(cpu.fetched_instruction);
            cpu.execute(cpu.decoded_instruction);
            printRegisterValues(cpu);
            System.out.println("---------------------------------");
        }
    }

    private static void printRegisterValues(CPU cpu) {
        for (int i = 0; i < cpu.general_purpose_registers.length; ++i) {
            System.out.println(cpu.general_purpose_registers[i].name + " " + cpu.general_purpose_registers[i].value);
        }
    }

    void fetch(String instruction_from_memory) {
        // fetching instruction from main memory
        this.fetched_instruction = instruction_from_memory;

        // incrementing program counter
    }

    void decode(String fetched) throws InvalidInstructionArguments, ValueOutOfBounds {
        // the split(" ") will the split the string on spaces so
        // "ADD R1 R2" will become {"ADD", "R1", "R2"}
        String[] divided_instruction = fetched.split("\\s+");
        String inst_name = divided_instruction[0];
        String r1 = divided_instruction[1];
        String r2;
        int immediate;
        /*
        In the try catch statement divided_instruction[2] I am seeing if the value of the
        third index (decoded_instruction[2]) is a string representing a number if it is a number
        then we know the user intent to input an immediate value and
        the try statement would work succesfully without reaching the catch..
        If we try to convert a string not representing a number to an interger,
        for example: "Hello", then a NumberFormatException would be thrown,
        and in that case I know that the decoded_instruction[2] is representing a register
        */
        try {
        	immediate = Integer.parseInt(divided_instruction[2]);
            this.decoded_instruction = new Instruction(inst_name, r1, immediate);
        }
        catch (NumberFormatException e) {
        	r2 = divided_instruction[2];
            this.decoded_instruction = new Instruction(inst_name, r1, r2);
        }

    }

    void execute(Instruction decoded) throws ValueOutOfBounds {
        int first_register_operand_address = Integer.parseInt(decoded.r1.name.substring(1));
        if (first_register_operand_address > 63 || first_register_operand_address < 0) {
            throw new ValueOutOfBounds("Invalid Register");   
        }
        Register r1 = general_purpose_registers[first_register_operand_address];

        if (decoded.type == "R") {
            int second_register_operand_address = Integer.parseInt(decoded.r2.name.substring(1));
            Register r2 = general_purpose_registers[second_register_operand_address];
            if (second_register_operand_address > 63 || second_register_operand_address < 0) {
                throw new ValueOutOfBounds("Invalid Register");   
            }
            switch(decoded.name) {
                case "ADD":
                    r1.value = r1.value + r2.value;
                    if (r1.value == 0) status.value |= 1; else status.value &= ~1;
                    break;
                    case "SUB":
                        r1.value = r1.value - r2.value;
                        if (r1.value == 0) status.value |= 1; else status.value &= ~1;
                        break;
                    case "MUL":
                        r1.value = r1.value * r2.value;
                        if (r1.value == 0) status.value |= 1; else status.value &= ~1;
                        break;
                    case "BR":
                        program_counter.value = r1.value | r2.value;
                        if (program_counter.value == 0) status.value |= 1; else status.value &= ~1;
                        break;
                    case "EOR":
                        r1.value = r1.value ^ r2.value;
                        if (r1.value == 0) status.value |= 1; else status.value &= ~1;
                        break;
            }
        }
        else if (decoded.type == "I") {
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
    }

    void initialize_general_purpose_registers() throws ValueOutOfBounds {
        for (int i = 0; i < general_purpose_registers.length; ++i) {
            general_purpose_registers[i] = new Register("R"+i, 0);
        }   
    }

    private static void putInstructionsIntoMemory() throws IOException {
        String programFileContent = ProgramFileParser.readFile("Program.txt");
        System.out.println(programFileContent);
        int number_of_instructions = ProgramFileParser.getNumberOfLinesWithContent(programFileContent);

        for (int i = 0; i < number_of_instructions; ++i) {
            Memory.instructions[i] = ProgramFileParser.getNthLineWithContent(i + 1 ,programFileContent);
        }
    }
}
