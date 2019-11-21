package in.chetanmehra.minimum.CardElements;

public enum Suit {
    Clubs(1, "clubs", 0, "♣"),
    Spades(2, "spades", 1, "♠"),
    Hearts(3, "hearts", 2, "♥"),
    Diamond(4, "diamonds", 3, "♦");
    private final int value;
    private final String name;
    private final String symbol;
    private final int index;

    Suit(int value, String name, int index, String symbol) {
        this.value = value;
        this.name = name;
        this.index = index;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
