package com.blogjava;

import java.util.ArrayList;
import java.util.List;

public class Tiles {
	private ChessBoard chessBoard;
	private Piece[] pieces = new Piece[ 90 ];
	private Rule rule = new Rule();

	public Tiles( ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	public int size() {
		return pieces.length;
	}

	public Tiles loadFromFen( FenRecord fenRecord ) {
		for( int i = 0; i < 90; i++ )		pieces[ i ] = null;
		while( true ) {
			Piece piece = fenRecord.fetchNextPiece();
			if( piece != null ) {
				int posInTiles = piece.getPosInTiles();
				pieces[ posInTiles ] = piece;
				if( chessBoard != null )
					chessBoard.getPositions()[ posInTiles ].setImgPiece( piece.getImage() );
			} else {
				break;
			}
		}

		return this;
	}

	public Piece getPiece( int pos ) {
		return pieces[ pos ];
	}

	public boolean movePiece( int oldPos, int newPos ) {
		boolean canMove = false;
		
		canMove = rule.isRightPath( this, oldPos, newPos );
		if( canMove ) {
			pieces[ newPos ] = pieces[ oldPos ];
			pieces[ newPos ].setPosInBoard( newPos );
			pieces[ oldPos ] = null;
			if( chessBoard != null ) {
				chessBoard.getPositions()[ newPos ].setImgPiece( pieces[ newPos ].getImage() );
				chessBoard.getPositions()[ oldPos ].setImgPiece( null );
			}
		}
		
		return canMove;
	}

	public List<Integer> getAvailableSteps( PieceSide pieceSide ) {
		List<Integer> steps = new ArrayList<>();
		
		for( Piece piece : pieces ) {
			if( piece != null && piece.getSide() == pieceSide ) {
				Step step = new Step( this, piece.getPosInTiles() );
				List<Integer> tmp = step.getAvailableSteps();
				for( Integer newPos : tmp ) {
					steps.add( piece.getPosInTiles() * 100 + newPos );
				}
			}
		}
		
		return steps;
	}

}
