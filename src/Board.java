import Pieces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 3/8/16.
 */
public class Board {
    public final static int SIZE=8;
    private Piece squares[][];
    private List<BoardListener> boardlisteners;



    public Board() {
        this.squares = new Piece[SIZE][SIZE];
        this.boardlisteners = new ArrayList<>();
        for (int y=0;y<SIZE;y++){
            for (int x = 0;x<SIZE;x++){
                if ( y== 1){
                    this.squares[x][y] = new Pawn(true);
                }else if (y == SIZE-2){
                    this.squares[x][y] = new Pawn(false);
                }else if ((x == 0 && y==0) || (x==SIZE-1 && y == 0)){
                    this.squares[x][y]= new Rook(true);
                }else if((x==0 && y==SIZE-1) || (x==SIZE-1 && y==SIZE-1)){
                    this.squares[x][y]= new Rook(false);
                }else if((x==1 && y==0) || (x==SIZE-2 && y==0)){
                    this.squares[x][y]= new Knight(true);
                }else if((x==1 && y==SIZE-1) || (x==SIZE-2 && y==SIZE-1)){
                    this.squares[x][y]=  new Knight(false);
                }else if((x==2 && y==0) || (x==SIZE-3 && y==0)){
                    this.squares[x][y]=new Bishop(true);
                }else if((x==2 && y==SIZE-1) || (x==SIZE-3 && y== SIZE-1)){
                    this.squares[x][y]= new Bishop(false);
                }else if((x==3 && y==0)){
                    this.squares[x][y]=new King(true);
                }else if((x==3 && y==SIZE-1)){
                    this.squares[x][y]=new King(false);
                }else if(x==4 && y==0){
                    this.squares[x][y]=new Queen(true);
                }else if(x==4 && y==SIZE-1){
                    this.squares[x][y]=new Queen(false);
                }

            }
        }
    }

    public Piece[][] getSquares() {
        return squares;
    }

    public Piece getPiece(int x, int y){
        return this.squares[x][y];
    }

    public void movePiece(int x0,int y0,int x,int y){
        if (squares[x0][y0] != null  && !(x0==x && y0 ==y)  && squares[x0][y0].canMove(x,y) || canReplace(x0, y0, x, y)){
            squares[x][y]=squares[x0][y0];
            squares[x0][y0]=null;
            notifyListeners();
        }

    }
    public void addBoardListener(BoardListener bl) {
        boardlisteners.add(bl);
    }

    public void notifyListeners() {
        for (BoardListener listener : boardlisteners) {
            listener.BoardChanged();
        }
    }
    public boolean canReplace(int x0, int y0, int x, int y){
        if(squares[x][y] != null && (squares[x0][y0].isWhite()!=squares[y][x].isWhite()) ){
            return true;
        }
       return false;
    }
}
