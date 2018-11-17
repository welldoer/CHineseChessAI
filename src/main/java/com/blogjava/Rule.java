package com.blogjava;

public class Rule {

	public boolean canMovePiece(Piece piece, int newPos) {
		boolean canMove = false;
		int oldPos = piece.getPosInBoard();
		
		int diffCol = Math.abs( newPos % 9 - oldPos % 9 );
		int diffRow = Math.abs( newPos / 9 - oldPos / 9 );

		switch( piece.getBasicType() ) {
		case Cannon :	/* 炮 */
		case Rook:		/* 车 */
			if( diffCol == 0 || diffRow == 0 )
				canMove = true;
			break;
		}
		
		return canMove;
	}

}
