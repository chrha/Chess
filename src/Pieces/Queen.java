package Pieces;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Queen implements Piece{
    private boolean isWhite;

    public Queen(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    @Override
    public String getDescription() {
        if (isWhite){
            return "Queen"+"White";
        }else{
            return "Queen"+"Black";
        }
    }
}
