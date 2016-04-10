import Pieces.Bishop;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ilian on 2016-04-06.
 */
public class ChessComponent extends JComponent implements BoardListener {
    private final static int BLOCK = 70;
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
                if (board.getPiece(x,y) != null){
                    board.getPiece(x,y).getIcon().paintIcon(this,g2d,x*BLOCK,y*BLOCK);
                }


            }
        }
}
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(Board.SIZE * BLOCK, Board.SIZE* BLOCK);
    }

    public void BoardChanged(){
        repaint();
    }
}