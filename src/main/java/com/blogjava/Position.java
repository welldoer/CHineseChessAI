package com.blogjava;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Position extends JPanel {
	private ChessBoard chessBoard = null;
	private int pos;
	private URL urlImgPosition[];
	private Image imgPosition[];
	private boolean isSelected = false;
	private Image imgPiece;
	private Piece piece = null;
	
	public Position( ChessBoard chessBoard, int pos ) {
		this.chessBoard = chessBoard;
		this.pos = pos;
		buildPosition( pos );
	}

	public Position( int pos ) {
		buildPosition(pos);
	}

	private void buildPosition(int pos) {
		setName( "position_" + pos );
		
		urlImgPosition = new URL[ 2 ];
		urlImgPosition[ 0 ] = getClass().getClassLoader().getResource( "oo.gif" );
		urlImgPosition[ 1 ] = getClass().getClassLoader().getResource( "oos.gif" );
		imgPosition = new Image[ 2 ];
		imgPosition[ 0 ] = Toolkit.getDefaultToolkit().getImage( urlImgPosition[ 0 ] );
		imgPosition[ 1 ] = Toolkit.getDefaultToolkit().getImage( urlImgPosition[ 1 ] );
		
		setOpaque( false );
		
		addMouseListener( new MouseMonitor() );
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent( g );

		g.drawImage( imgPosition[ isSelected ? 1 : 0 ], 0, 0, this );
		
		if( piece != null ) {
			g.drawImage( piece.getImage(), 0, 0, this);
		}
		
		if( imgPiece != null )
			g.drawImage( imgPiece, 0, 0, this );
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 57, 57 );
	}

	public boolean isSelected() {
		return isSelected;
	}

	class MouseMonitor extends MouseAdapter {

		public void mousePressed( MouseEvent e ) {
			isSelected = ! isSelected;
			
			if( chessBoard != null ) {
				chessBoard.clickPosition( Position.this.pos );
			}

			repaint();
		}
	}
	
	public void setSelected( boolean state ) {
		isSelected = state;
	}

	public void setImgPiece( Image imgPiece ) {
		this.imgPiece = imgPiece;
	}

	public Image getImgPiece() {
		return imgPiece;
	}
}
