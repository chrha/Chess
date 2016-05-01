package chess;

/**
 * Is used by classes that need to know if the board has been changed.
 */
public interface BoardListener {
    void boardChanged();
}
