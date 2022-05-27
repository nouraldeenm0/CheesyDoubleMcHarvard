public class Memory {

    // it is where program instructions are stored (word addressable)
    // 16 bits / word
    static Instruction[] instruction_Memory = new Instruction[1024];

    // it is where data is stored (word/byte addressable 1 word = 1 byte)
    // 8 bits / word
    static String[] data_Memory = new String[2048];

}