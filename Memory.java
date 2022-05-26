public class Memory {

    // it is where program instructions are stored (word addressable)
    // 16 bits / word
    static Unsigned_N_Bits[] instruction_Memory = new Unsigned_N_Bits[1024];

    // it is where data is stored (word/byte addressable 1 word = 1 byte)
    // 8 bits / word
    static Unsigned_N_Bits[] data_Memory = new Unsigned_N_Bits[2048];

}