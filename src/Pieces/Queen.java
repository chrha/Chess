package Pieces;

import javax.swing.*;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Queen implements Piece{
    private boolean isWhite;
    private ImageIcon icon;

    public Queen(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/resources/Queen_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/resources/Queen_Black.png"));
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
            return "Queen"+"White";
        }else{
            return "Queen"+"Black";
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
