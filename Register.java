public class Register {
    String name;
    int value;
    // A register can maximum hold a value represented by 16 bits, which is 131071

    public Register(String name, int value) throws ValueOutOfBounds {
        this.name = name;
        if (this.value > Math.pow(2, 16) - 1 || this.value < 0) {
            throw new ValueOutOfBounds("Register value is out of bounds");
        }
        else {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return "Register [name=" + name + ", value=" + value + "]";
    }
    
}
