package Chess.Pieces;


import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Rook extends AbstractSpecialPiece implements Piece {
    private boolean isWhite;
    private ImageIcon icon;
    private boolean moved;

    public Rook(boolean isWhite,boolean moved) {
        super(moved);
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
    public List MoveList(Coordinates from,Coordinates to) {
        List dir = new ArrayList<Coordinates>();
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
        return "Rook";
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
