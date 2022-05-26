
public class CPU {
    Register[] general_purpose_registers = new Register[64];
    // 0 0 0 C V N S Z
    Register status = new Register("Status");
    Register program_counter = new Register(0, "PC");

    CPU() {
        this.initialize_general_purpose_registers();
    }

    void initialize_general_purpose_registers() {
        for (int i = 0; i < general_purpose_registers.length; ++i) {
            general_purpose_registers[i] = new Register(0, "R"+i);
            System.out.println(general_purpose_registers[i].name);
        }    
    }

    public static void main(String[] args) {
        CPU cpu = new CPU();
    }
}
