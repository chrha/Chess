package Chess.Pieces;

import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Queen implements Piece{
    private boolean isWhite;
    private ImageIcon icon;

    public Queen(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Queen_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Queen_Black.png"));
        }
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public List MoveList(int x0, int y0, int x, int y) {
        List dir = new ArrayList<Coordinates>();
        for (int i = x0,z = y0; i < 8 && z<8; i++,z++) {
            dir.add(new Coordinates(i, z));
            if (i == x && z == y) {
                return dir;
            }
        }

        for (int i = x0,z = y0; i < 8 && z>0; i++,z--) {
            dir.add(new Coordinates(i, z));
            if (i == x && z == y) {
                return dir;
            }
        }
        for (int i = x0,z = y0; i>0 && z<8 ; i--,z++) {
            dir.add(new Coordinates(i, z));
            if (i == x && z == y) {
                return dir;
            }
        }
        for (int i = x0,z = y0; i > 0 && z>0; i--,z--) {
            dir.add(new Coordinates(i, z));
            if (i == x && z == y) {
                return dir;
            }
        }
        for (int i = x0; i<8 ; i++){
            dir.add(new Coordinates(i,y));
            if (i == x && y0 == y){
                return dir;
            }
        }
        dir.clear();
        for (int i = x0; i>0 ; i--) {
            dir.add(new Coordinates(i,y));
            if (i == x && y0 == y) {
                return dir;
            }
        }
        dir.clear();
        for (int i = y0; i<8 ; i++) {
            dir.add(new Coordinates(x,i));
            if (i == y && x0 == x) {
                return dir;
            }
        }
        dir.clear();
        for (int i = y0; i>0 ; i--) {
            dir.add(new Coordinates(x,i));
            if (i == y && x0 == x) {
                return dir;
            }
        }
        return dir;
    }
    @Override
    public String getDescription() {
        if (isWhite){
            return "Queen"+"White";
        }else{
            return "Queen"+"Black";
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
