package se.liu.ida.chrha376.chess;

import se.liu.ida.chrha376.chess.pieces.Bishop;
import se.liu.ida.chrha376.chess.pieces.Knight;
import se.liu.ida.chrha376.chess.pieces.Queen;
import se.liu.ida.chrha376.chess.pieces.Rook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * JDialog that pops up when a Pawn object in board has reached the end of the board and gives you options to change piece.
 */
public class NewPieceDialog extends JDialog
{
    private Coordinates cord;
    private Board board;

    public NewPieceDialog(Board board, Coordinates cord) {
	Container pane = this.getContentPane();
	pane.setLayout(new FlowLayout(FlowLayout.CENTER));





	JButton queen=new JButton("Queen");
	queen.setAction(newQueen);
	queen.setText("Queen");
	pane.add(queen);

	JButton rook= new JButton("Rook");
	rook.setAction(newRook);
	rook.setText("Rook");
	pane.add(rook);

	JButton bishop= (new JButton("Bishop"));
	bishop.setAction(newBishop);
	bishop.setText("Bishop");
	pane.add(bishop);

	JButton knight= (new JButton("Knight"));
	knight.setAction(newKnight);
	knight.setText("Knight");
	pane.add(knight);

	this.pack();
	this.setVisible(true);
	this.cord = cord;
	this.board = board;
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setLayout(new BorderLayout());
    }
    protected Action newQueen = new AbstractAction(){


	@Override public void actionPerformed(final ActionEvent e) {
	    board.set(cord,new Queen(board.getPiece(cord).isWhite()));
	    dispose();


	}
    };protected Action newRook = new AbstractAction(){


	@Override public void actionPerformed(final ActionEvent e) {
	    board.set(cord,new Rook(board.getPiece(cord).isWhite(), true));
	    dispose();

	}
    };protected Action newBishop = new AbstractAction(){


	@Override public void actionPerformed(final ActionEvent e) {
	    board.set(cord,new Bishop(board.getPiece(cord).isWhite()));
	    dispose();


	}
    };protected Action newKnight = new AbstractAction(){


	@Override public void actionPerformed(final ActionEvent e) {
	    board.set(cord,new Knight(board.getPiece(cord).isWhite()));
	    dispose();


	}
    };



}