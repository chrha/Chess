package Chess.Pieces;

import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Bishop implements Piece{
    private boolean isWhite;
    private ImageIcon icon;

    public Bishop(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Bishop_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Bishop_Black.png"));
        }
    }
    public boolean isWhite(){
        return isWhite;
    }

    @Override
    public List<Coordinates> MoveList(Coordinates from,Coordinates to) {
        List dir = new ArrayList<Coordinates>();
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
        return dir;
    }

    @Override
    public String getDescription() {
        return "Bishop";
    }
    public  ImageIcon getIcon() {
        return icon;
    }
    public void setMoved(boolean moved){}
    public boolean isMoved(){return false;}
}
