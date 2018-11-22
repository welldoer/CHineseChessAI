package com.blogjava;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ChessBoard extends JPanel {
	private URL urlImgBoard;
	private Image imgBoard;
	private Position[] positions;
	private int selectedPos = -1;
	private int[] movedPos;
	private FenRecord fenRecord;
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
		tiles = new Tiles( this );
	}

	public ChessBoard loadFromFen( FenRecord fenRecord ) {
		this.fenRecord = fenRecord;
		tiles.loadFromFen( fenRecord );
		return this;
	}
	
	public void restartGame( boolean isFirstAI ) {
		removeAll();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap( 0 );
		flowLayout.setVgap( 0 );
		setLayout( flowLayout );
		positions = new Position[ 90 ];
		for( int i = 0; i < 90; i++ ) {
			int cnt = isFirstAI ? ( 89 -i ) : i;
			Position position = new Position( this, cnt );
			positions[ cnt ] = position;
			add( position );
		}
		updateUI();
		repaint();
		if( fenRecord != null ) {
			fenRecord.restart();
			loadFromFen( fenRecord );
			
			repaint();
		}
		System.out.println( "isFirstAI: " + isFirstAI );
		if( isFirstAI ) {
			List<Integer> steps = tiles.getAvailableSteps( PieceSide.Red );
			int oldPos = steps.get( 0 ) / 100;
			int newPos = steps.get( 0 ) % 100;
			System.out.println( "First step: " + steps.get( 0 ) );
			tiles.movePiece( oldPos, newPos );
			
			repaint();
		}
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
			if( selectedPos == pos )							/* 再次点击取消选中		*/
				selectedPos = -1;
			else {
				boolean boolCanReplace = ( tiles.getPiece( pos ) == null ||
						tiles.getPiece( selectedPos ).getSide() != tiles.getPiece( pos ).getSide() );
				boolean moved = tiles.movePiece( selectedPos, pos );	/* 尝试移动并记录结果	*/
				if( moved ) {									/* 已移动完成				*/
					movedPos = new int[ 2 ];
					movedPos[ 0 ] = selectedPos;
					movedPos[ 1 ] = pos;
				} else if( boolCanReplace ){					/* 不可移动，且无子或对方子	*/
					positions[ pos ].setSelected( false );
				} else {										/* 不可移动，但选中新的己子	*/
					positions[ selectedPos ].setSelected( false );
					this.selectedPos = pos;
					positions[ pos ].setSelected( true );
				}
			}
		} else if( tiles.getPiece( pos ) != null ) {			/* 选中一个子				*/
			this.selectedPos = pos;
			positions[ pos ].setSelected( true );
		} else {												/* 点击空白处				*/
			this.selectedPos = -1;
			positions[ pos ].setSelected( false );
		}

		repaint();
	}

}
