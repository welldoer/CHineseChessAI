package com.blogjava;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ChessBoard extends JPanel {
	private URL urlImgBoard;
	private Image imgBoard;
	private Position[] positions;
	
	public ChessBoard() {
		setName( "ChessBoard" );
		
		urlImgBoard = getClass().getClassLoader().getResource( "board.jpg" );
		imgBoard = Toolkit.getDefaultToolkit().getImage( urlImgBoard );
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap( 0 );
		flowLayout.setVgap( 0 );
		setLayout( flowLayout );
		positions = new Position[ 90 ];
		for( int i = 0; i < 90; i++ ) {
			Position position = new Position( i );
			positions[ i ] = position;
			add( position );
		}
		
		positions[ 1 ].setPiece( new Piece( PieceType.KnightBlack, 1 ) );
	}

	public ChessBoard loadFromFen( FenRecord fenRecord ) {
		while( true ) {
			Piece piece = fenRecord.fetchNextPiece();
			if( piece != null ) {
				int posInBoard = piece.getPosInBoard();
				positions[ posInBoard ].setPiece( piece );
			} else
				break;
		}
		
		return this;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent( g );
		g.drawImage( imgBoard, 0, 0, this );
	}
	
	public Dimension getPreferredSize() {
		return new Dimension( 521, 577 );
	}
}
