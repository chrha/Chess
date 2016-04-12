package Pieces;

import javax.swing.*;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Knight implements Piece{
    private boolean isWhite;
    private ImageIcon icon;

    public Knight(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/resources/Knight_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/resources/Knight_Black.png"));
        }

    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public boolean canMove(int x, int y) {

        return true;
    }

    @Override
    public String getDescription() {
        if (isWhite){
            return "Knight"+"White";
        }else{
            return "Knight"+"Black";
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
