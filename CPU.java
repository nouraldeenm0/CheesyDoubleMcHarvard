
public class CPU {
    Register[] general_purpose_registers = new Register[64];
    // 0 0 0 C V N S Z
    Register status = new Register("Status", "0");
    Register program_counter = new Register("PC", "0");
    int clock_cycle = 1;

    String fetched_instruction;
    Instruction decoded_instruction;
    Instruction executed_instruction;

    CPU() {
        this.initialize_general_purpose_registers();
    }

    void fetch(String instruction_from_memory) {
        // fetching instruction from main memory
        this.fetched_instruction = instruction_from_memory; 

        // incrementing program counter
        program_counter.value = Integer.toString(Integer.parseInt(program_counter.value) + 1);

    }

    void decode(String fetched_instruction) {
        String[] divided_instruction = fetched_instruction.split(" ");
        String inst_name = divided_instruction[0];
        String r1 = divided_instruction[1];
        String r2_or_immediate = divided_instruction[2];
    
        // instruction type is infered inside the instruction constructor
        // now we have a decoded instruction :)
        this.decoded_instruction = new Instruction(inst_name, r1, r2_or_immediate);
    }

    void execute(Instruction decoded_instruction) {
        switch(decoded_instruction.name) {
                case "ADD":
                    // for example if the user wrote ADD R12 R93, this will return the '12' in R1 as an int
                    // we will then use these values to address them in the array storing general_purpose_registers
                    int first_register_operand_address = Integer.parseInt(decoded_instruction.r1.name.substring(1));
                    // this will store 93
                    int second_register_operand_address = Integer.parseInt(decoded_instruction.r2.name.substring(1));
                    int first_register_value = Integer.parseInt(decoded_instruction.r1.value);
                    int second_register_value = Integer.parseInt(decoded_instruction.r2.value);

                    Register r1 = general_purpose_registers[first_register_operand_address];
                    Register r2 = general_purpose_registers[second_register_operand_address];
        }
    }


    void initialize_general_purpose_registers() {
        for (int i = 0; i < general_purpose_registers.length; ++i) {
            general_purpose_registers[i] = new Register("R"+i, "0");
            System.out.println(general_purpose_registers[i].name);
        }    
    }

    public static void main(String[] args) {
        CPU cpu = new CPU();
        // Before fetching we would read the user Program File and store the Instructions in memory
        cpu.fetch(Memory.instructions[Integer.parseInt(cpu.program_counter.value)]);
        cpu.decode(to_be_decoded_instruction);
        cpu.clock_cycle += 1;
    }
}
