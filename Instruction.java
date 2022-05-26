public class Instruction {
    Instruction_name name;
    Instruction_type type;    
    short size = 16;
    Unsigned_N_Bits opcode = new Unsigned_N_Bits(4);
    Register r1;
    Register r2;
    Unsigned_N_Bits immediate;


    Instruction(Instruction_name name, Register r1, Register r2) {
        this.name = name;
        this.type = getTypeDependingOnName(name);
        this.opcode = getOpcodeDependingOnName(name); 
        this.r1 = r1;
        this.r2 = r2;
    }

    Instruction(Instruction_name name, Register r1, Unsigned_N_Bits immediate) {
        this.name = name;
        this.type = getTypeDependingOnName(name);
        this.opcode = getOpcodeDependingOnName(name); 
        this.r1 = r1;
        this.immediate = immediate;
    }
    
    private Instruction_type getTypeDependingOnName(Instruction_name name) {
        switch(name) {
            case ADD, SUB, MUL, EOR, BR:
                return Instruction_type.R_FORMAT;
            case MOVI, BEQZ, ANDI, SAL, SAR, LDR, STR:
                return Instruction_type.I_FORMAT;
            default:
                return null;
        }
    }

    private Unsigned_N_Bits getOpcodeDependingOnName(Instruction_name name) {
        short value = 0b0000;

        switch(name) {
            case ADD:
                value = 0b0000;
                break;
            case SUB:
                value = 0b0001;
                break;
            case MUL:
                value = 0b0010;
                break;
            case MOVI:
                value = 0b0011;
                break;
            case BEQZ:
                value = 0b0100;
                break;
            case ANDI:
                value = 0b0101;
                break;
            case EOR:
                value = 0b0110;
                break;
            case BR:
                value = 0b0111;
                break;
            case SAL:
                value = 0b1000;
                break;
            case SAR:
                value = 0b1001;
                break;
            case LDR:
                value = 0b1010;
                break;
            case STR:
                value = 0b1011;
                break;
        }

        Unsigned_N_Bits opcode = new Unsigned_N_Bits(4, value);
        return opcode;
    }
}
