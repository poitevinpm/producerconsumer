import javax.management.InvalidAttributeValueException;

public final class Word {

    private final String value;

    public Word(String value) throws InvalidAttributeValueException {
        if (value.length() > 50) {
            throw new InvalidAttributeValueException("value has too many characters: " + value.length());
        }
        this.value = value;
    }

    public final String get() {
        return value;
    }
}