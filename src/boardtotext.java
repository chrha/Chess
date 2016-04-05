/**
 * Created by Ilian on 2016-04-05.
 */
public class boardtotext {
    public static String convertToText(Board board){
        StringBuilder boardtext = new StringBuilder();
        for (int x=0;x<Board.SIZE;x++){
            for (int y=0;y<Board.SIZE;y++){
                switch(board.getPiece(x,y)){
                    case EMPTY:
                        boardtext.append("_ ");
                        break;
                    case WHITE_KING :
                        boardtext.append("K");
                        break;
                    case I:
                        boardtext.append("I ");
                        break;
                    case O:
                        boardtext.append("O ");
                        break;
                    case T:
                        boardtext.append("T ");
                        break;
                    case S:
                        boardtext.append("S ");
                        break;
                    case Z:
                        boardtext.append("Z ");
                        break;
                    case J:
                        boardtext.append("J ");
                        break;
                    case L:
                        boardtext.append("L ");
                        break;
                    default:
                        boardtext.append("NULL ");
                }
            }
        }

        return boardtext;
    }

}
