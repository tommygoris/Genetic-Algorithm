package geneticalgorithm.examples.evolveann.playchess.pieces;

public enum NumberOfPiecesEnum {
    PAWN(8), QUEEN(1), KING(1), ROOK(2), KNIGHT(2), BISHOP(2);

    private final int value;

    NumberOfPiecesEnum(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
