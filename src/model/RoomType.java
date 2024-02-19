package model;

public enum RoomType {
    SINGLE("S"),
    DOUBLE("D");

    final String initial;

    RoomType(String stringInitial) {
        this.initial = stringInitial;
    }

    public static RoomType valueFromNumberOption(String initial) {
        for (RoomType r : RoomType.values()) {
            if (r.initial.equals(initial)) {
                return  r;
            }
        }
        throw new IllegalArgumentException();
    }
}
