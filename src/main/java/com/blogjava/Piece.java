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
	
	public void setPosInBoard( int posInBoard ) {
		this.posInBoard = posInBoard;
	}
	
	public PieceSide getSide() {
		return pieceType.getPieceSide();
	}

	public boolean canMoveTo(int newPos) {
		boolean canMove = false;
		int diffPos = Math.abs( newPos - posInBoard );
		int diffCol = Math.abs( newPos % 9 - posInBoard % 9 );
		int diffRow = Math.abs( newPos / 9 - posInBoard / 9 );
		int diffCenter = Math.abs( newPos % 9 - 4 );

		Rule rule = new Rule();
		switch( pieceType.getBasicType() ) {
		case Cannon :	/* 炮 */
		case Rook:		/* 车 */
		case Knight:	/* 马 */
		case Bishop:	/* 相 */
		case Advisor:	/* 士 */
			canMove = rule.canMovePiece( this, newPos );
			break;
		case King:	/* 士 */
			if( ( diffCol == 0 || diffRow == 0 ) && diffCenter <= 1 &&
					( ( pieceType == PieceType.KingBlack && newPos / 9 <= 2 ) ||
						( pieceType == PieceType.KingRed && newPos / 9 >= 7 ) ) ) {
				canMove = true;
			}
			break;
		case Pawn:	/* 兵 */
			if( ( ( pieceType == PieceType.PawnBlack ) &&
					( ( posInBoard / 45 < 1 && diffPos == 9 && newPos > posInBoard ) ||
						( posInBoard / 45 >= 1 && ( diffPos == 1 || diffPos == 9 ) && newPos >= posInBoard - 1 ) ) ) ||
				( ( pieceType == PieceType.PawnRed ) &&
						( ( posInBoard / 45 >= 1 && diffPos == 9 && newPos < posInBoard ) ||
								( posInBoard / 45 < 1 && ( diffPos == 1 || diffPos == 9 ) && newPos <= posInBoard + 1 ) ) ) )
				canMove = true;
			break;
		default:
			break;
		}
		
		return canMove;
	}
}
