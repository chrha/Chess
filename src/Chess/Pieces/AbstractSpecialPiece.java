package Chess.Pieces;

public abstract class AbstractSpecialPiece {
    private boolean moved;

    public AbstractSpecialPiece(final boolean moved) {
	this.moved = moved;
    }

    public void setMoved(boolean moved) {
	this.moved = moved;
    }

    public boolean isMoved() {
	return moved;
    }
}
