package Chess.Pieces;

import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class King implements Piece {
    private boolean isWhite;
    private ImageIcon icon;

    public King(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/Chess/resources/King_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/Chess/resources/King_Black.png"));
        }
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public List MoveList(int x0, int y0, int x, int y) {
        List dir = new ArrayList<Coordinates>();
        if (x == (x0+1) && y == (y0+1)){
            dir.add(new Coordinates(x,y));
        }
        return dir;
    }

    @Override
    public String getDescription() {
        if (isWhite){
            return "King"+"White";
        }else{
            return "King"+"Black";
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }
}
