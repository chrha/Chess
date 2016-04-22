package Chess.Pieces;

import Chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class King extends AbstractSpecialPiece implements Piece {
    private boolean isWhite;
    private ImageIcon icon;
    private boolean moved;

    public King(boolean isWhite,boolean moved) {
        super(moved);
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
        if (1 == Math.abs((x0-x)) || 1 == Math.abs((y0-y))){
            dir.add(new Coordinates(x,y));
        }
        return dir;
    }

    @Override
    public String getDescription() {
        return "King";
    }
    public ImageIcon getIcon() {
        return icon;
    }
    public void setMoved(boolean moved){}
}
