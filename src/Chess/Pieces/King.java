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
    public List<Coordinates> MoveList(Coordinates from,Coordinates to) {
        List dir = new ArrayList<Coordinates>();
        if (Math.sqrt(Math.pow(from.getX()-to.getX(),2)+Math.pow(from.getY()-to.getY(),2))==1 || Math.sqrt(Math.pow(from.getX()-to.getX(),2)+Math.pow(from.getY()-to.getY(),2))==Math.sqrt(2)){
            dir.add(new Coordinates(to.getX(),to.getY()));
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
