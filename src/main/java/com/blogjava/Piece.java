package com.blogjava;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Piece {
	private PieceType pieceType;
	private URL urlImgPiece;
	private Image imgPiece;
	private int posInBoard;

	public Piece( PieceType pieceType, int posInBoard ) {
		this.pieceType = pieceType;
		
		urlImgPiece = getClass().getClassLoader().getResource( pieceType.getGifName() + ".gif" );
		imgPiece = Toolkit.getDefaultToolkit().getImage( urlImgPiece );
		
		this.posInBoard = posInBoard;
	}

	public PieceType getType() {
		return pieceType;
	}

	public PieceBasicType getBasicType() {
		return pieceType.getBasicType();
	}

	public Image getImage() {
		return imgPiece;
	}

	public String getName() {
		return pieceType.getName();
	}
	
	public int getPosInBoard() {
		return posInBoard;
	}

	public boolean canMoveTo(int newPos) {
		boolean canMove = false;
		int diffPos = newPos - posInBoard;
		int diffCol = Math.abs( newPos % 9 - posInBoard % 9 );
		int diffRow = Math.abs( newPos / 9 - posInBoard / 9 );

		switch( pieceType.getBasicType() ) {
		case Cannon :	/* 炮 */
		case Rook:		/* 车 */
			if( ( diffCol == 0 ) || diffRow == 0 )
				canMove = true;
			break;
		case Knight:	/* 马 */
			if( ( ( diffPos %  7 == 0 || diffPos % 11 == 0 ) && diffRow == 1 ) ||
				( ( diffPos % 17 == 0 || diffPos % 19 == 0 ) && diffRow == 2 ) )
				canMove = true;
			break;

		default:
			break;
		}
		
		return canMove;
	}
}
