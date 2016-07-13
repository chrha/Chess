package se.liu.ida.chrha376.chess;

import se.liu.ida.chrha376.chess.pieces.Bishop;
import se.liu.ida.chrha376.chess.pieces.King;
import se.liu.ida.chrha376.chess.pieces.Knight;
import se.liu.ida.chrha376.chess.pieces.Pawn;
import se.liu.ida.chrha376.chess.pieces.Piece;
import se.liu.ida.chrha376.chess.pieces.PieceColor;
import se.liu.ida.chrha376.chess.pieces.Queen;
import se.liu.ida.chrha376.chess.pieces.Rook;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * Board is where all the game mechanics are implemented.
 */
public class Board
{
    /**
     * The absolute size of a chess board.
     */
    public final static int SIZE = 8;
    private Piece[][] squares;
    private Collection<Piece> deadPiecesWhite = new ArrayList<>();
    private Collection<Piece> deadPiecesBlack = new ArrayList<>();
    private List<BoardListener> boardlisteners;
    private PieceColor turn = PieceColor.WHITE;



    public Board() {
        this.squares = new Piece[SIZE][SIZE];
        this.boardlisteners = new ArrayList<>();
        boardSetup();
    }

    public Iterable<Piece> getDeadPiecesWhite() {
        return deadPiecesWhite;
    }

    public Iterable<Piece> getDeadPiecesBlack() {
        return deadPiecesBlack;
    }

    public PieceColor getTurn() {
            return turn;
        }
    public Piece getPiece(Coordinates cor) {
        return this.squares[cor.getX()][cor.getY()];
    }

    public void movePiece(Coordinates from, Coordinates to) {
        if (isValid(from,to)) {
            replace(from, to);
        }if ((getPiece(from)!=null && getPiece(to)!=null)&&getPiece(from).getDescription().equals("King") && getPiece(to).getDescription().equals("Rook")){
            cancel(from, to);
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

    /**
     * Checks if one of the kings are safe by checking if any of the opponets pieces are threatening the king.
     * @param color The color of the king.
     * @return Whether the king is safe or not
     */
    public boolean isKingSafe(PieceColor color) {
        Coordinates kingpos = findKing(color);
        if (squares[kingpos.getX()][kingpos.getY()] != null && squares[kingpos.getX()][kingpos.getY()].getDescription().equals("King") && squares[kingpos.getX()][kingpos.getY()].getColor() == turn) {
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if (squares[i][j] != null && squares[i][j].getColor() != color) {
                        if (squares[i][j].getDescription().equals("Pawn")) {
                            if (i != kingpos.getX() && squares[i][j].moveList(new Coordinates(i, j), new Coordinates(kingpos.getX(), kingpos.getY()))
                                    .contains(new Coordinates(kingpos.getX(), kingpos.getY()))) {
                                return false;
                            }
                        } else if (squares[i][j].moveList(new Coordinates(i, j), new Coordinates(kingpos.getX(), kingpos.getY()))
                                           .contains(new Coordinates(kingpos.getX(), kingpos.getY())) &&
                                   noObstacle(squares[i][j].moveList(new Coordinates(i, j), new Coordinates(kingpos.getX(), kingpos.getY())))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public Coordinates findKing(PieceColor color){
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (squares[x][y] != null && squares[x][y].getDescription().equals("King") && squares[x][y].getColor() == color) {
                    return new Coordinates(x,y);
                }
            }
        }
        return null;
    }

    /**
     *Moves piece only if king is safe and adds dead pieces to a list if
     * one of the oponents pieces is where you are trying to move to.
     * @param from The coordinates for the piece that you are trying to move.
     * @param to The coordinate that you are trying to move the piece to.
     */

    public void replace(Coordinates from, Coordinates to) {
        Piece k = squares[to.getX()][to.getY()];
        squares[to.getX()][to.getY()] = squares[from.getX()][from.getY()];
        squares[from.getX()][from.getY()] = null;
        squares[to.getX()][to.getY()].setMoved(true);
        if (k!= null && k.getColor() == PieceColor.WHITE){
            deadPiecesWhite.add(k);
        }else if(k!=null && k.getColor()== PieceColor.BLACK) {
            deadPiecesBlack.add(k);}
        if (!isKingSafe(turn)) {
            if (k!= null && k.getColor() == PieceColor.WHITE){
                deadPiecesWhite.remove(k);
            }else if(k!=null && k.getColor() == PieceColor.BLACK){
                deadPiecesBlack.remove(k);
            }
            squares[from.getX()][from.getY()] = squares[to.getX()][to.getY()];
            squares[to.getX()][to.getY()] = k;
            ChangeTurn();
        }
        ChangeTurn();
        notifyListeners();
    }

    public boolean movePossible(Coordinates from, Coordinates to) {
        return (squares[from.getX()][from.getY()] != null && turn == squares[from.getX()][from.getY()].getColor()) &&
               (((squares[to.getX()][to.getY()] != null && squares[from.getX()][from.getY()].getColor() != squares[to.getX()][to.getY()].getColor()) ||
                 squares[to.getX()][to.getY()] == null) && squares[from.getX()][from.getY()].moveList(from, to).contains(to));

    }

    /**
     * Checks if is checkmate by checking if any of your pieces can move in a certain way to protect the king.
     * Method overly complex and cant be simplified.
     * @return If you are in checkmate.
     */
    public boolean checkmate() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (getPiece(new Coordinates(x, y)) != null && getPiece(new Coordinates(x, y)).getColor() == turn) {
                    for (int j = 0; j < 8; j++) {
                        for (int i = 0; i < 8; i++) {
                            if (isValid(new Coordinates(x, y), new Coordinates(i, j))) {
                                Coordinates to = new Coordinates(i, j);
                                Coordinates from = new Coordinates(x, y);
                                if (place(from, to)) {
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
                return validPawnmove(from,to);
            }else if (noObstacle(squares[from.getX()][from.getY()].moveList(from, to))) {

                return true;
            }
        }

        return false;
    }
    public boolean validPawnmove(Coordinates from,Coordinates to){
        for (Coordinates c : getPiece(from).moveList(from, to)) {
            if (c.getX() == to.getX() && c.getY() == to.getY()) {
                if (squares[to.getX()][to.getY()] != null && (squares[from.getX()][from.getY()].getColor() != squares[to.getX()][to.getY()].getColor() && from.getX() != to.getX())) {
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
        return false;
    }

    /**
     * Returns if king is safe after a move, by placing that piece in the coordinate.
     * @param from Coordinate for the piece that you are moving
     * @param to Coordinate that you are moving to.
     * @return If the move can save the king.
     */
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
	    /**
             * We are not intrested in the result of NewPieceDialog, only that it shows us our dialog.
             */
            new NewPieceDialog(this, cor);
        }

    }
    public void set(Coordinates cor,Piece p){
        squares[cor.getX()][cor.getY()] = p;
        notifyListeners();
    }
    public void restart() {
        clearboard();
        this.turn = PieceColor.WHITE;
        deadPiecesWhite = new ArrayList<>();
        deadPiecesBlack = new ArrayList<>();
        boardSetup();

    }
    public void clearboard() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                this.squares[x][y] = null;
            }
        }
    }

    /**
     * Metod for canceling.
     * @param from Coordinate for the king.
     * @param to Coordinate for the rook.
     */
    public void cancel(Coordinates from, Coordinates to){
        if((!getPiece(from).isMoved() && !getPiece(to).isMoved()) && (getPiece(from).getColor() == getPiece(to).getColor())){
            if(noObstacle(getPiece(to).moveList(to, from))){
                if(from.getX() > to.getX()){
                    if(place(from,new Coordinates((from.getX()-1),from.getY())) &&
                            place(from,new Coordinates((from.getX()-2),from.getY()))){
                        replace(from,new Coordinates((from.getX()-2),from.getY()));
                        ChangeTurn();
                        replace(to,new Coordinates((from.getX()-1),from.getY()));
                        squares[from.getX()-1][from.getY()].setMoved(true);
                        squares[from.getX()-2][from.getY()].setMoved(true);

                    }
                }else if(from.getX() < to.getX()){
                    if(place(from,new Coordinates((from.getX()+1),from.getY())) &&
                            place(from,new Coordinates((from.getX()+2),from.getY()))){
                        replace(from,new Coordinates((from.getX()+2),from.getY()));
                        ChangeTurn();
                        replace(to,new Coordinates((from.getX()+1),from.getY()));
                        squares[from.getX()+1][from.getY()].setMoved(true);
                        squares[from.getX()+2][from.getY()].setMoved(true);

                    }
                }
            }
        }
        notifyListeners();
    }
    public void ChangeTurn(){
        if (this.turn == PieceColor.WHITE){
            this.turn = PieceColor.BLACK;
        }else{
            this.turn = PieceColor.WHITE;
        }
    }
    /**
     * Method too complex and can not be simplified. Places Pieces on Board in an orthodox chess setup.
     */
    public void boardSetup(){
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (y == 1) {
                    this.squares[x][y] = new Pawn(PieceColor.WHITE, false);
                } else if (y == SIZE - 2) {
                    this.squares[x][y] = new Pawn(PieceColor.BLACK, false);
                } else if ((x == 0 && y == 0) || (x == SIZE - 1 && y == 0)) {
                    this.squares[x][y] = new Rook(PieceColor.WHITE, false);
                } else if ((x == 0 && y == SIZE - 1) || (x == SIZE - 1 && y == SIZE - 1)) {
                    this.squares[x][y] = new Rook(PieceColor.BLACK, false);
                } else if ((x == 1 && y == 0) || (x == SIZE - 2 && y == 0)) {
                    this.squares[x][y] = new Knight(PieceColor.WHITE);
                } else if ((x == 1 && y == SIZE - 1) || (x == SIZE - 2 && y == SIZE - 1)) {
                    this.squares[x][y] = new Knight(PieceColor.BLACK);
                } else if ((x == 2 && y == 0) || (x == SIZE - 3 && y == 0)) {
                    this.squares[x][y] = new Bishop(PieceColor.WHITE);
                } else if ((x == 2 && y == SIZE - 1) || (x == SIZE - 3 && y == SIZE - 1)) {
                    this.squares[x][y] = new Bishop(PieceColor.BLACK);
                } else if ((x == 3 && y == 0)) {
                    this.squares[x][y] = new King(PieceColor.WHITE, false);
                } else if ((x == 3 && y == SIZE - 1)) {
                    this.squares[x][y] = new King(PieceColor.BLACK, false);
                } else if (x == 4 && y == 0) {
                    this.squares[x][y] = new Queen(PieceColor.WHITE);
                } else if (x == 4 && y == SIZE - 1) {
                    this.squares[x][y] = new Queen(PieceColor.BLACK);
                }
            }
        }
    }
}
