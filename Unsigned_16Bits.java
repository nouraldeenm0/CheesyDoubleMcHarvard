public class Unsigned_16Bits {
    static final int MIN_VALUE = 0b0000000000000000; // 0
    static final int MAX_VALUE = 0b1111111111111111; // 131071 
    int value;

    public Unsigned_16Bits() {
        this.value = 0;
    }

    public Unsigned_16Bits(int value) {
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