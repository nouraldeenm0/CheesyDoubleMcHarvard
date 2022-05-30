public class Register {
    String name;
    int value;
    // A register can maximum hold a value represented by 16 bits, which is 131071

    public Register(String name, String value) throws RegisterValueIsOutOfBounds {
        this.name = name;
        if (this.value > 131071 || this.value < 0) {
            throw new RegisterValueIsOutOfBounds("Register value is out of bounds");
        }
        else {
            this.value = value;
        }

    }

}
