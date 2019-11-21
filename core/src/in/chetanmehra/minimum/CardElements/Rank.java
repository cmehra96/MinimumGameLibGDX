package in.chetanmehra.minimum.CardElements;

public enum Rank {
    Ace(1, "ace", "ace", 0),
    Two(2, "two", "2", 1),
    Three(3, "three", "3", 2),
    Four(4, "four", "4", 3),
    Five(5, "five", "5", 4),
    Six(6, "six", "6", 5),
    Seven(7, "seven", "7", 6),
    Eight(8, "eight", "8", 7),
    Nine(9, "nine", "9", 8),
    Ten(10, "ten", "10", 9),
    Jack(11, "jack", "jack", 10),
    Queen(12, "queen", "queen", 11),
    King(13, "king", "king", 12);


    private final int rank;
    private final String name;
    private final String imageName;
    private final int index;

    Rank(int rank, String name, String imageName, int index) {
        this.rank = rank;
        this.name = name;
        this.imageName = imageName;
        this.index = index;
    }

    public String getImageName() {
        return imageName;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
