public class Register {
    Unsigned_N_Bits reg_stored_Bits = new Unsigned_N_Bits(8);
    String name;

    public Register() {
        this.reg_stored_Bits.value = 0;
        this.name = "Unnamed";
    }

    public Register(String name) {
        this.reg_stored_Bits.value = 0;
        this.name = name;
    }

    public Register(int value, String name) {
        this.reg_stored_Bits.value = value;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Register [name=" + name + ", reg_stored_8Bits.value=" + reg_stored_Bits.value + "]";
    }

    public static void main(String[] args) {
        Register r = new Register("Some name");
        r.reg_stored_Bits.value = 5;
        System.out.println(r);
    }
}
