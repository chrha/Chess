package Pieces;

import javax.swing.*;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Pawn implements Piece {
    private boolean isWhite;
    private ImageIcon icon;

    public Pawn(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/resources/Pawn_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/resources/Pawn_Black.png"));
        }
    }

    @Override
    public boolean isWhite() {
        return isWhite;

    }

    @Override
    public boolean canMove(int x0,int y0,int x, int y) {
        return true;
    }

    @Override
    public String getDescription() {
        if (isWhite){
            return "Pawn"+"White";
        }else{

            return "Pawn"+"Black";
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
