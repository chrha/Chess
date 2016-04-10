package Pieces;

import javax.swing.*;

/**
 * Created by Ilian on 2016-04-06.
 */
public class King implements Piece {
    private boolean isWhite;
    private ImageIcon icon;

    public King(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/resources/King_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/resources/King_Black.png"));
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
            return "King"+"White";
        }else{
            return "King"+"Black";
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
