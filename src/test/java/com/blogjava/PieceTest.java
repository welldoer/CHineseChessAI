package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class PieceTest {
	private Piece piece;
	private Rule rule;

	@Before
	public void setUp() throws Exception {
		piece = new Piece( PieceType.KING_RED, 0 );
		rule = new Rule();
	}

	@Test
	public void testPiece() {
		assertThat( piece.getType() ).isEqualTo( PieceType.KING_RED );
		assertThat( piece.getBasicType() ).isEqualTo( PieceBasicType.King );
		assertThat( piece.getSide() ).isEqualTo( PieceSide.RED );
		assertThat( piece.getPosInTiles() ).isEqualTo( 0 );
	}

	@Test
	public void testPieceCannonMove() {
		piece = new Piece( PieceType.CannonBlack, 10 );
		assertThat( rule.hasDirectPath( piece,  1 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece,  2 ) ).isEqualTo( false );
		assertThat( rule.hasDirectPath( piece,  9 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 11 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 20 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceRookMove() {
		piece = new Piece( PieceType.CannonBlack, 78 );

		assertThat( rule.hasDirectPath( piece, 60 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 68 ) ).isEqualTo( false );
		assertThat( rule.hasDirectPath( piece, 77 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 79 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 88 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceKnightMove() {
		piece = new Piece( PieceType.KnightBlack, 20 );
		assertThat( rule.hasDirectPath( piece,  1 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece,  3 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece,  9 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 13 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 27 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 31 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 37 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 39 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 10 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceBishopMove() {
		piece = new Piece( PieceType.BishopBlack, 22 );
		assertThat( rule.hasDirectPath( piece,  2 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece,  6 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 38 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 42 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 13 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopBlack, 38 );
		assertThat( rule.hasDirectPath( piece, 54 ) ).isEqualTo( false );
		assertThat( rule.hasDirectPath( piece, 58 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopRed, 67 );
		assertThat( rule.hasDirectPath( piece, 47 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 51 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 83 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 87 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 70 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopRed, 51 );
		assertThat( rule.hasDirectPath( piece, 31 ) ).isEqualTo( false );
		assertThat( rule.hasDirectPath( piece, 35 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceAdvisorMove() {
		piece = new Piece( PieceType.AdvisorBlack, 13 );
		assertThat( rule.hasDirectPath( piece,  3 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece,  5 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 21 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 23 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 31 ) ).isEqualTo( false );
		piece = new Piece( PieceType.AdvisorRed, 76 );
		assertThat( rule.hasDirectPath( piece, 66 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 68 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 84 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 86 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 58 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceKingMove() {
		piece = new Piece( PieceType.KingBlack, 4 );
		assertThat( rule.hasDirectPath( piece,  3 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece,  5 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece,  13 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece,  12 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KingBlack, 13 );
		assertThat( rule.hasDirectPath( piece,  4 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 12 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 14 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 22 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 21 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KingBlack, 23 );
		assertThat( rule.hasDirectPath( piece, 14 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 22 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 13 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KING_RED, 67 );
		assertThat( rule.hasDirectPath( piece, 66 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 68 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 76 ) ).isEqualTo( true );
		piece = new Piece( PieceType.KING_RED, 85 );
		assertThat( rule.hasDirectPath( piece, 84 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 86 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 58 ) ).isEqualTo( false );
	}

	@Test
	public void testPiecePawnMove() {
		piece = new Piece( PieceType.PawnBlack, 27 );
		assertThat( rule.hasDirectPath( piece, 36 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 18 ) ).isEqualTo( false );
		assertThat( rule.hasDirectPath( piece, 28 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnBlack, 46 );
		assertThat( rule.hasDirectPath( piece, 45 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 47 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 55 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 38 ) ).isEqualTo( false );
		assertThat( rule.hasDirectPath( piece, 56 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnRed, 47 );
		assertThat( rule.hasDirectPath( piece, 38 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 46 ) ).isEqualTo( false );
		assertThat( rule.hasDirectPath( piece, 48 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnRed, 38 );
		assertThat( rule.hasDirectPath( piece, 29 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 37 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 39 ) ).isEqualTo( true );
		assertThat( rule.hasDirectPath( piece, 20 ) ).isEqualTo( false );
		assertThat( rule.hasDirectPath( piece, 56 ) ).isEqualTo( false );
	}

}
