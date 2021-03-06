package se.liu.ida.chrha376.chess.pieces;

import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as Piece on Board.
 */
public class Pawn implements Piece {
    private PieceColor color;
    private ImageIcon icon;
    private boolean moved;
    public Pawn(PieceColor color, boolean moved) {
        this.moved = moved;
        this.color = color;
        if (this.color == PieceColor.WHITE){
            icon = new ImageIcon(getClass().getResource("/chess/resources/Pawn_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/Pawn_Black.png"));
        }
    }


    @Override
    public List<Coordinates> getPossibleMoves(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();

        if(color == PieceColor.WHITE){
            if (((from.getX()+1)<=7)){
                dir.add(new Coordinates(from.getX()+1,from.getY()+1));
            }if (((from.getX()-1)>=0)) {
                dir.add(new Coordinates(from.getX() - 1, from.getY() + 1));
            }
            dir.add(new Coordinates(from.getX(),from.getY()+1));
            if(!moved){
                dir.add(new Coordinates(from.getX(),from.getY()+2));
            }

        }else {
            if (((from.getX() + 1) <= 7)) {
                dir.add(new Coordinates(from.getX() + 1, from.getY() - 1));
            }
            if (((from.getX() - 1) >= 0)) {
                dir.add(new Coordinates(from.getX() - 1, from.getY() - 1));
            }
            dir.add(new Coordinates(from.getX(), from.getY() - 1));
            if (!moved) {
                dir.add(new Coordinates(from.getX(), from.getY() - 2));
            }
        }
        return dir;
    }


    @Override
    public String getDescription() {return "Pawn";}
    public PieceColor getColor(){
                return color;
            }
    public boolean hasColor(PieceColor color){ return this.color == color;}
    public boolean hasType(PieceType type){ return type == PieceType.PAWN;}
    public  ImageIcon getIcon() {
        return icon;
    }
    public boolean isMoved(){return moved;}
    public void setMoved(boolean moved){this.moved = moved;}
}
