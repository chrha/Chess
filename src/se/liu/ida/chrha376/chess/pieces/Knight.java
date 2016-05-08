package se.liu.ida.chrha376.chess.pieces;

import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as Piece on Board.
 */
public class Knight implements Piece{
    private boolean white;
    private ImageIcon icon;

    public Knight(boolean white) {
        this.white = white;
        if (white){
            icon = new ImageIcon(getClass().getResource("/chess/resources/Knight_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/Knight_Black.png"));
        }

    }

    @Override
    public boolean isWhite() {
        return white;
    }

    @Override
    public List<Coordinates> moveList(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();
        double dis = Math.sqrt(Math.pow((from.getX()-to.getX()),2) + Math.pow((from.getY()-to.getY()),2));
	/**
	 * dis is the exact distance to a possible coordinate in for Knight based on Pythagoras theorem.
         * Java is complaining about comparing floating point values for exact equality, but sense we are comparing
         * Math.sqrt(5) == Math.sqrt(5) this should not be a problem.
         */
        if (dis == Math.sqrt(5)){
            dir.add(new Coordinates(to.getX(),to.getY()));
        }
        return dir;
    }

    @Override
    public String getDescription() {
        return "Knight";
    }
    public ImageIcon getIcon() {
        return icon;
    }
    public void setMoved(boolean moved){}
    public boolean isMoved(){return false;}
}
