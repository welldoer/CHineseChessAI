package com.blogjava.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.IntPredicate;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.blogjava.ChessBoard;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	private ChessBoard chessBoard;
	private JButton btnRestart;
	private boolean isRestarting = false;

	public ControlPanel( ChessBoard chessBoard ) {
		this.chessBoard = chessBoard;
		
		setLayout( new GridLayout( 5, 1 ) );
		
		JRadioButton btnRedAI = new JRadioButton( "电脑红方先行" );
		JRadioButton btnRedHB = new JRadioButton( "大哥红方先行" );
		JRadioButton btnOther = new JRadioButton( "其他情况再说" );
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add( btnRedAI );
		btnGroup.add( btnRedHB );
		btnGroup.add( btnOther );
		JPanel choicePanel = new JPanel( new GridLayout( 3, 1 ) );
		choicePanel.setBorder( BorderFactory.createTitledBorder( "对战类型" ) );
		choicePanel.add( btnRedAI );
		choicePanel.add( btnRedHB );
		choicePanel.add( btnOther );

		btnRestart = new JButton( "重新开始" );
		JButton btnUndo = new JButton( "悔棋" );
		btnRestart.setName( "btnRestart" );
		JPanel btnPanel = new JPanel();
		btnPanel.add( btnRestart );
		btnPanel.add( btnUndo );
		
		add( choicePanel );
		add( btnPanel );
		
		btnRestart.addMouseListener( new MouseMonitor() );

		setVisible( true );
	}

	public boolean isRestarting() {
		return isRestarting;
	}
	
	public void restartGame() {
		isRestarting = true;
		if( chessBoard != null )
			chessBoard.restartGame();
	}
	
	class MouseMonitor extends MouseAdapter {
		
		@Override
		public void mousePressed( MouseEvent e ) {
			if( e.getSource() == btnRestart ) {
				restartGame();
			}
		}
	}
}
