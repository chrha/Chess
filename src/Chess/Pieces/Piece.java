package Chess.Pieces;

import javax.swing.*;
import java.util.List;

/**
 * Created by Ilian on 2016-04-06.
 */
public interface Piece {
    boolean isWhite();
    List MoveList(int x0, int y0, int x, int y);
    String getDescription();
    ImageIcon getIcon();
}
