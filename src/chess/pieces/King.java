package chess.pieces;

import chess.Coordinates;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public class King extends AbstractSpecialPiece implements Piece {
    private boolean white;
    private ImageIcon icon;

    public King(boolean isWhite,boolean moved) {
        super(moved);
        this.white = isWhite;
        if (isWhite){
            icon = new ImageIcon(getClass().getResource("/chess/resources/King_White.png"));
        }else{
            icon = new ImageIcon(getClass().getResource("/chess/resources/King_Black.png"));
        }
    }

    @Override
    public boolean isWhite() {
        return white;
    }

    @Override
    public List<Coordinates> moveList(Coordinates from, Coordinates to) {
        List<Coordinates> dir = new ArrayList<>();
        if ((int)Math.sqrt(Math.pow(from.getX()-to.getX(),2)+Math.pow(from.getY()-to.getY(),2))==1 || (int)Math.sqrt(Math.pow(from.getX()-to.getX(),2)+Math.pow(from.getY()-to.getY(),2))==Math.sqrt(2)){
            dir.add(new Coordinates(to.getX(),to.getY()));
        }
        return dir;
    }

    @Override
    public String getDescription() {
        return "King";
    }
    public  ImageIcon getIcon() {
        return icon;
    }
}
