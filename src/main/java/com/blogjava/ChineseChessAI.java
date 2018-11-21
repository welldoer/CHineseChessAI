package com.blogjava;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.blogjava.ui.ControlPanel;

@SuppressWarnings("serial")
public class ChineseChessAI extends JFrame {
	private ChessBoard chessBoard;
	private ControlPanel controlPanel;
	
	public ChineseChessAI() {
		setLayout( new BorderLayout() );
		
		chessBoard = new ChessBoard();
		controlPanel = new ControlPanel( chessBoard );
		add( chessBoard, BorderLayout.CENTER );
		add( controlPanel, BorderLayout.EAST );
		
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
		chessBoard.loadFromFen( fenRecord );
	}

}
