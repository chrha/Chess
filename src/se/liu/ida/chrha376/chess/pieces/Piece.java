package se.liu.ida.chrha376.chess.pieces;

import se.liu.ida.chrha376.chess.Coordinates;
import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.List;

/**
 * Interface for all pieces that are used in board.
 */
public interface Piece {
    void setMoved(boolean moved);
    boolean isMoved();
    boolean isWhite();
    List<Coordinates> moveList(Coordinates from, Coordinates to);
    String getDescription();
     ImageIcon getIcon();
}
