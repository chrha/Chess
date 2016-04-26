package Chess.Pieces;

import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Pawn extends AbstractSpecialPiece implements Piece {
    private boolean isWhite;
    private ImageIcon icon;
    private boolean moved;
    public Pawn(boolean isWhite,boolean moved) {
        super(moved);
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Pawn_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Pawn_Black.png"));
        }
    }

    @Override
    public boolean isWhite() {
        return isWhite;

    }

    @Override
    public List<Coordinates> MoveList(Coordinates from,Coordinates to) {
        List dir = new ArrayList<Coordinates>();

        if(isWhite()){
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
    public String getDescription() {
        return "Pawn";
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
