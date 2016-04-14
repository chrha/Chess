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
    public boolean canMove(int x0,int y0,int x, int y) {
        if(Math.sqrt(Math.pow(Math.abs((x - x0)),2)) + Math.pow(Math.abs((y - y0)), 2) != Math.sqrt(2)){
            return false;
        }
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
