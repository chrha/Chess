package se.liu.ida.chrha376.chess.pieces;

import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as Piece on Board.
 */
public class Queen implements Piece{
    private PieceColor color;
    private ImageIcon icon;

    public Queen(PieceColor color) {
        this.color = color;
        if (this.color == PieceColor.WHITE){
            icon = new ImageIcon(getClass().getResource("/chess/resources/Queen_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/Queen_Black.png"));
        }
    }


    /**
     * Method dictates where a queen can move not considering the pieces on the board and sense the queen can
     * move in all direction the method gets complex.
     * @param from Queens Coordinates
     * @param to Coordinate you want to move to.
     * @return List of Coordinates that will be the path it will take to get do its destination. If the queen cant
     * get to that destination the method will return an empty List.
     */
    @Override
    public List<Coordinates> getPossibleMoves(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();
        for (int i = from.getX()+1,z = from.getY()+1; i < 8 && z<8; i++,z++) {
            dir.add(new Coordinates(i, z));
            if (i == to.getX() && z == to.getY()) {
                return dir;
            }
        }
        dir.clear();

        for (int i = from.getX()+1,z = from.getY()-1; i < 8 && z>=0; i++,z--) {
            dir.add(new Coordinates(i, z));
            if (i == to.getX() && z == to.getY()) {
                return dir;
            }
        }
        dir.clear();
        for (int i = from.getX()-1,z = from.getY()+1; i>=0 && z<8 ; i--,z++) {
            dir.add(new Coordinates(i, z));
            if (i == to.getX() && z == to.getY()) {
                return dir;
            }
        }
        dir.clear();
        for (int i = from.getX()-1,z = from.getY()-1; i >= 0 && z>=0; i--,z--) {
            dir.add(new Coordinates(i, z));
            if (i == to.getX() && z == to.getY()) {
                return dir;
            }
        }

        if (from.getY() == to.getY()) {
            dir.clear();
            for (int i = from.getX() + 1; i < 8; i++) {
                dir.add(new Coordinates(i, to.getY()));
                if (i == to.getX()) {
                    return dir;
                }
            }

            dir.clear();
            for (int i = from.getX() - 1; i >= 0; i--) {
                dir.add(new Coordinates(i, to.getY()));
                if (i == to.getX()) {
                    return dir;
                }
            }
        }
        if (from.getX() == to.getX()) {
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
        return "Queen";
    }
    public PieceColor getColor(){
                return color;
            }
    public boolean hasColor(PieceColor color){ return this.color == color;}
    public boolean hasType(PieceType type){ return type == PieceType.QUEEN;}
    public  ImageIcon getIcon() {
        return icon;
    }
    public void setMoved(boolean moved){}
    public boolean isMoved(){return false;}
}
