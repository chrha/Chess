package chess;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(final int x, final int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    @Override public boolean equals(final Object o) {
	if (this == o) return true;
	if (!(o instanceof Coordinates)) return false;

	final Coordinates that = (Coordinates) o;

	if (x != that.x) return false;
	if (y != that.y) return false;

	return true;
    }

    @Override public int hashCode() {
	int result = x;
	result = 31 * result + y;
	return result;
    }

	@Override
	public String toString() {
		return "Coordinates{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
