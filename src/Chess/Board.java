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

    public Piece getPiece(Coordinates cor) {
        return this.squares[cor.getX()][cor.getY()];
    }

    public void movePiece(Coordinates from, Coordinates to) {
        if (movePossible(from,to)){
            if (squares[from.getX()][from.getY()].getDescription() == "Pawn") {
                movePawn(from,to);
            } else if (Obstacle(squares[from.getX()][from.getY()].MoveList(from,to))) {
                replace(from,to);
            }
        }
        System.out.println(checkMate(turn));
        notifyListeners();
    }

    public boolean Path(List<Coordinates> l) {
           for (Coordinates c : l) {
               if (squares[c.getX()][c.getY()] != null) {
                   return true;
               }
           }

           return false;
       }
    public boolean Obstacle(List<Coordinates> l) {
        for (Coordinates c : l.subList(0,l.size()-1)) {
            if (squares[c.getX()][c.getY()] != null) {
                return false;
            }
        }

        return true;
    }

    public void movePawn(Coordinates from,Coordinates to) {
        List<Coordinates> l = squares[from.getX()][from.getY()].MoveList(from,to);
        Piece k=squares[to.getX()][to.getY()];
        for (Coordinates c : l) {
            if (c.getX() == to.getX() && c.getY() == to.getY()) {
                if (squares[to.getX()][to.getY()] != null && (squares[from.getX()][from.getY()].isWhite() != squares[to.getX()][to.getY()].isWhite() && from.getX() != to.getX())) {
                    replace(from,to);
                }
                if ((squares[c.getX()][c.getY()] == null && from.getX() == to.getX())) {
                    if (from.getY() == (to.getY()-2)) {
                        if (squares[c.getX()][c.getY() - 1] == null) {
                            replace(from,to);
                        }
                    } else if (from.getY() == (to.getY()+2)){
                        if (squares[c.getX()][c.getY() + 1] == null) {
                            replace(from,to);
                        }
                    }else{
                        replace(from,to);
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
    public boolean isKingSafe(boolean isWhite){
        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                if (squares[x][y]!=null && squares[x][y].getDescription() == "King"){
                    if (squares[x][y].isWhite()==isWhite){
                        for (int i = 0; i<8;i++){
                            for (int j = 0; j<8;j++){
                                if (squares[j][i]!=null && squares[j][i].isWhite()!= isWhite){
                                    if (squares[j][i].getDescription() == "Pawn"){
                                        if (j != x && squares[j][i].MoveList(new Coordinates(j,i),new Coordinates(x,y)).contains(new Coordinates(x,y))){
                                            return false;
                                        }
                                    }else if (squares[j][i].MoveList(new Coordinates(j,i),new Coordinates(x,y)).contains(new Coordinates(x,y))){

                                        return !Path(squares[j][i].MoveList(new Coordinates(j,i),new Coordinates(x,y)));
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

    public void replace(Coordinates from,Coordinates to){
        Piece k=squares[to.getX()][to.getY()];
        squares[to.getX()][to.getY()] = squares[from.getX()][from.getY()];
        squares[from.getX()][from.getY()] = null;
        squares[to.getX()][to.getY()].setMoved(true);
        if (!isKingSafe(turn)){
            squares[from.getX()][from.getY()] = squares[to.getX()][to.getY()];
            squares[to.getX()][to.getY()] = k;
            this.turn = !turn;
        }
        this.turn = !turn;
        notifyListeners();
    }
    public boolean movePossible(Coordinates from,Coordinates to){
        return (squares[from.getX()][from.getY()] != null && turn == squares[from.getX()][from.getY()].isWhite()) &&
               (((squares[to.getX()][to.getY()] != null && squares[from.getX()][from.getY()].isWhite() != squares[to.getX()][to.getY()].isWhite()) || squares[to.getX()][to.getY()] == null) &&
                squares[from.getX()][from.getY()].MoveList(from, to).contains(to));

    }
    public boolean checkMate(boolean isWhite){
        for (int y = 0; y<8;y++){
            for (int x = 0; x<8;x++){
                if (squares[x][y] !=null && squares[x][y].getDescription() == "King"){
                    if (squares[x][y].isWhite()==isWhite){
                        for (int i = 0; i<8;i++){
                            for (int j = 0; j<8;j++){
                                if (squares[j][i]!=null && squares[j][i].isWhite()== isWhite){
                                    if (j != x && squares[j][i].MoveList(new Coordinates(j,i),new Coordinates(x,y)).contains(new Coordinates(x,y))){
                                        return false;
                                    }
                                    return checkcheckmate(new Coordinates(j,i),squares[j][i].MoveList(new Coordinates(j,i),new Coordinates(x,y)));
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    public boolean checkcheckmate(Coordinates pos,List<Coordinates> l){
        for (Coordinates c: l){
            Piece k = squares [c.getX()][c.getY()];
            squares[c.getX()][c.getY()] = new Pawn(true,true);
            if (isKingSafe(turn)){
                squares[c.getX()][c.getY()] = k;
                return !Obstacle(squares[pos.getX()][pos.getY()].MoveList(pos,c));
            }
            squares[c.getX()][c.getY()] = k;
        }
        return true;
    }

}
