package Chess.Pieces;


import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Rook implements Piece {
    private boolean isWhite;
    private ImageIcon icon;

    public Rook(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Rook_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Rook_Black.png"));
        }
    }
    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public List MoveList(int x0, int y0, int x, int y) {
        List dir = new ArrayList<Coordinates>();
        for (int i = x0+1; i<8 ; i++){
            dir.add(new Coordinates(i,y));
            if (i == x && y0 == y){
                return dir;
            }
        }
        dir.clear();
        for (int i = x0-1; i>0 ; i--) {
            dir.add(new Coordinates(i,y));
            if (i == x && y0 == y) {
                return dir;
            }
        }
        dir.clear();
        for (int i = y0+1; i<8 ; i++) {
            dir.add(new Coordinates(x,i));
            if (i == y && x0 == x) {
                return dir;
            }
        }
        dir.clear();
        for (int i = y0-1; i>0 ; i--) {
            dir.add(new Coordinates(x,i));
            if (i == y && x0 == x) {
                return dir;
            }
        }
        dir.clear();
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
    public void setMoved(boolean moved){}
}
