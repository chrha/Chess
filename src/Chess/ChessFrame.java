package Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Ilian on 2016-04-05.
 */
public class ChessFrame extends JFrame implements MouseListener {
    private Board board;
    private static int currentX;
    private int currentY;
    public ChessFrame(Board board){
        super("Chess");

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
        double xd = Math.floor((e.getX()-10)/70);
        double yd = Math.floor((e.getY()-32)/70);
        currentX = (int) xd;
        currentY = (int) yd;

    }

    public void mouseReleased(MouseEvent e) {
        double xd = Math.floor((e.getX()-10)/70);
        double yd = Math.floor(((e.getY()-32))/70);
        int x=(int) xd;
        int y=(int) yd;
        if ((currentX != x || currentY != y) && x>=0 && x<8 && y>=0 && y<8){
            board.movePiece(currentX,currentY,x,y);
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

}
