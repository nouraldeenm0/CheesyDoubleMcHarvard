
public class CPU {
    Register[] general_purpose_registers = new Register[64];
    // 0 0 0 C V N S Z
    Register status = new Register("Status", "0");
    Register program_counter = new Register("PC", "0");

    CPU() {
        this.initialize_general_purpose_registers();
    }



    void initialize_general_purpose_registers() {
        for (int i = 0; i < general_purpose_registers.length; ++i) {
            general_purpose_registers[i] = new Register("R"+i, "0");
            System.out.println(general_purpose_registers[i].name);
        }    
    }

    public static void main(String[] args) {
        CPU cpu = new CPU();
        String x = cpu.general_purpose_registers[0].value;
        System.out.println(x);
    }
}
