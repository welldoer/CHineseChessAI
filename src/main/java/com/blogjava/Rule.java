package com.blogjava;

public class Rule {

	public boolean canMovePiece(Piece piece, int newPos) {
		boolean canMove = false;
		int oldPos = piece.getPosInBoard();
		
		int diffPos = Math.abs( newPos - oldPos );
		int diffCol = Math.abs( newPos % 9 - oldPos % 9 );
		int diffRow = Math.abs( newPos / 9 - oldPos / 9 );
		int oldSide = oldPos / 45;
		int newSide = newPos / 45;
		int diffSide = newSide - oldSide;
		int diffCenter = Math.abs( newPos % 9 - 4 );
		int newRow = newPos / 9;

		switch( piece.getBasicType() ) {
		case Cannon :	/* 炮 */
		case Rook:		/* 车 */
			if( diffCol == 0 || diffRow == 0 )
				canMove = true;
			break;
		case Knight:	/* 马 */
			if( ( ( diffPos %  7 == 0 || diffPos % 11 == 0 ) && diffRow == 1 ) ||
				( ( diffPos % 17 == 0 || diffPos % 19 == 0 ) && diffRow == 2 ) )
				canMove = true;
			break;
		case Bishop:	/* 相 */
			if( ( diffPos == 16 || diffPos == 20 ) && diffSide == 0 ) {
				canMove = true;
			}
			break;
		case Advisor:	/* 士 */
			if( ( diffPos == 8 || diffPos == 10 ) && diffCenter <= 1 &&
					( ( piece.getType() == PieceType.AdvisorBlack && newRow <= 2 ) ||
						( piece.getType() == PieceType.AdvisorRed && newRow >= 7 ) ) ) {
				canMove = true;
			}
			break;
		}
		
		return canMove;
	}

}
