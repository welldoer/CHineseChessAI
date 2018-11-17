package com.blogjava;

public class Rule {

	public boolean canMovePiece(Piece piece, int newPos) {
		boolean canMove = false;
		int oldPos = piece.getPosInBoard();
		
		int diffPos = Math.abs( newPos - oldPos );
		int diffCol = Math.abs( newPos % 9 - oldPos % 9 );
		int diffRow = Math.abs( newPos / 9 - oldPos / 9 );

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
		}
		
		return canMove;
	}

}
