package Chess.Pieces;

import Chess.Coordinates;

import javax.swing.*;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public interface Piece {
    void setMoved(boolean moved);
    boolean isMoved();
    boolean isWhite();
    List<Coordinates> MoveList(Coordinates from,Coordinates to);
    String getDescription();
     ImageIcon getIcon();
}
