package com.blogjava;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Position extends JPanel {
	private URL urlImgPosition[];
	private Image imgPosition[];
	
	public Position( int pos ) {
		setName( "position_" + pos );
		
		urlImgPosition = new URL[ 2 ];
		urlImgPosition[ 0 ] = getClass().getClassLoader().getResource( "oo.gif" );
		urlImgPosition[ 1 ] = getClass().getClassLoader().getResource( "oos.gif" );
		imgPosition = new Image[ 2 ];
		imgPosition[ 0 ] = Toolkit.getDefaultToolkit().getImage( urlImgPosition[ 0 ] );
		imgPosition[ 1 ] = Toolkit.getDefaultToolkit().getImage( urlImgPosition[ 1 ] );
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent( g );

		g.drawImage( imgPosition[ 1 ], 0, 0, this );
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 57, 57 );
	}

}
