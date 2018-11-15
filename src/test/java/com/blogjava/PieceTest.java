package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class PieceTest {
	private Piece piece;

	@Before
	public void setUp() throws Exception {
		piece = new Piece( PieceType.KingRed, 0 );
	}

	@Test
	public void testPiece() {
		assertThat( piece.getType() ).isEqualTo( PieceType.KingRed );
		assertThat( piece.getBasicType() ).isEqualTo( PieceBasicType.King );
		assertThat( piece.getPosInBoard() ).isEqualTo( 0 );
	}

	@Test
	public void testPieceCannonMove() {
		piece = new Piece( PieceType.CannonBlack, 10 );
		assertThat( piece.canMoveTo(  1 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo(  2 ) ).isEqualTo( false );
		assertThat( piece.canMoveTo(  9 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 11 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 20 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceRookMove() {
		piece = new Piece( PieceType.CannonBlack, 78 );
		assertThat( piece.canMoveTo( 60 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 68 ) ).isEqualTo( false );
		assertThat( piece.canMoveTo( 77 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 79 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 88 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceKnightMove() {
		piece = new Piece( PieceType.KnightBlack, 20 );
		assertThat( piece.canMoveTo(  1 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo(  3 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo(  9 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 13 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 27 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 31 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 37 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 39 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 10 ) ).isEqualTo( false );
	}

}
