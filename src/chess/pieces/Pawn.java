package chess.pieces;

import chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Pawn implements Piece {
    private boolean white;
    private ImageIcon icon;
    private boolean moved;
    public Pawn(boolean white, boolean moved) {
        this.moved = moved;
        this.white = white;
        if (white){
            icon = new ImageIcon(getClass().getResource("/chess/resources/Pawn_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/Pawn_Black.png"));
        }
    }

    @Override
    public boolean isWhite() {
        return white;

    }

    @Override
    public List<Coordinates> moveList(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();

        if(white){
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
