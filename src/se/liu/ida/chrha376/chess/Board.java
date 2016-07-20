package se.liu.ida.chrha376.chess;

import se.liu.ida.chrha376.chess.pieces.Bishop;
import se.liu.ida.chrha376.chess.pieces.King;
import se.liu.ida.chrha376.chess.pieces.Knight;
import se.liu.ida.chrha376.chess.pieces.Pawn;
import se.liu.ida.chrha376.chess.pieces.Piece;
import se.liu.ida.chrha376.chess.pieces.PieceColor;
import se.liu.ida.chrha376.chess.pieces.PieceType;
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
        if (isValidMove(from, to)) {
            replace(from, to);
        }if ((hasPieceAt(from) && hasPieceAt(to)) && getPiece(from).hasType(PieceType.KING) && getPiece(to).hasType(PieceType.ROOK)){
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
    public boolean hasPieceAt(Coordinates cord){
        return getPiece(cord)!=null;
    }

    public boolean noObstacle(List<Coordinates> l) {
        if(!l.isEmpty()){
            for (Coordinates c : l.subList(0, l.size() - 1)) {
                if (hasPieceAt(c)) {
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
        if (hasPieceAt(kingpos) && getPiece(kingpos).hasType(PieceType.KING) && getPiece(kingpos).hasColor(turn)) {
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {

                    if (hasPieceAt(new Coordinates(i,j)) && !getPiece(new Coordinates(i,j)).hasColor(color)) {
                        List<Coordinates> possibleMoves = getPiece(new Coordinates(i,j)).getPossibleMoves(new Coordinates(i, j), kingpos);
                        if (getPiece(new Coordinates(i,j)).hasType(PieceType.PAWN)) {
                            if (i != kingpos.getX() && possibleMoves.contains(kingpos)) {
                                return false;
                            }
                        } else if (possibleMoves.contains(kingpos) &&
                                   noObstacle(possibleMoves)) {
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
                if (hasPieceAt(new Coordinates(x,y)) && getPiece(new Coordinates(x,y)).hasType(PieceType.KING) && getPiece(new Coordinates(x,y)).hasColor(color)) {
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
        Piece k = getPiece(to);
        squares[to.getX()][to.getY()] = squares[from.getX()][from.getY()];
        squares[from.getX()][from.getY()] = null;
        squares[to.getX()][to.getY()].setMoved(true);
        if (k!= null && k.hasColor(PieceColor.WHITE)){
            deadPiecesWhite.add(k);
        }else if(k != null && k.hasColor(PieceColor.BLACK)) {
            deadPiecesBlack.add(k);}
        if (!isKingSafe(turn)) {
            if (k != null && k.hasColor(PieceColor.WHITE)){
                deadPiecesWhite.remove(k);
            }else if(k != null && k.hasColor(PieceColor.BLACK)){
                deadPiecesBlack.remove(k);
            }
            squares[from.getX()][from.getY()] = getPiece(to);
            squares[to.getX()][to.getY()] = k;
            changeTurn();
        }
        changeTurn();
        notifyListeners();
    }

    public boolean movePossible(Coordinates from, Coordinates to) {
        return (hasPieceAt(from) && turn == getPiece(from).getColor()) &&
               (((hasPieceAt(to) && getPiece(from).getColor() != getPiece(to).getColor()) ||
                 getPiece(to) == null) && getPiece(from).getPossibleMoves(from, to).contains(to));

    }

    /**
     * Checks if is checkmate by checking if any of your pieces can move in a certain way to protect the king.
     * Method overly complex and cant be simplified.
     * @return If you are in checkmate.
     */
    public boolean checkmate() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (hasPieceAt(new Coordinates(x, y))&& getPiece(new Coordinates(x, y)).hasColor(turn) ){
                    for (int j = 0; j < 8; j++) {
                        for (int i = 0; i < 8; i++) {
                            if (isValidMove(new Coordinates(x, y), new Coordinates(i, j))) {
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

    public boolean isValidMove(Coordinates from, Coordinates to){

        if (movePossible(from, to)) {
            if (getPiece(from).hasType(PieceType.PAWN)) {
                return validPawnmove(from,to);
            }else if (noObstacle(getPiece(from).getPossibleMoves(from, to))) {

                return true;
            }
        }

        return false;
    }
    public boolean validPawnmove(Coordinates from,Coordinates to){
        for (Coordinates c : getPiece(from).getPossibleMoves(from, to)) {
            if (c.getX() == to.getX() && c.getY() == to.getY()) {
                if (hasPieceAt(to) && (getPiece(from).getColor() != getPiece(to).getColor() && from.getX() != to.getX())) {
                    return true;
                }
                if ((getPiece(c) == null && from.getX() == to.getX())) {
                    if (from.getY() == (to.getY() - 2)) {
                        if (squares[c.getX()][c.getY() - 1] == null && !getPiece(from).isMoved()) {
                            return true;
                        }
                    } else if (from.getY() == (to.getY() + 2)) {
                        if (squares[c.getX()][c.getY() + 1] == null && !getPiece(from).isMoved()) {
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
            squares[from.getX()][from.getY()] = getPiece(to);
            squares[to.getX()][to.getY()] = k;
            return true;
        }
        squares[from.getX()][from.getY()] = getPiece(to);
        squares[to.getX()][to.getY()] = k;
        return false;
    }

    public void checkPawn(Coordinates cor){
        if(hasPieceAt(cor) && getPiece(cor).hasType(PieceType.PAWN) && ((cor.getY() == 7) || (cor.getY() == 0))) {
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

        if((!getPiece(from).isMoved() && !getPiece(to).isMoved()) && (getPiece(from).hasColor( getPiece(to).getColor() )) ){
            if(noObstacle(getPiece(to).getPossibleMoves(to, from))){
                if(from.getX() > to.getX()){
                   help(from,to,-2,-1);
                    }
                }else if(from.getX() < to.getX()) {
                help(from,to,2,1);
            }
        }
        notifyListeners();
    }
    public void help(Coordinates from, Coordinates to, int x,int y){
        if(place(from,new Coordinates((from.getX()+y),from.getY())) &&
           place(from,new Coordinates((from.getX()+x),from.getY()))){
            replace(from,new Coordinates((from.getX()+x),from.getY()));
            changeTurn();
            replace(to,new Coordinates((from.getX()+y),from.getY()));
            squares[from.getX()-1][from.getY()].setMoved(true);
            squares[from.getX()-2][from.getY()].setMoved(true);
        }
    }
    public void changeTurn(){
        if (this.turn == PieceColor.WHITE){
            this.turn = PieceColor.BLACK;
        }else{
            this.turn = PieceColor.WHITE;
        }
    }
    public void boardSetup(){
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (y == 1) {
                    this.squares[x][y] = new Pawn(PieceColor.WHITE, false);
                } else if (y == SIZE - 2) {
                    this.squares[x][y] = new Pawn(PieceColor.BLACK, false);
                }
            }
        }
        this.squares[0][0] = new Rook(PieceColor.WHITE, false);
        this.squares[SIZE - 1][0] = new Rook(PieceColor.WHITE, false);

        this.squares[0][SIZE - 1] = new Rook(PieceColor.BLACK, false);
        this.squares[SIZE - 1][SIZE - 1] = new Rook(PieceColor.BLACK, false);

        this.squares[1][0] = new Knight(PieceColor.WHITE);
        this.squares[SIZE - 2][0] = new Knight(PieceColor.WHITE);

        this.squares[1][SIZE - 1] = new Knight(PieceColor.BLACK);
        this.squares[SIZE - 2][SIZE - 1] = new Knight(PieceColor.BLACK);

        this.squares[2][0] = new Bishop(PieceColor.WHITE);
        this.squares[SIZE - 3][0] = new Bishop(PieceColor.WHITE);

        this.squares[2][SIZE - 1] = new Bishop(PieceColor.BLACK);
        this.squares[SIZE - 3][SIZE - 1] = new Bishop(PieceColor.BLACK);

        this.squares[3][0] = new King(PieceColor.WHITE, false);
        this.squares[3][SIZE - 1] = new King(PieceColor.BLACK, false);

        this.squares[4][0] = new Queen(PieceColor.WHITE);
        this.squares[4][SIZE - 1] = new Queen(PieceColor.BLACK);
    }
}
