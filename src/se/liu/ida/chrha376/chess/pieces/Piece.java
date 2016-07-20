package se.liu.ida.chrha376.chess.pieces;

import se.liu.ida.chrha376.chess.Coordinates;

import javax.swing.*;
import java.util.List;

/**
 * Interface for all pieces that are used in board.
 */
public interface Piece {
    void setMoved(boolean moved);
    boolean isMoved();
    PieceColor getColor();
    boolean hasColor(PieceColor color);
    List<Coordinates> getPossibleMoves(Coordinates from, Coordinates to);
    boolean hasType(PieceType type);
    String getDescription();
     ImageIcon getIcon();
}
