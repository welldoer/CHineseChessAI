package com.blogjava;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Position extends JPanel {
	private URL urlImgPosition[];
	private Image imgPosition[];
	private boolean isSelected = false;
	
	public Position( int pos ) {
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
			String msg = "你点击了【" + "棋盘"  + "】！";
			JOptionPane.showMessageDialog( Position.this, msg );
			
			isSelected = ! isSelected;

			repaint();
		}
	}
}
