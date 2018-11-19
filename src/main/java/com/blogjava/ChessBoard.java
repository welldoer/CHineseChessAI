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
	private int selectedPos = -1;
	private int[] movedPos;
	private Tiles tiles;
	
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
			Position position = new Position( this, i );
			positions[ i ] = position;
			add( position );
		}
		tiles = new Tiles();
	}

	public ChessBoard loadFromFenX( FenRecord fenRecord ) {
		while( true ) {
			Piece piece = fenRecord.fetchNextPiece();
			if( piece != null ) {
				int posInBoard = piece.getPosInTiles();
				positions[ posInBoard ].setPiece( piece );
			} else
				break;
		}
		
		return this;
	}
	public ChessBoard loadFromFen( FenRecord fenRecord ) {
		tiles.loadFromFen( fenRecord );
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

	public Position[] getPositions() {
		return positions;
	}

	public int getSelectedPos() {
		return selectedPos;
	}
	
	public Tiles getTiles() {
		return tiles;
	}

	public void clickPosition(int pos) {
		if( movedPos != null ) {
			positions[ movedPos[ 0 ] ].setSelected( false );
			positions[ movedPos[ 1 ] ].setSelected( false );
			
			movedPos = null;
			selectedPos = -1;
		}
		if( selectedPos != -1 ) {
			Rule rule = new Rule();
			Piece oldPiece = tiles.getPiece( selectedPos );
			boolean boolCanMove = rule.hasDirectPath( oldPiece, pos );
			if( boolCanMove ) {
				boolCanMove = rule.isRightPath( tiles, selectedPos, pos );
			}
			if( selectedPos == pos )
				selectedPos = -1;
			else if( tiles.getPiece( pos ) == null ) {
				System.out.println( "1: Old pos: " + selectedPos + ", New pos: " + pos + "." );
				if( boolCanMove ) {
					Piece piece = positions[ selectedPos ].getPiece( );
					piece.setPosInBoard( pos );
					positions[ pos ].setPiece( piece );
					positions[ selectedPos ].setPiece( null );

					tiles.movePiece( selectedPos, pos );
					
					movedPos = new int[ 2 ];
					movedPos[ 0 ] = selectedPos;
					movedPos[ 1 ] = pos;
				} else {
					positions[ pos ].setSelected( false );
				}
			} else {
				boolean booltmp = tiles.getPiece( selectedPos ).getSide() != tiles.getPiece( pos ).getSide();
				boolean boolReplace = true;
				System.out.println( "2: SelectPos: " + selectedPos + ", pos: " + pos + ", booltmp: " + booltmp );
				if( booltmp ) {
					if( boolCanMove ) {
						boolCanMove = rule.isRightPath( tiles, selectedPos, pos );
					}
				} else {
					positions[ selectedPos ].setSelected( false );
					this.selectedPos = pos;
					positions[ pos ].setSelected( true );
				}
				if( booltmp && boolCanMove && boolReplace ) {
					Piece piece = positions[ selectedPos ].getPiece();
					piece.setPosInBoard( pos );
					positions[ pos ].setPiece( piece );
					positions[ selectedPos ].setPiece( null );

					tiles.movePiece( selectedPos, pos );
										
					movedPos = new int[ 2 ];
					movedPos[ 0 ] = selectedPos;
					movedPos[ 1 ] = pos;
				}
			}
		} else if( tiles.getPiece( pos ) != null ) {
			this.selectedPos = pos;
			positions[ pos ].setSelected( true );
		} else {
			this.selectedPos = -1;
			positions[ pos ].setSelected( false );
		}

		repaint();
	}

}
