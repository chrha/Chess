package chess.pieces;

import chess.Coordinates;

import javax.swing.*;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public interface Piece {
    void setMoved(boolean moved);
    boolean isMoved();
    boolean isWhite();
    List<Coordinates> moveList(Coordinates from, Coordinates to);
    String getDescription();
     ImageIcon getIcon();
}
