package Chess;

import Chess.Pieces.Bishop;
import Chess.Pieces.Knight;
import Chess.Pieces.Queen;
import Chess.Pieces.Rook;

import javax.swing.*;
import java.awt.*;

public class NewPieceFrame extends JDialog
{
    private Coordinates cord;
    private Board board;
    private static int currentX;
    private int currentY;

    public NewPieceFrame(Board board,Coordinates cord) {
	Container pane = this.getContentPane();
	pane.setLayout(new FlowLayout(FlowLayout.CENTER));





	JButton queen=new JButton("Queen");
	queen.setAction(newQueen);
	queen.setText("Queen");
	queen.setBounds(10,10,40,40);
	pane.add(queen);

	JButton rook= new JButton("Rook");
	rook.setAction(newRook);
	rook.setText("Rook");
	rook.setBounds(60,10,40,40);
	pane.add(rook);

	JButton bishop= (new JButton("Bishop"));
	bishop.setAction(newBishop);
	bishop.setText("Bishop");
	bishop.setBounds(110,10,40,40);
	pane.add(bishop);

	JButton knight= (new JButton("Knight"));
	knight.setAction(newKnight);
	knight.setText("Knight");
	knight.setBounds(160,10,40,40);
	pane.add(knight);

	this.pack();
	this.setVisible(true);
	this.cord = cord;
	this.board = board;
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setLayout(new BorderLayout());
    }
    private Action newQueen = new AbstractAction(){


	@Override public void actionPerformed(final java.awt.event.ActionEvent e) {
	    board.set(cord,new Queen(board.getPiece(cord).isWhite()));
	    dispose();


	}
    };private Action newRook = new AbstractAction(){


	@Override public void actionPerformed(final java.awt.event.ActionEvent e) {
	    board.set(cord,new Rook(board.getPiece(cord).isWhite(),true));
	    dispose();

	}
    };private Action newBishop = new AbstractAction(){


	@Override public void actionPerformed(final java.awt.event.ActionEvent e) {
	    board.set(cord,new Bishop(board.getPiece(cord).isWhite()));
	    dispose();


	}
    };private Action newKnight = new AbstractAction(){


	@Override public void actionPerformed(final java.awt.event.ActionEvent e) {
	    board.set(cord,new Knight(board.getPiece(cord).isWhite()));
	    dispose();


	}
    };



}