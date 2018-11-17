package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class PieceTest {
	private Piece piece;
	private Rule rule;

	@Before
	public void setUp() throws Exception {
		piece = new Piece( PieceType.KingRed, 0 );
		rule = new Rule();
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
		assertThat( rule.canMovePiece( piece,  1 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece,  2 ) ).isEqualTo( false );
		assertThat( rule.canMovePiece( piece,  9 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 11 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 20 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceRookMove() {
		piece = new Piece( PieceType.CannonBlack, 78 );

		assertThat( rule.canMovePiece( piece, 60 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 68 ) ).isEqualTo( false );
		assertThat( rule.canMovePiece( piece, 77 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 79 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 88 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceKnightMove() {
		piece = new Piece( PieceType.KnightBlack, 20 );
		assertThat( rule.canMovePiece( piece,  1 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece,  3 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece,  9 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 13 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 27 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 31 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 37 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 39 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 10 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceBishopMove() {
		piece = new Piece( PieceType.BishopBlack, 22 );
		assertThat( rule.canMovePiece( piece,  2 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece,  6 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 38 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 42 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 13 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopBlack, 38 );
		assertThat( rule.canMovePiece( piece, 54 ) ).isEqualTo( false );
		assertThat( rule.canMovePiece( piece, 58 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopRed, 67 );
		assertThat( rule.canMovePiece( piece, 47 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 51 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 83 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 87 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 70 ) ).isEqualTo( false );
		piece = new Piece( PieceType.BishopRed, 51 );
		assertThat( rule.canMovePiece( piece, 31 ) ).isEqualTo( false );
		assertThat( rule.canMovePiece( piece, 35 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceAdvisorMove() {
		piece = new Piece( PieceType.AdvisorBlack, 13 );
		assertThat( rule.canMovePiece( piece,  3 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece,  5 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 21 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 23 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 31 ) ).isEqualTo( false );
		piece = new Piece( PieceType.AdvisorRed, 76 );
		assertThat( rule.canMovePiece( piece, 66 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 68 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 84 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 86 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 58 ) ).isEqualTo( false );
	}

	@Test
	public void testPieceKingMove() {
		piece = new Piece( PieceType.KingBlack, 4 );
		assertThat( rule.canMovePiece( piece,  3 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece,  5 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece,  13 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece,  12 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KingBlack, 13 );
		assertThat( rule.canMovePiece( piece,  4 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 12 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 14 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 22 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 21 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KingBlack, 23 );
		assertThat( rule.canMovePiece( piece, 14 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 22 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 13 ) ).isEqualTo( false );
		piece = new Piece( PieceType.KingRed, 67 );
		assertThat( rule.canMovePiece( piece, 66 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 68 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 76 ) ).isEqualTo( true );
		piece = new Piece( PieceType.KingRed, 85 );
		assertThat( rule.canMovePiece( piece, 84 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 86 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 58 ) ).isEqualTo( false );
	}

	@Test
	public void testPiecePawnMove() {
		piece = new Piece( PieceType.PawnBlack, 27 );
		assertThat( rule.canMovePiece( piece, 36 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 18 ) ).isEqualTo( false );
		assertThat( rule.canMovePiece( piece, 28 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnBlack, 46 );
		assertThat( rule.canMovePiece( piece, 45 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 47 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 55 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 38 ) ).isEqualTo( false );
		assertThat( rule.canMovePiece( piece, 56 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnRed, 47 );
		assertThat( rule.canMovePiece( piece, 38 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 46 ) ).isEqualTo( false );
		assertThat( rule.canMovePiece( piece, 48 ) ).isEqualTo( false );
		piece = new Piece( PieceType.PawnRed, 38 );
		assertThat( rule.canMovePiece( piece, 29 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 37 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 39 ) ).isEqualTo( true );
		assertThat( rule.canMovePiece( piece, 20 ) ).isEqualTo( false );
		assertThat( rule.canMovePiece( piece, 56 ) ).isEqualTo( false );
	}

}
