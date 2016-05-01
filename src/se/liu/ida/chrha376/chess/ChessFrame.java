package se.liu.ida.chrha376.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Makes all components visable and gets input from MouseListener.
 */
public class ChessFrame extends JFrame implements MouseListener {
    private static final int BLOCK = 70;
    private static final int OFFSET_Y = 32;
    private static final int OFFSET_X = 10;

    private Board board;
    private int currentX;
    private int currentY;

    public ChessFrame(Board board){
        super("chess");

        addMouseListener(this);
        this.board = board;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final ChessComponent chessComp =new ChessComponent(board);
        board.addBoardListener(chessComp);
        this.setLayout(new BorderLayout());
        this.add(chessComp, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }
    public void mousePressed(MouseEvent e) {
        double xd = Math.floor((e.getX()-OFFSET_X)/BLOCK);
        double yd = Math.floor((e.getY()-OFFSET_Y)/BLOCK);
        currentX = (int) xd;
        currentY = (int) yd;

    }

    public void mouseReleased(MouseEvent e) {
        double xd = Math.floor((e.getX()-OFFSET_X)/BLOCK);
        double yd = Math.floor(((e.getY()-OFFSET_Y))/BLOCK);
        int x=(int) xd;
        int y=(int) yd;
        if ((currentX != x || currentY != y) && x>=0 && x<8 && y>=0 && y<8){
            board.movePiece(new Coordinates(currentX,currentY),new Coordinates(x,y));
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

}
