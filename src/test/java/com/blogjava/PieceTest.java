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
		assertThat( piece.getSide() ).isEqualTo( PieceSide.Red );
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

	@Test
	public void testPieceBishopMove() {
		piece = new Piece( PieceType.BishopBlack, 22 );
		assertThat( piece.canMoveTo(  2 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo(  6 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 38 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 42 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 13 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopBlack, 38 );
		assertThat( piece.canMoveTo( 54 ) ).isEqualTo( false );
		assertThat( piece.canMoveTo( 58 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopRed, 67 );
		assertThat( piece.canMoveTo( 47 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 51 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 83 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 87 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 70 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopRed, 51 );
		assertThat( piece.canMoveTo( 31 ) ).isEqualTo( false );
		assertThat( piece.canMoveTo( 35 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceAdvisorMove() {
		piece = new Piece( PieceType.AdvisorBlack, 13 );
		assertThat( piece.canMoveTo(  3 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo(  5 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 21 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 23 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 31 ) ).isEqualTo( false );
		piece = new Piece( PieceType.AdvisorRed, 76 );
		assertThat( piece.canMoveTo( 66 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 68 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 84 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 86 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 58 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceKingMove() {
		piece = new Piece( PieceType.KingBlack, 4 );
		assertThat( piece.canMoveTo(  3 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo(  5 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo(  13 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo(  12 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KingBlack, 13 );
		assertThat( piece.canMoveTo(  4 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 12 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 14 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 22 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 21 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KingBlack, 23 );
		assertThat( piece.canMoveTo( 14 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 22 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 13 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KingRed, 67 );
		assertThat( piece.canMoveTo( 66 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 68 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 76 ) ).isEqualTo( true );
		piece = new Piece( PieceType.KingRed, 85 );
		assertThat( piece.canMoveTo( 84 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 86 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 58 ) ).isEqualTo( false );
	}

	@Test
	public void testPiecePawnMove() {
		piece = new Piece( PieceType.PawnBlack, 27 );
		assertThat( piece.canMoveTo( 36 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 18 ) ).isEqualTo( false );
		assertThat( piece.canMoveTo( 28 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnBlack, 46 );
		assertThat( piece.canMoveTo( 45 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 47 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 55 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 38 ) ).isEqualTo( false );
		assertThat( piece.canMoveTo( 56 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnRed, 47 );
		assertThat( piece.canMoveTo( 38 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 46 ) ).isEqualTo( false );
		assertThat( piece.canMoveTo( 48 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnRed, 38 );
		assertThat( piece.canMoveTo( 29 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 37 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 39 ) ).isEqualTo( true );
		assertThat( piece.canMoveTo( 20 ) ).isEqualTo( false );
		assertThat( piece.canMoveTo( 56 ) ).isEqualTo( false );
	}

}
