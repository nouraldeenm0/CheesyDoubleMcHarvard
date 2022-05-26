public class Unsigned_N_Bits {
    static int MIN_VALUE = 0;
    static int MAX_VALUE;
    int value;

    public Unsigned_N_Bits(int numberOfBits) {
        MAX_VALUE = (int) Math.pow(2, (numberOfBits + 1)) - 1;
        this.value = 0;
    }

    public Unsigned_N_Bits(int numberOfBits, int value) {
        MAX_VALUE = (int) Math.pow(2, (numberOfBits + 1)) - 1;

        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new OutOfMemoryError("Too Big or too Short number");
        }
        else {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return value + "";
    }
}
