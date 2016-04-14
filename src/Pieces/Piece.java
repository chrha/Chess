package Pieces;

import javax.swing.*;

/**
 * Created by Ilian on 2016-04-06.
 */
public interface Piece {
    boolean isWhite();
    boolean canMove(int x0,int y0,int x,int y);
    String getDescription();
    ImageIcon getIcon();
}
