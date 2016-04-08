import javax.swing.*;
import java.awt.*;

/**
 * Created by Ilian on 2016-04-05.
 */
public class ChessFrame extends JFrame {
    private Board board;
    public ChessFrame(Board board){
        super("Chess");
        this.board = board;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final ChessComponent sarea =new ChessComponent(board);
        this.setLayout(new BorderLayout());
        this.add(sarea, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }

}
