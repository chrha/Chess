package Chess.Pieces;

import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class Knight implements Piece{
    private boolean isWhite;
    private ImageIcon icon;

    public Knight(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Knight_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/Chess/resources/Knight_Black.png"));
        }

    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public List MoveList(int x0, int y0, int x, int y) {
        List dir = new ArrayList<Coordinates>();
        double dis = Math.sqrt(Math.pow((x0-x),2) + Math.pow((y0-y),2));
        if (dis == Math.sqrt(5)){
            dir.add(new Coordinates(x,y));
        }
        return dir;
    }

    @Override
    public String getDescription() {
        return "Knight";
    }
    public ImageIcon getIcon() {
        return icon;
    }
    public void setMoved(boolean moved){}
}
