package Chess.Pieces;

import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Pawn implements Piece {
    private boolean isWhite;
    private ImageIcon icon;
    private boolean moved;
    public Pawn(boolean isWhite,boolean moved) {
        this.isWhite = isWhite;
        this.moved= moved;
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
    public List MoveList(int x0, int y0, int x, int y) {
        List dir = new ArrayList<Coordinates>();
       /** if (isWhite()){
            if (!this.moved && ((x == x0+2 || x == x0+1) && (y == y0+1  || y == )
        }*/

        if(isWhite()){
            if(!moved){
                if (((x0+1)<=7)){
                    dir.add(new Coordinates(x0+1,y0+1));
                }if (((x0-1)>=0)){
                    dir.add(new Coordinates(x0-1,y0+1));
                }
                dir.add(new Coordinates(x0,y0+1));
                dir.add(new Coordinates(x0,y0+2));




            }else{
                if (((x0+1)<=7)){
                    dir.add(new Coordinates(x0+1,y0+1));
                }if (((x0-1)>=0)){
                    dir.add(new Coordinates(x0-1,y0+1));
                }
                dir.add(new Coordinates(x0,y0+1));
            }
        }else {
            if (!moved) {
                if (((x0 + 1) <= 7)) {
                    dir.add(new Coordinates(x0 + 1, y0 - 1));
                }
                if (((x0 - 1) >= 0)) {
                    dir.add(new Coordinates(x0 - 1, y0 - 1));
                }
                dir.add(new Coordinates(x0, y0 - 1));
                dir.add(new Coordinates(x0, y0 - 2));


            } else {
                if (((x0 + 1) <= 7)) {
                    dir.add(new Coordinates(x0 + 1, y0 - 1));
                }
                if (((x0 - 1) >= 0)) {
                    dir.add(new Coordinates(x0 - 1, y0 - 1));
                }
                dir.add(new Coordinates(x0, y0 - 1));
            }
        }
        return dir;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    @Override
    public String getDescription() {
        if (isWhite){
            return "Pawn"+"White";
        }else{

            return "Pawn"+"Black";
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
