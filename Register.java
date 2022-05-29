public class Register {
    String name;
    String value;
    // A register can maximum hold a value represented by 16 bits, which is 131071

    public Register(String name, String value) throws RegisterValueIsOutOfBounds {
        this.name = name;
        if (Integer.parseInt(value) > 131071 || Integer.parseInt(value) < 0) {
            throw new RegisterValueIsOutOfBounds("Register value is out of bounds");
        }
        else {
            this.value = value;
        }

    }

}
