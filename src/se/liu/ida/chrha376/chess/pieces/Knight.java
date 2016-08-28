package se.liu.ida.chrha376.chess.pieces;

import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as Piece on Board.
 */
public class Knight implements Piece{
    private PieceColor color;
    private ImageIcon icon;

    public Knight(PieceColor color) {
        this.color = color;
        if (this.color == PieceColor.WHITE){
            icon = new ImageIcon(getClass().getResource("/chess/resources/Knight_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/Knight_Black.png"));
        }

    }

    @Override
    public List<Coordinates> getPossibleMoves(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();
        if ((Math.abs(from.getX()-to.getX()) == 2 && Math.abs(from.getY()-to.getY()) == 1) ||
            (Math.abs(from.getX()-to.getX()) == 1 && Math.abs(from.getY()-to.getY()) == 2)){
            dir.add(new Coordinates(to.getX(),to.getY()));
        }
        return dir;
    }

    @Override
    public String getDescription() {
        return "Knight";
    }
    public PieceColor getColor(){
                return color;
            }
    public boolean hasColor(PieceColor color){ return this.color == color;}
    public boolean hasType(PieceType type){ return type == PieceType.KNIGHT;}
    public ImageIcon getIcon() {
        return icon;
    }
    public void setMoved(boolean moved){}
    public boolean isMoved(){return false;}
}
