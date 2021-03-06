package se.liu.ida.chrha376.chess.pieces;


import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as Piece on Board.
 */
public class Rook implements Piece {
    private PieceColor color;
    private ImageIcon icon;
    private boolean moved;

    public Rook(PieceColor color, boolean moved) {
        this.moved = moved;
        this.color = color;
        if (this.color == PieceColor.WHITE){
            icon = new ImageIcon(getClass().getResource("/chess/resources/Rook_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/Rook_Black.png"));
        }
    }

    @Override
    public List<Coordinates> getPossibleMoves(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();
        if (from.getY() == to.getY()) {
            for (int i = from.getX() + 1; i < 8; i++) {
                dir.add(new Coordinates(i, to.getY()));
                if (i == to.getX()) {
                    return dir;
                }
            } dir.clear();
            for (int i = from.getX() - 1; i >= 0; i--) {
                dir.add(new Coordinates(i, to.getY()));
                if (i == to.getX()) {
                    return dir;
                }
            }
        }
        if(from.getX() == to.getX()){
            dir.clear();
            for (int i = from.getY() + 1; i < 8; i++) {
                dir.add(new Coordinates(to.getX(), i));
                if (i == to.getY()) {
                    return dir;
                }
            }
            dir.clear();
            for (int i = from.getY() - 1; i >= 0; i--) {
                dir.add(new Coordinates(to.getX(), i));
                if (i == to.getY()) {
                    return dir;
                }
            }
        }
        dir.clear();
        return dir;
    }

    @Override
    public String getDescription() {
        return "Rook";
    }
    public PieceColor getColor(){
                return color;
            }
    public boolean hasColor(PieceColor color){ return this.color == color;}
    public boolean hasType(PieceType type){ return type == PieceType.ROOK;}
    public ImageIcon getIcon() {
        return icon;
    }
    public boolean isMoved(){return moved;}
    public void setMoved(boolean moved){this.moved = moved;}
}
