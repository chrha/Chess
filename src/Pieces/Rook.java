package Pieces;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Rook implements Piece {
    private boolean isWhite;

    public Rook(boolean isWhite) {
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
            return "Pawn"+"White";
        }else{
            return "Pawn"+"Black";
        }
    }
}
