import Pieces.*;

/**
 * Created by chris on 3/8/16.
 */
public class Board {
    public final static int SIZE=8;
    private Piece squares[][];

    public Piece[][] getSquares() {
        return squares;
    }

    public Board() {
        this.squares = new Piece[SIZE][SIZE];
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
    public Piece getPiece(int x, int y){
        return this.squares[x][y];
    }
}
