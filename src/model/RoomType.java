package model;

public enum RoomType {
    SINGLE("1"),
    DOUBLE("2");

    final String numberOption;

    RoomType(String numberOption) {
        this.numberOption = numberOption;
    }

    public static RoomType valueFromNumberOption(String numberOption) {
        for (RoomType r : RoomType.values()) {
            if (numberOption.equals(r.numberOption)) {
                return  r;
            }
        }
        throw new NumberFormatException();
    }
}
