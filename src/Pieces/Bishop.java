package Pieces;

import javax.swing.*;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Bishop implements Piece{
    private boolean isWhite;
    private ImageIcon icon;

    public Bishop(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/resources/Bishop_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/resources/Bishop_Black.png"));
        }
    }
    public boolean isWhite(){
        return isWhite;
    }

    @Override
    public boolean canMove(int x0,int y0,int x, int y) {
        return true;
    }

    @Override
    public String getDescription() {
        if (isWhite){
            return "Bishop"+"White";
        }else{
            return "Bishop"+"Black";
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
