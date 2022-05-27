public class Main
{
        public static void main(String[] args) {
            // int b = 0b10111100000000000000000000000000;
            // b = b >>> 26;
            int k = 0b01110010010111100000011110000000;
            // k = k << 9;
            // k = k >>> (32 - 6);
            int z = getRegValueFromInstruction(k, 24, 8);
            System.out.println(z); // should be equal 0b101111 = 47
        }
       
       
        static int getOpcodeFromInstruction(int instruction) {
           return instruction >>> 26;
        }
        static int getRegValueFromInstruction(int instruction, int fromBit, int bitsSize) {
            int num = instruction;
            num = num << (32 - 1 - fromBit);
            num = num >>> (32 - bitsSize);
            // num = num << 9;
            // num = num >>> (32 - 6);
            return num;
        }

}