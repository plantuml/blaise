package blaise;

public class BToken {

    private final BTokenType type;

    private final String value;

    @Override
    public String toString() {
        return type.toString() + " " + value;
    }

    public BToken(BTokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public BTokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }


}
