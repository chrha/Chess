package chess;

import chess.pieces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by chris on 3/8/16.
 */
public class Board
{
    public final static int SIZE = 8;
    private Piece[][] squares;
    private List<Piece> deadPiecesWhite = new ArrayList<>();
    private List<Piece> deadPiecesBlack = new ArrayList<>();
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

    public List<Piece> getDeadPiecesWhite() {
        return deadPiecesWhite;
    }

    public List<Piece> getDeadPiecesBlack() {
        return deadPiecesBlack;
    }

    public boolean isTurn() {
            return turn;
        }
    public Piece getPiece(Coordinates cor) {
        return this.squares[cor.getX()][cor.getY()];
    }

    public void movePiece(Coordinates from, Coordinates to) {
        if (isValid(from,to)) {
            replace(from, to);
        }if ((getPiece(from)!=null && getPiece(to)!=null)&&getPiece(from).getDescription().equals("King") && getPiece(to).getDescription().equals("Rook")){
            rockade(from,to);
        }
        if (checkmate()){
            int reply = JOptionPane.showConfirmDialog(null, "CHECKMATE! \n Restart?", "chess", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION){
                restart();
            }else{
                System.exit(0);
            }
        }
            checkPawn(to);
            notifyListeners();
    }

    public boolean noObstacle(List<Coordinates> l) {
        if(!l.isEmpty()){
            for (Coordinates c : l.subList(0, l.size() - 1)) {
                if (squares[c.getX()][c.getY()] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addBoardListener(BoardListener bl) {
        boardlisteners.add(bl);
    }

    public void notifyListeners() {
        for (BoardListener listener : boardlisteners) {
            listener.boardChanged();
        }
    }

    public boolean isKingSafe(boolean isWhite) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (squares[x][y] != null && squares[x][y].getDescription().equals("King") && squares[x][y].isWhite() == isWhite) {
                    for (int j = 0; j < 8; j++) {
                        for (int i = 0; i < 8; i++) {
                            if (squares[i][j] != null && squares[i][j].isWhite() != isWhite) {
                                if (squares[i][j].getDescription().equals("Pawn")){
                                    if (i != x && squares[i][j].moveList(new Coordinates(i, j), new Coordinates(x, y)).contains(new Coordinates(x, y))){
                                        return false;
                                    }
                                } else if (squares[i][j].moveList(new Coordinates(i, j), new Coordinates(x, y))
                                                   .contains(new Coordinates(x, y)) &&
                                           noObstacle(squares[i][j].moveList(new Coordinates(i, j), new Coordinates(x, y)))) {
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
        if (k!= null && k.isWhite()){
            deadPiecesWhite.add(k);
        }else if(k!=null && !k.isWhite() ) {
            deadPiecesBlack.add(k);}
        if (!isKingSafe(turn)) {
            if (k!= null && k.isWhite()){
                deadPiecesWhite.remove(k);
            }else if(k!=null && !k.isWhite() ){
                deadPiecesBlack.remove(k);
            }
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
                 squares[to.getX()][to.getY()] == null) && squares[from.getX()][from.getY()].moveList(from, to).contains(to));

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
                                System.out.println(place(from,to));
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

            if (squares[from.getX()][from.getY()].getDescription().equals("Pawn")) {
                for (Coordinates c : getPiece(from).moveList(from, to)) {
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
            }else if (noObstacle(squares[from.getX()][from.getY()].moveList(from, to))) {

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
        if(getPiece(cor) != null && getPiece(cor).getDescription().equals("Pawn") && ((cor.getY() == 7) || (cor.getY() == 0))) {
            new NewPieceFrame(this, cor);
        }

    }
    public void set(Coordinates cor,Piece p){
        squares[cor.getX()][cor.getY()] = p;
        notifyListeners();
    }
    public void restart() {
        clearboard();
        this.turn = true;
        deadPiecesWhite = new ArrayList<>();
        deadPiecesBlack = new ArrayList<>();
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
    public void clearboard() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                this.squares[x][y] = null;
            }
        }
    }

    public void rockade(Coordinates from, Coordinates to){
        if((!getPiece(from).isMoved() && !getPiece(to).isMoved()) && (getPiece(from).isWhite() == getPiece(to).isWhite())){
            if(noObstacle(getPiece(to).moveList(to, from))){
                if(from.getX() > to.getX()){
                    if(place(from,new Coordinates((from.getX()-1),from.getY())) &&
                            place(from,new Coordinates((from.getX()-2),from.getY()))){
                        replace(from,new Coordinates((from.getX()-2),from.getY()));
                        this.turn = !turn;
                        replace(to,new Coordinates((from.getX()-1),from.getY()));
                        squares[from.getX()-1][from.getY()].setMoved(true);
                        squares[from.getX()-2][from.getY()].setMoved(true);
                        System.out.println(squares[from.getX()-2][from.getY()].isMoved());

                    }
                }else if(from.getX() < to.getX()){
                    if(place(from,new Coordinates((from.getX()+1),from.getY())) &&
                            place(from,new Coordinates((from.getX()+2),from.getY()))){
                        replace(from,new Coordinates((from.getX()+2),from.getY()));
                        this.turn = !turn;
                        replace(to,new Coordinates((from.getX()+1),from.getY()));
                        squares[from.getX()+1][from.getY()].setMoved(true);
                        squares[from.getX()+2][from.getY()].setMoved(true);

                    }
                }
            }
        }
        notifyListeners();
    }

}
