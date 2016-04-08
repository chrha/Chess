package Pieces;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Bishop implements Piece{
    private boolean isWhite;

    public Bishop(boolean isWhite) {
        this.isWhite = isWhite;
    }
    public boolean isWhite(){
        return isWhite;
    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    @Override
    public String getDescription() {
        if (isWhite){
            return "Bishop"+"White";
        }else{
            return "Bishop"+"Black";
        }
    }
}
