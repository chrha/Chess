/**
 * Created by Ilian on 2016-04-05.
 */
public class BoardTest {
    public static void main(String args[]){
        Board b = new Board();
        new ChessFrame(b);
        System.out.println(boardtotext.convertToText(b));
    }

}
