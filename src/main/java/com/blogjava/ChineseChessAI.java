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
		ChineseChessAI chineseChessAI = new ChineseChessAI();
		
		FenRecord fenRecord = new FenRecord( "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR r - - 0 1" );
		chineseChessAI.loadFromFen( fenRecord );
	}

	public void loadFromFen(FenRecord fenRecord) {
		FenRecord fenRecordX = new FenRecord( "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR r - - 0 1" );
		chessBoard.loadFromFenX( fenRecordX );
		chessBoard.loadFromFen( fenRecord );
	}

}
