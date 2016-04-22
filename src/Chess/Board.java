package Chess;

import Chess.Pieces.*;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by chris on 3/8/16.
 */
public class Board
{
    public final static int SIZE = 8;
    private Piece squares[][];
    private List<BoardListener> boardlisteners;
    private boolean turn = true;


    public Board() {
        this.squares = new Piece[SIZE][SIZE];
        this.boardlisteners = new ArrayList<>();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (y == 1) {
                    this.squares[x][y] = new Pawn(true, false);
                } else if (y == SIZE - 2) {
                    this.squares[x][y] = new Pawn(false, false);
                } else if ((x == 0 && y == 0) || (x == SIZE - 1 && y == 0)) {
                    this.squares[x][y] = new Rook(true, false);
                } else if ((x == 0 && y == SIZE - 1) || (x == SIZE - 1 && y == SIZE - 1)) {
                    this.squares[x][y] = new Rook(false, false);
                } else if ((x == 1 && y == 0) || (x == SIZE - 2 && y == 0)) {
                    this.squares[x][y] = new Knight(true);
                } else if ((x == 1 && y == SIZE - 1) || (x == SIZE - 2 && y == SIZE - 1)) {
                    this.squares[x][y] = new Knight(false);
                } else if ((x == 2 && y == 0) || (x == SIZE - 3 && y == 0)) {
                    this.squares[x][y] = new Bishop(true);
                } else if ((x == 2 && y == SIZE - 1) || (x == SIZE - 3 && y == SIZE - 1)) {
                    this.squares[x][y] = new Bishop(false);
                } else if ((x == 3 && y == 0)) {
                    this.squares[x][y] = new King(true, false);
                } else if ((x == 3 && y == SIZE - 1)) {
                    this.squares[x][y] = new King(false, false);
                } else if (x == 4 && y == 0) {
                    this.squares[x][y] = new Queen(true);
                } else if (x == 4 && y == SIZE - 1) {
                    this.squares[x][y] = new Queen(false);
                }

            }
        }
    }

    public Piece[][] getSquares() {
        return squares;
    }

    public Piece getPiece(int x, int y) {
        return this.squares[x][y];
    }

    /**
     * public void movePiece(int x0,int y0,int x,int y){ if ( ( (squares[x][y]!=null && (squares[x0][y0].isWhite() !=
     * squares[x][y].isWhite())) || squares[x][y] == null ) && squares[x0][y0].canMove(x0,y0,x,y)) {
     * squares[x][y]=squares[x0][y0]; squares[x0][y0]=null; } notifyListeners(); }
     */
    public void movePiece(int x0, int y0, int x, int y) {
        if (squares[x0][y0] != null && turn == squares[x0][y0].isWhite()){
            if (((squares[x][y] != null && squares[x0][y0].isWhite() != squares[x][y].isWhite()) || squares[x][y] == null) &&
                        squares[x0][y0].MoveList(x0, y0, x, y).contains(new Coordinates(x, y))) {
                        if (squares[x0][y0].getDescription() == "Pawn") {
                            movePawn(x0, y0, x, y);
                        } else if (Obstacle(squares[x0][y0].MoveList(x0, y0, x, y))) {
                            squares[x][y] = squares[x0][y0];
                            squares[x0][y0] = null;
                            if (!isKingSafe()){

                            }
                            this.turn = !turn;

                        }

                    }
            System.out.println(isKingSafe());
            System.out.println();
            notifyListeners();

                }

        }



    public boolean Obstacle(List<Coordinates> l) {
        System.out.println(l.toString());
        for (Coordinates c : l.subList(0,l.size()-1)) {
            if (squares[c.getX()][c.getY()] != null) {
                return false;
            }
        }
        return true;
    }

    public void movePawn(int x0, int y0, int x, int y) {
        List<Coordinates> l = squares[x0][y0].MoveList(x0, y0, x, y);
        for (Coordinates c : l) {
            System.out.println(l.toString());
            if (c.getX() == x && c.getY() == y) {
                if (squares[x][y] != null && (squares[x0][y0].isWhite() != squares[x][y].isWhite() && x0 != x)) {

                    squares[x][y] = squares[x0][y0];
                    squares[x0][y0] = null;
                    squares[x][y].setMoved(true);
                    this.turn = !turn;
                }
                if ((squares[c.getX()][c.getY()] == null && x0 == x)) {
                    if (y0 == (y-2)) {
                        if (squares[c.getX()][c.getY() - 1] == null) {
                            squares[x][y] = squares[x0][y0];
                            squares[x0][y0] = null;
                            squares[x][y].setMoved(true);
                            this.turn = !turn;
                        }
                    } else if (y0 == (y+2)){
                        if (squares[c.getX()][c.getY() + 1] == null) {
                            squares[x][y] = squares[x0][y0];
                            squares[x0][y0] = null;
                            squares[x][y].setMoved(true);
                            this.turn = !turn;

                        }

                    }else{
                        squares[x][y] = squares[x0][y0];
                        squares[x0][y0] = null;
                        squares[x][y].setMoved(true);
                        this.turn = !turn;
                    }

                }
            }

        }
        notifyListeners();
    }

    public void addBoardListener(BoardListener bl) {
        boardlisteners.add(bl);
    }

    public void notifyListeners() {
        for (BoardListener listener : boardlisteners) {
            listener.BoardChanged();
        }
    }
    public boolean isKingSafe(){
        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                if (squares[x][y]!=null && squares[x][y].getDescription() == "King"){
                    if (squares[x][y].isWhite()){
                        for (int i = 0; i<8;i++){
                            for (int j = 0; j<8;j++){
                                if (squares[j][i]!=null && !squares[j][i].isWhite()){
                                    if (squares[j][i].MoveList(j,i,x,y).contains(new Coordinates(x,y))){
                                        return !Obstacle(squares[j][i].MoveList(j,i,x,y));
                                    }
                                }
                            }
                        }
                    }else if (!squares[x][y].isWhite()){
                        for (int i = 0; i<8;i++){
                            for (int j = 0; j<8;j++){
                                if (squares[j][i]!=null && squares[j][i].isWhite()){
                                    if (squares[j][i].MoveList(j,i,x,y).contains(new Coordinates(x,y))){
                                        return !Obstacle(squares[j][i].MoveList(j,i,x,y));
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        return true;
    }
}
