package com.blogjava;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ChineseChessAI extends JFrame {
	private ChessBoard chessBoard;
	
	public ChineseChessAI() {
		chessBoard = new ChessBoard();
		add( chessBoard );
		
		setResizable( false );
		setVisible( true );
		pack();
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	}

	public static void main(String[] args) {
		new ChineseChessAI();
	}

}
