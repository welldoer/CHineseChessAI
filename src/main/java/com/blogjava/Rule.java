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
		case ROOK:		/* 车 */
		case CANNON :	/* 炮 */
			if( diffCol == 0 || diffRow == 0 )
				hasPath = true;
			break;
		case KNIGHT:	/* 马 */
			if( ( ( diffPos %  7 == 0 || diffPos % 11 == 0 ) && diffRow == 1 ) ||
				( ( diffPos % 17 == 0 || diffPos % 19 == 0 ) && diffRow == 2 ) )
				hasPath = true;
			break;
		case BISHOP:	/* 相 */
			if( ( diffPos == 16 || diffPos == 20 ) && diffSide == 0 ) {
				hasPath = true;
			}
			break;
		case ADVISOR:	/* 士 */
			if( ( diffPos == 8 || diffPos == 10 ) && diffCenter <= 1 &&
					( ( piece.getType() == PieceType.ADVISOR_BLACK && newRow <= 2 ) ||
						( piece.getType() == PieceType.ADVISOR_RED && newRow >= 7 ) ) ) {
				hasPath = true;
			}
			break;
		case KING:	/* 帅 */
			if( ( diffCol == 0 || diffRow == 0 ) && diffCenter <= 1 &&
					( ( piece.getType() == PieceType.KING_BLACK && newRow <= 2 ) ||
						( piece.getType() == PieceType.KING_RED && newRow >= 7 ) ) ) {
				hasPath = true;
			}
			break;
		case PAWN:	/* 兵 */
			if( ( ( piece.getType() == PieceType.PAWN_BLACK ) &&
					( ( oldSide < 1 && diffPos == 9 && newPos > oldPos ) ||
						( oldSide >= 1 && ( diffPos == 1 || diffPos == 9 ) && newPos >= oldPos - 1 ) ) ) ||
				( ( piece.getType() == PieceType.PAWN_RED ) &&
					( ( oldSide >= 1 && diffPos == 9 && newPos < oldPos ) ||
						( oldSide < 1 && ( diffPos == 1 || diffPos == 9 ) && newPos <= oldPos + 1 ) ) ) )
				hasPath = true;
			break;
		}
		
		return hasPath;
	}

	public boolean isRightPath(Tiles tiles, int oldPos, int newPos) {
		boolean canPass = false;
		Piece oldPiece;
		Piece newPiece;

		oldPiece = tiles.getPiece( oldPos );
		newPiece = tiles.getPiece( newPos );
		
		if( newPiece == null || oldPiece.getSide() != newPiece.getSide() ) {
			canPass = hasDirectPath( oldPiece, newPos );
			if( canPass ) {
				int begin;
				int end;
				int inc;
				begin = oldPos;
				end = newPos;
				inc = 1;
				if( oldPos > newPos ) {
					begin = newPos;
					end = oldPos;
				}
				switch( oldPiece.getBasicType() ) {
				case ROOK:
					if( end - begin >= 9 )
						inc = 9;
					for( int i = begin + inc; i < end; i += inc ) {
						if( tiles.getPiece( i ) != null )
							canPass = false;
					}
					break;
				case CANNON:
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
				case KNIGHT:
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
				case BISHOP:
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
		case ROOK:
		case CANNON:
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
		case KNIGHT:
			int[] incsKnight = { -19, -17, -11, -7, +7, +11, +17, +19 };
			for( int inc : incsKnight ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		case BISHOP:
			int[] incsBishop = { -20, -16, +16, +20 };
			for( int inc : incsBishop ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		case ADVISOR:
			int[] incsAdvisor = { -10, -8, +8, +10 };
			for( int inc : incsAdvisor ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		case KING:
			int[] incsKing = { -9, -1, +1, +9 };
			for( int inc : incsKing ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		case PAWN:
			int[] incsPawn	= { -9, -1, +1, +9 };
			for( int inc : incsPawn ) {
				int tmp = piece.getPosInTiles() + inc;
				if( tmp >= 0 && tmp <= 89 )
					ret.add( tmp );
			}
			break;
		}
		
		return ret;
	}

}
