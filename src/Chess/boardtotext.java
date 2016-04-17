package Chess;

/**
 * Created by Ilian on 2016-04-05.
 */
public class boardtotext {
    public static String convertToText(Board board) {
        StringBuilder boardtext = new StringBuilder();
        for (int y = 0; y < Board.SIZE; y++) {
            for (int x = 0; x < Board.SIZE; x++) {
                String p;
                String u;
                if (board.getPiece(x,y) == null){
                    p = null;
                    u = null;
                }else{
                    p = board.getPiece(x, y).getDescription();
                    u= p.substring(0, Math.min(p.length(), 4));
                }
                if (p == null) {
                    boardtext.append("E");
                }else if (u.equals("King")) {
                    boardtext.append("K");
                }else if (u.equals("Quee")) {
                    boardtext.append("Q");
                }else if (u.equals("Bish")) {
                    boardtext.append("B");
                }else if (u.equals("Knig")) {
                    boardtext.append("H");
                }else if (u.equals("Rook")) {
                    boardtext.append("R");
                }else if (u.equals("Pawn")) {
                    boardtext.append("P");
                }

            }
            boardtext.append('\n');

        }
        return boardtext.toString();
    }
}