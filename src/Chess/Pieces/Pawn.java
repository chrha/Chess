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
    public Pawn(boolean isWhite) {
        this.isWhite = isWhite;
        this.moved=false;
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

        if(isWhite()){
            if(!moved){
                if( y==y0+1 || y==y0+2 ){
                    dir.add(new Coordinates(x,y));
                }
                this.moved = true;
            }else if(y==y0+1){
                dir.add(new Coordinates(x,y));
            }
        }else {
            if(!moved){
                if(y==y0-1 || y==y0-2 ){
                    dir.add(new Coordinates(x,y));
                }
                this.moved = true;
            }else if(y==y0-1){
                dir.add(new Coordinates(x,y));
            }

        }
        return dir;
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
