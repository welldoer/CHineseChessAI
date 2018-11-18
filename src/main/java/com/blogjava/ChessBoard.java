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

	public ChessBoard loadFromFen( FenRecord fenRecord ) {
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
	public ChessBoard loadFromFe_( FenRecord fenRecord ) {
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
			Piece oldPiece = positions[ selectedPos ].getPiece();
			boolean boolCanMove = rule.hasDirectPath( oldPiece, pos );
			boolean boolCanMov_;
			if( boolCanMove ) {
				boolCanMove = canMovePieceBetweenPositions( selectedPos, pos );
				boolCanMov_ = rule.isRightPath( tiles, selectedPos, pos );
				if( boolCanMove != boolCanMov_ ) {
					System.out.println( "1: canMove: " + boolCanMove + ", canMov_: " + boolCanMov_ + "." );
				}
			}
			if( selectedPos == pos )
				selectedPos = -1;
			else if( positions[ pos ].getPiece() == null ) {
				System.out.println( "1: Old pos: " + selectedPos + ", New pos: " + pos + "." );
				if( boolCanMove ) {
					Piece piece = positions[ selectedPos ].getPiece();
					piece.setPosInBoard( pos );
					positions[ pos ].setPiece( piece );
					positions[ selectedPos ].setPiece( null );

					tiles.movePiece( selectedPos, pos );
					System.out.println( "1: Move -" + selectedPos + "- >> -" + pos + "-." );
					
					movedPos = new int[ 2 ];
					movedPos[ 0 ] = selectedPos;
					movedPos[ 1 ] = pos;
				} else {
					positions[ pos ].setSelected( false );
				}
			} else {
				boolean booltmp = positions[ selectedPos ].getPiece().getSide() != positions[ pos ].getPiece().getSide();
				boolean boolReplace = true;
				System.out.println( "2: SelectPos: " + selectedPos + ", pos: " + pos + ", booltmp: " + booltmp );
				if( booltmp ) {
					if( boolCanMove ) {
						boolCanMove = canMovePieceBetweenPositions( selectedPos, pos );
						boolCanMov_ = rule.isRightPath( tiles, selectedPos, pos );
						if( boolCanMove != boolCanMov_ ) {
							System.out.println( "2: canMove: " + boolCanMove + ", canMov_: " + boolCanMov_ + "." );
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

					tiles.movePiece( selectedPos, pos );
					System.out.println( "2: Move -" + selectedPos + "- >> -" + pos + "-." );
										
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

	private boolean canMovePieceBetweenPositions(int selectedPos, int pos) {
		boolean boolReplace = true;

		int begin, end, inc;
		begin = selectedPos;
		end = pos;
		inc = 1;
		if( selectedPos > pos ) {
			begin = pos;
			end = selectedPos;
		}
		switch( positions[ selectedPos ].getPiece().getBasicType() ) {
		case Rook:
			if( end - begin >= 9 )
				inc = 9;
			for( int i = begin + inc; i < end; i += inc ) {
				if( positions[ i ].getPiece() != null )
					boolReplace = false;
			}
			break;
		case Cannon:
			if( end - begin >= 9 )
				inc = 9;
			int num = 0;
			for( int i = begin + inc; i < end; i += inc ) {
				if( positions[ i ].getPiece() != null )
					num++;
			}
			if( num > 1 )
				boolReplace = false;
			break;
		case Bishop:
			System.out.println( "Bishop: " + ( begin + end ) / 2 + "." );
			if( positions[ ( begin + end ) / 2 ].getPiece() != null )
				boolReplace = false;
			break;
		case Knight:
			int checkPos = 0;
			switch( selectedPos - pos ) {
			case 11:
			case -7:
				checkPos = selectedPos - 1;
				break;
			case 7:
			case -11:
				checkPos = selectedPos + 1;
				break;
			case 17:
			case 19:
				checkPos = selectedPos - 9;
				break;
			case -17:
			case -19:
				checkPos = selectedPos + 9;
				break;
			}
			if( positions[ checkPos ].getPiece() != null )
				boolReplace = false;
			break;
		default:
			break;
		}
		
		return boolReplace;
	}
}
