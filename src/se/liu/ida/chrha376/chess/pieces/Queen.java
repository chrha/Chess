package se.liu.ida.chrha376.chess.pieces;

import se.liu.ida.chrha376.chess.Coordinates;
import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as Piece on Board.
 */
public class Queen implements Piece{
    private boolean white;
    private ImageIcon icon;

    public Queen(boolean white) {
        this.white = white;
        if (white){
            icon = new ImageIcon(getClass().getResource("/chess/resources/Queen_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/Queen_Black.png"));
        }
    }

    @Override
    public boolean isWhite() {
        return white;
    }

    @Override
    public List<Coordinates> moveList(Coordinates from, Coordinates to) {
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
        dir.clear();
        for (int i = from.getX()+1; i<8 ; i++){
            dir.add(new Coordinates(i,to.getY()));
            if (i == to.getX() && from.getY() == to.getY()){
                return dir;
            }
        }

        dir.clear();
        for (int i = from.getX()-1; i>=0 ; i--) {
            dir.add(new Coordinates(i,to.getY()));
            if (i == to.getX() && from.getY() == to.getY()) {
                return dir;
            }
        }
        dir.clear();
        for (int i = from.getY()+1; i<8 ; i++) {
            dir.add(new Coordinates(to.getX(),i));
            if (i == to.getY() && from.getX() == to.getX()) {
                return dir;
            }
        }
        dir.clear();
        for (int i = from.getY()-1; i>=0 ; i--) {
            dir.add(new Coordinates(to.getX(),i));
            if (i == to.getY() && from.getX() == to.getX()) {
                return dir;
            }
        }
        dir.clear();
        return dir;
    }
    @Override
    public String getDescription() {
        return "Queen";
    }
    public  ImageIcon getIcon() {
        return icon;
    }
    public void setMoved(boolean moved){}
    public boolean isMoved(){return false;}
}
