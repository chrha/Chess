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

    public PieceColor getColor() {
        return color;

    }

    @Override
    public List<Coordinates> moveList(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();

        if(color == PieceColor.WHITE){
            if(!moved){
                if (((from.getX()+1)<=7)){
                    dir.add(new Coordinates(from.getX()+1,from.getY()+1));
                }if (((from.getX()-1)>=0)){
                    dir.add(new Coordinates(from.getX()-1,from.getY()+1));
                }
                dir.add(new Coordinates(from.getX(),from.getY()+1));
                dir.add(new Coordinates(from.getX(),from.getY()+2));




            }else{
                if (((from.getX()+1)<=7)){
                    dir.add(new Coordinates(from.getX()+1,from.getY()+1));
                }if (((from.getX()-1)>=0)){
                    dir.add(new Coordinates(from.getX()-1,from.getY()+1));
                }
                dir.add(new Coordinates(from.getX(),from.getY()+1));
            }
        }else {
            if (!moved) {
                if (((from.getX() + 1) <= 7)) {
                    dir.add(new Coordinates(from.getX() + 1, from.getY() - 1));
                }
                if (((from.getX() - 1) >= 0)) {
                    dir.add(new Coordinates(from.getX() - 1, from.getY() - 1));
                }
                dir.add(new Coordinates(from.getX(), from.getY() - 1));
                dir.add(new Coordinates(from.getX(), from.getY() - 2));


            } else {
                if (((from.getX() + 1) <= 7)) {
                    dir.add(new Coordinates(from.getX() + 1, from.getY() - 1));
                }
                if (((from.getX() - 1) >= 0)) {
                    dir.add(new Coordinates(from.getX() - 1, from.getY() - 1));
                }
                dir.add(new Coordinates(from.getX(), from.getY() - 1));
            }
        }
        return dir;
    }


    @Override
    public String getDescription() {return "Pawn";}
    public  ImageIcon getIcon() {
        return icon;
    }
    public boolean isMoved(){return moved;}
    public void setMoved(boolean moved){this.moved = moved;}
}
