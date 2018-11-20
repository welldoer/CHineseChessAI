package com.blogjava;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	public boolean hasDirectPath(Piece piece, int newPos) {
		boolean hasPath = false;
		int oldPos = piece.getPosInTiles();
		
		int diffPos = Math.abs( newPos - oldPos );
		int diffCol = Math.abs( newPos % 9 - oldPos % 9 );
		int diffRow = Math.abs( newPos / 9 - oldPos / 9 );
		int oldSide = oldPos / 45;
		int newSide = newPos / 45;
		int diffSide = newSide - oldSide;
		int diffCenter = Math.abs( newPos % 9 - 4 );
		int newRow = newPos / 9;

		switch( piece.getBasicType() ) {
		case Rook:		/* 车 */
		case Cannon :	/* 炮 */
			if( diffCol == 0 || diffRow == 0 )
				hasPath = true;
			break;
		case Knight:	/* 马 */
			if( ( ( diffPos %  7 == 0 || diffPos % 11 == 0 ) && diffRow == 1 ) ||
				( ( diffPos % 17 == 0 || diffPos % 19 == 0 ) && diffRow == 2 ) )
				hasPath = true;
			break;
		case Bishop:	/* 相 */
			if( ( diffPos == 16 || diffPos == 20 ) && diffSide == 0 ) {
				hasPath = true;
			}
			break;
		case Advisor:	/* 士 */
			if( ( diffPos == 8 || diffPos == 10 ) && diffCenter <= 1 &&
					( ( piece.getType() == PieceType.AdvisorBlack && newRow <= 2 ) ||
						( piece.getType() == PieceType.AdvisorRed && newRow >= 7 ) ) ) {
				hasPath = true;
			}
			break;
		case King:	/* 帅 */
			if( ( diffCol == 0 || diffRow == 0 ) && diffCenter <= 1 &&
					( ( piece.getType() == PieceType.KingBlack && newRow <= 2 ) ||
						( piece.getType() == PieceType.KingRed && newRow >= 7 ) ) ) {
				hasPath = true;
			}
			break;
		case Pawn:	/* 兵 */
			if( ( ( piece.getType() == PieceType.PawnBlack ) &&
					( ( oldSide < 1 && diffPos == 9 && newPos > oldPos ) ||
						( oldSide >= 1 && ( diffPos == 1 || diffPos == 9 ) && newPos >= oldPos - 1 ) ) ) ||
				( ( piece.getType() == PieceType.PawnRed ) &&
					( ( oldSide >= 1 && diffPos == 9 && newPos < oldPos ) ||
						( oldSide < 1 && ( diffPos == 1 || diffPos == 9 ) && newPos <= oldPos + 1 ) ) ) )
				hasPath = true;
			break;
		}
		
		return hasPath;
	}

	public boolean isRightPath(Tiles tiles, int oldPos, int newPos) {
		boolean canPass = false;
		Piece oldPiece, newPiece;

		oldPiece = tiles.getPiece( oldPos );
		newPiece = tiles.getPiece( newPos );
		
		if( newPiece == null || oldPiece.getSide() != newPiece.getSide() ) {
			canPass = hasDirectPath( oldPiece, newPos );
			if( canPass ) {
				int begin, end, inc;
				begin = oldPos;
				end = newPos;
				inc = 1;
				if( oldPos > newPos ) {
					begin = newPos;
					end = oldPos;
				}
				switch( oldPiece.getBasicType() ) {
				case Rook:
					if( end - begin >= 9 )
						inc = 9;
					for( int i = begin + inc; i < end; i += inc ) {
						if( tiles.getPiece( i ) != null )
							canPass = false;
					}
					break;
				case Cannon:
					if( end - begin >= 9 )
						inc = 9;
					int num = 0;
					for( int i = begin + inc; i < end; i += inc ) {
						if( tiles.getPiece( i ) != null ) {
							num++;
						}
					}
					if( ( num == 0 && newPiece != null ) ||
						( num == 1 && ( newPiece == null || oldPiece.getSide() == newPiece.getSide() ) ) ||
						( num > 1 ) )
						canPass = false;
					break;
				case Knight:
					int checkPos = 0;
					switch( oldPos - newPos ) {
					case 11:
					case -7:
						checkPos = oldPos - 1;
						break;
					case 7:
					case -11:
						checkPos = oldPos + 1;
						break;
					case 17:
					case 19:
						checkPos = oldPos - 9;
						break;
					case -17:
					case -19:
						checkPos = oldPos + 9;
						break;
					}
					if( tiles.getPiece( checkPos ) != null )
						canPass = false;
					break;
				case Bishop:
					if( tiles.getPiece( ( begin + end ) / 2 ) != null )
						canPass = false;
					break;
				default:
					break;
				}
			}
		}

		return canPass;
	}

	public List<Integer> getBasicSteps( Piece piece ) {
		List<Integer> ret = new ArrayList<>();
		
		switch( piece.getBasicType() ) {
		case Rook:
		case Cannon:
			for( int i = 0; i < 9; i++ ) {
				int tmp = piece.getPosInTiles() / 9 * 9 + i;
				if( tmp != piece.getPosInTiles() )
					ret.add( tmp );
			}
			for( int i = 0; i < 10; i++ ) {
				int tmp = piece.getPosInTiles() % 9 + i * 9;
				if( tmp != piece.getPosInTiles() )
					ret.add( tmp );
			}
			break;
		case Knight:
			int[] incs_knight = { -19, -17, -11, -7, +7, +11, +17, +19 };
			for( int inc : incs_knight ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		case Bishop:
			int[] incs_bishop = { -20, -16, +16, +20 };
			for( int inc : incs_bishop ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		case Advisor:
			int[] incs_advisor = { -10, -8, +8, +10 };
			for( int inc : incs_advisor ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		case King:
			int[] incs_king = { -9, -1, +1, +9 };
			for( int inc : incs_king ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		case Pawn:
			int[] incs_pawn	= { -9, -1, +1, +9 };
			for( int inc : incs_pawn ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		}
		
		return ret;
	}

}
