package se.liu.ida.chrha376.chess.pieces;

import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as Piece on Board.
 */
public class King implements Piece {
    private PieceColor color;
    private ImageIcon icon;
    private boolean moved;

    public King(PieceColor color,boolean moved) {
        this.moved = moved;
        this.color = color;
        if (this.color == PieceColor.WHITE){
            icon = new ImageIcon(getClass().getResource("/chess/resources/King_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/King_Black.png"));
        }
    }


    @Override
    public List<Coordinates> getPossibleMoves(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();
        if ((int)(Math.sqrt(Math.pow(from.getX()-to.getX(),2)+Math.pow(from.getY()-to.getY(),2)))==1 || (int)(Math.sqrt(Math.pow(from.getX()-to.getX(),2)+Math.pow(from.getY()-to.getY(),2)))==(int)Math.sqrt(2)){
            dir.add(new Coordinates(to.getX(),to.getY()));
        }
        return dir;
    }

    @Override
    public PieceColor getColor(){
                return color;
            }
    public boolean hasColor(PieceColor color){ return this.color == color;}
    public String getDescription() {
        return "King";
    }
    public boolean hasType(PieceType type){ return type == PieceType.KING;}
    public ImageIcon getIcon() {
        return icon;
    }
    public boolean isMoved(){return moved;}
    public void setMoved(boolean moved){this.moved = moved;}
}
