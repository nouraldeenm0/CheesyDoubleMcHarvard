
public class CPU {
    Register[] general_purpose_registers = new Register[64];
    // 0 0 0 C V N S Z
    Register status = new Register("Status", "0");
    Register program_counter = new Register("PC", "0");
    int clock_cycle = 1;
    Instruction[] to_be_executed_instructions = new Instruction[3];

    CPU() {
        this.initialize_general_purpose_registers();
    }

    private void fetch(Instruction instruction) {
        // fetching instruction from main memory
        to_be_executed_instructions[0] = instruction; 

        // incrementing program counter
        program_counter.value = Integer.toString(Integer.parseInt(program_counter.value) + 1);

    }

    private void decode(Instruction instruction) {
        switch(instruction.name) {
            case "ADD":
                int first_register_operand = Integer.parseInt(instruction.r1.substring(1));
                int second_register_operand = Integer.parseInt(instruction.r2.substring(1));
        }
    }

    private void execute() {
    }


    void initialize_general_purpose_registers() {
        for (int i = 0; i < general_purpose_registers.length; ++i) {
            general_purpose_registers[i] = new Register("R"+i, "0");
            System.out.println(general_purpose_registers[i].name);
        }    
    }

    public static void main(String[] args) {
        CPU cpu = new CPU();
        cpu.fetch(Memory.instructions[Integer.parseInt(cpu.program_counter.value)]);
        cpu.decode();
        cpu.execute();
    }
}
