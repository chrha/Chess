package se.liu.ida.chrha376.chess;

/**
 * Created by Ilian on 2016-04-05.
 */
public final class MainBoard
{
    private MainBoard() {}

    public static void main(String[] args) {
        final Board b = new Board();
        /**
         * We are not intrested in the result of ChessFrame, Only that it shows our frame.
         */
        new ChessFrame(b);

    }

}