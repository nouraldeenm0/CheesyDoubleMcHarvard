public class Memory {

    // it is where program instructions are stored (word addressable)
    // 16 bits / word
    // array of instructions
    static Instruction[] instruction = new Instruction[1024];

    // it is where data is stored (word/byte addressable 1 word = 1 byte)
    // 8 bits / word
    // example: data[0] could be: "00001111" representing the value 15
    static String[] data = new String[2048];

}