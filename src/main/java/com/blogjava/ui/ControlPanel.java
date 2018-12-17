package com.blogjava.ui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.blogjava.ChessBoard;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	private ChessBoard chessBoard;
	private JRadioButton btnRedAI;
	private JRadioButton btnRedHB;
	private JButton btnRestart;
	private boolean isRestarting = false;

	public ControlPanel( ChessBoard chessBoard ) {
		this.chessBoard = chessBoard;
		
		setLayout( new GridLayout( 5, 1 ) );
		
		btnRedAI = new JRadioButton( "电脑红方先行" );
		btnRedHB = new JRadioButton( "大哥红方先行" );
		JRadioButton btnOther = new JRadioButton( "其他情况再说" );
		ButtonGroup btnGroup = new ButtonGroup();
		btnRedAI.setSelected( true );
		btnGroup.add( btnRedAI );
		btnGroup.add( btnRedHB );
		btnGroup.add( btnOther );
		JPanel choicePanel = new JPanel( new GridLayout( 3, 1 ) );
		choicePanel.setBorder( BorderFactory.createTitledBorder( "对战类型" ) );
		choicePanel.add( btnRedAI );
		choicePanel.add( btnRedHB );
		choicePanel.add( btnOther );
		btnRedAI.setName( "btnRedAI" );
		btnRedHB.setName( "btnRedHB" );
		btnOther.setName( "btnOther" );

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
	
	private boolean isRedAI() {
		return btnRedAI.isSelected();
	}
	
	public void restartGame( boolean isFirstAI ) {
		isRestarting = true;
		if( chessBoard != null )
			chessBoard.restartGame( isFirstAI );
	}
	
	class MouseMonitor extends MouseAdapter {
		
		@Override
		public void mousePressed( MouseEvent e ) {
			if( e.getSource() == btnRestart ) {
				restartGame( isRedAI() );
			}
		}
	}
}
