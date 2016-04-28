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
    public boolean isTurn() {
            return turn;
        }
    public Piece[][] getSquares() {
        return squares;
    }

    public Piece getPiece(Coordinates cor) {
        return this.squares[cor.getX()][cor.getY()];
    }

    public void movePiece(Coordinates from, Coordinates to) {
        if (isValid(from,to)) {
            replace(from, to);
            System.out.println(checkmate());
//        if (movePossible(from, to)) {
//            if (squares[from.getX()][from.getY()].getDescription() == "Pawn") {
//                movePawn(from, to);
//            } else if (Obstacle(squares[from.getX()][from.getY()].MoveList(from, to))) {
//               replace(from, to);
//            }
//
//
//        }
            checkPawn(to);
            notifyListeners();
        }
    }

    public boolean Obstacle(List<Coordinates> l) {
        if(!l.isEmpty()){
            for (Coordinates c : l.subList(0, l.size() - 1)) {
                if (squares[c.getX()][c.getY()] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void movePawn(Coordinates from, Coordinates to) {
        List<Coordinates> l = squares[from.getX()][from.getY()].MoveList(from, to);
        for (Coordinates c : l) {
            if (c.getX() == to.getX() && c.getY() == to.getY()) {
                if (squares[to.getX()][to.getY()] != null &&
                    (squares[from.getX()][from.getY()].isWhite() != squares[to.getX()][to.getY()].isWhite() && from.getX() != to.getX())) {
                    replace(from, to);
                }
                if ((squares[c.getX()][c.getY()] == null && from.getX() == to.getX())) {
                    if (from.getY() == (to.getY() - 2)) {
                        if (squares[c.getX()][c.getY() - 1] == null && !squares[from.getX()][from.getY()].isMoved()) {
                            replace(from, to);
                        }
                    } else if (from.getY() == (to.getY() + 2)) {
                        if (squares[c.getX()][c.getY() + 1] == null && !squares[from.getX()][from.getY()].isMoved()) {
                            replace(from, to);
                        }
                    } else {
                        replace(from, to);
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

    public boolean isKingSafe(boolean isWhite) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (squares[x][y] != null && squares[x][y].getDescription() == "King" && squares[x][y].isWhite() == isWhite) {
                    for (int j = 0; j < 8; j++) {
                        for (int i = 0; i < 8; i++) {
                            if (squares[i][j] != null && squares[i][j].isWhite() != isWhite) {
                                if (squares[i][j].getDescription() == "Pawn"){
                                    if (i != x && squares[i][j].MoveList(new Coordinates(i, j), new Coordinates(x, y)).contains(new Coordinates(x, y))){
                                        return false;
                                    }
                                } else if (squares[i][j].MoveList(new Coordinates(i, j), new Coordinates(x, y))
                                                   .contains(new Coordinates(x, y)) &&
                                           Obstacle(squares[i][j].MoveList(new Coordinates(i, j), new Coordinates(x, y)))) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public void replace(Coordinates from, Coordinates to) {
        Piece k = squares[to.getX()][to.getY()];
        squares[to.getX()][to.getY()] = squares[from.getX()][from.getY()];
        squares[from.getX()][from.getY()] = null;
        squares[to.getX()][to.getY()].setMoved(true);
        if (!isKingSafe(turn)) {

            squares[from.getX()][from.getY()] = squares[to.getX()][to.getY()];
            squares[to.getX()][to.getY()] = k;
            this.turn = !turn;
        }
        this.turn = !turn;
        notifyListeners();
    }

    public boolean movePossible(Coordinates from, Coordinates to) {
        return (squares[from.getX()][from.getY()] != null && turn == squares[from.getX()][from.getY()].isWhite()) &&
               (((squares[to.getX()][to.getY()] != null && squares[from.getX()][from.getY()].isWhite() != squares[to.getX()][to.getY()].isWhite()) ||
                 squares[to.getX()][to.getY()] == null) && squares[from.getX()][from.getY()].MoveList(from, to).contains(to));

    }


    public boolean checkmate(){
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (getPiece(new Coordinates(x,y)) != null && getPiece(new Coordinates(x,y)).isWhite() == turn){
                    for (int j = 0; j < 8; j++) {
                        for (int i = 0; i < 8; i++) {
                            if (isValid(new Coordinates(x,y), new Coordinates(i,j))) {
                                Coordinates to =new Coordinates(i,j);
                                Coordinates from =new Coordinates(x,y);

//                                if(getPiece(new Coordinates(x,y)) != null && squares[x][y].getDescription() == "Pawn" && i != x && place(from,to)){
//                                    return false;
//                                }
                                if (place(from,to)){
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }


    public boolean isValid(Coordinates from,Coordinates to){

        if (movePossible(from, to)) {

            if (squares[from.getX()][from.getY()].getDescription() == "Pawn") {
                for (Coordinates c : getPiece(from).MoveList(from,to)) {
                    if (c.getX() == to.getX() && c.getY() == to.getY()) {
                        if (squares[to.getX()][to.getY()] != null && (squares[from.getX()][from.getY()].isWhite() != squares[to.getX()][to.getY()].isWhite() && from.getX() != to.getX())) {

                            return true;
                        }
                        if ((squares[c.getX()][c.getY()] == null && from.getX() == to.getX())) {
                            if (from.getY() == (to.getY() - 2)) {
                                if (squares[c.getX()][c.getY() - 1] == null && !squares[from.getX()][from.getY()].isMoved()) {

                                    return true;
                                }
                            } else if (from.getY() == (to.getY() + 2)) {
                                if (squares[c.getX()][c.getY() + 1] == null && !squares[from.getX()][from.getY()].isMoved()) {

                                    return true;
                                }
                            } else {

                                return true;
                            }

                        }
                    }
                }
            }else if (Obstacle(squares[from.getX()][from.getY()].MoveList(from, to))) {

                return true;
            }
        }

        return false;
    }
    public boolean place(Coordinates from,Coordinates to){
        Piece k = getPiece(to);
        squares[to.getX()][to.getY()] = getPiece(from);
        squares[from.getX()][from.getY()] = null;
        if (isKingSafe(turn)){
            squares[from.getX()][from.getY()] = squares[to.getX()][to.getY()];
            squares[to.getX()][to.getY()] = k;
            return true;
        }
        squares[from.getX()][from.getY()] = squares[to.getX()][to.getY()];
        squares[to.getX()][to.getY()] = k;
        return false;
    }

    public void checkPawn(Coordinates cor){
        if(getPiece(cor) != null && getPiece(cor).getDescription()=="Pawn"  && getPiece(cor).isWhite() && (cor.getY()==7) ) {
            NewPieceFrame c=new NewPieceFrame(this, cor);
        }else if(getPiece(cor) != null && getPiece(cor).getDescription()=="Pawn" && !getPiece(cor).isWhite() && cor.getY()==0){
            new NewPieceFrame(this, cor);
        }

    }
    public void set(Coordinates cor,Piece p){
        squares[cor.getX()][cor.getY()] = p;
        notifyListeners();
    }

}
