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

	public Position[] getPositions() {
		return positions;
	}

	public int getSelectedPos() {
		return selectedPos;
	}

	public void clickPosition(int pos) {
		if( movedPos != null ) {
			positions[ movedPos[ 0 ] ].setSelected( false );
			positions[ movedPos[ 1 ] ].setSelected( false );
			
			movedPos = null;
			selectedPos = -1;
		}
		if( selectedPos != -1 ) {
			if( selectedPos == pos )
				selectedPos = -1;
			else if( positions[ pos ].getPiece() == null ) {
				System.out.println( "Old pos: " + selectedPos + ", New pos: " + pos + "." );
				if( positions[ selectedPos ].getPiece().canMoveTo( pos ) ) {
					Piece piece = positions[ selectedPos ].getPiece();
					piece.setPosInBoard( pos );
					positions[ pos ].setPiece( piece );
					positions[ selectedPos ].setPiece( null );
					
					movedPos = new int[ 2 ];
					movedPos[ 0 ] = selectedPos;
					movedPos[ 1 ] = pos;
				} else {
					positions[ pos ].setSelected( false );
				}
			} else {
				boolean booltmp = positions[ selectedPos ].getPiece().getSide() != positions[ pos ].getPiece().getSide();
				boolean boolCanMove = positions[ selectedPos ].getPiece().canMoveTo( pos );
				boolean boolReplace = true;
				System.out.println( "SelectPos: " + selectedPos + ", pos: " + pos + ", booltmp: " + booltmp );
				if( booltmp ) {
					if( boolCanMove ) {
						switch( positions[ selectedPos ].getPiece().getBasicType() ) {
						case Rook:
							int begin = selectedPos;
							int end = pos;
							int inc = 1;
							if( selectedPos > pos ) {
								begin = pos;
								end = selectedPos;
							}
							if( end - begin >= 9 )
								inc = 9;
							for( int i = begin + inc; i < end; i += inc ) {
								if( positions[ i ].getPiece() != null )
									boolReplace = false;
							}
							break;
						default:
							break;
						}
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
					
					movedPos = new int[ 2 ];
					movedPos[ 0 ] = selectedPos;
					movedPos[ 1 ] = pos;
				}
			}
		} else {
			this.selectedPos = pos;
			positions[ pos ].setSelected( true );
		}

		repaint();
	}
}
