package service1;

public enum Cargo {
    BULK,
    CONTAINER,
    LIQUID;

    @Override
    public String toString() {
        return switch (this) {
            case BULK -> "B";
            case CONTAINER -> "C";
            case LIQUID -> "L";
        };
    }
}
