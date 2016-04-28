package Chess;

import Chess.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ilian on 2016-04-06.
 */
public class ChessComponent extends JComponent implements BoardListener {
    private final static int BLOCK = 70;
    private final static int INFO_BAR = 200;
    private final static int FONT_SIZE = 20;
    private final static int TURNSTRING_OFFSET = 60;
    private Board board;

    public ChessComponent(Board board) {
        this.board = board;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        for (int y = 0; y < Board.SIZE; y++) {
            for (int x = 0; x < Board.SIZE; x++) {
                if (y % 2 != 0) {
                    if (x % 2 != 0) {
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);
                    } else {
                        g2d.setColor(Color.WHITE);
                        g2d.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);
                    }
                } else {
                    if (x % 2 == 0) {
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);
                    } else {
                        g2d.setColor(Color.WHITE);
                        g2d.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);

                    }
                }
                if (board.getPiece(new Coordinates(x,y)) != null){
                    board.getPiece(new Coordinates(x,y)).getIcon().paintIcon(this,g2d,x*BLOCK,y*BLOCK);
                }


            }
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE));
        g2d.drawString("Turn: ",Board.SIZE * BLOCK,Board.SIZE * BLOCK/2);
        if (board.isTurn()){
            g2d.setColor(Color.BLACK);
            g2d.drawString("White",Board.SIZE * BLOCK+TURNSTRING_OFFSET,Board.SIZE * BLOCK/2);
        }else{
            g2d.setColor(Color.BLACK);
            g2d.drawString("Black",Board.SIZE * BLOCK+TURNSTRING_OFFSET,Board.SIZE * BLOCK/2);
        }
        if (board.getDeadPieces_White() != null){
            int row = 0;
            int column =0;
            int pawn_count=0;
            for (Piece p : board.getDeadPieces_White() ){
                if ( p.getDescription() == "Pawn"&& pawn_count==0){
                    p.getIcon().paintIcon(this,g2d,Board.SIZE * BLOCK+BLOCK*row ,BLOCK*column);
                    pawn_count++;
                    row++;
                    if (row>2){
                        column++;
                        row=0;
                    }
                }else if (p.getDescription() != "Pawn"){
                    p.getIcon().paintIcon(this,g2d,Board.SIZE * BLOCK+BLOCK*row ,BLOCK*column);
                    row++;
                    if (row>2){
                        column++;
                        row =0;
                    }
                }

            }

        }
        if (board.getDeadPieces_Black() != null){
            int row = 0;
            int column =-1;
            int pawn_count=0;
            for (Piece p : board.getDeadPieces_Black() ){
                if ( p.getDescription() == "Pawn"&& pawn_count==0){
                    p.getIcon().paintIcon(this,g2d,Board.SIZE * BLOCK+BLOCK*row ,Board.SIZE * BLOCK+BLOCK*column);
                    pawn_count++;
                    row++;
                    if (row>2){
                        column--;
                        row=0;
                    }
                }else if (p.getDescription() != "Pawn"){
                    p.getIcon().paintIcon(this,g2d,Board.SIZE * BLOCK+BLOCK*row ,Board.SIZE * BLOCK+BLOCK*column);
                    row++;
                    if (row>2){
                        column--;
                        row =0;
                    }
                }
            }
        }
}
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(Board.SIZE * BLOCK+INFO_BAR, Board.SIZE* BLOCK);
    }

    public void BoardChanged(){
        repaint();
    }
}