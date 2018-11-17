package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class RuleTest {
	private Rule rule;

	@Before
	public void setUp() throws Exception {
		rule = new Rule();
	}

	@Test
	public void testRuleRookBasicSteps() {
		Piece piece = new Piece( PieceType.RookBlack, 40 );
		assertThat( rule.canMovePiece( piece,  4 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 85 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 36 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 44 ) ).isTrue();
	}
	
	@Test
	public void testRuleRookIncorrectSteps() {
		Piece piece = new Piece( PieceType.RookBlack, 40 );
		assertThat( rule.canMovePiece( piece,  3 ) ).isFalse();
	}

	@Test
	public void testRuleKnightBasicSteps() {
		Piece piece = new Piece( PieceType.KnightBlack, 22 );
		assertThat( rule.canMovePiece( piece,  3 ) ).isTrue();
		assertThat( rule.canMovePiece( piece,  5 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 11 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 15 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 29 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 33 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 39 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 41 ) ).isTrue();
	}
	
	@Test
	public void testRuleKnightIncorrectSteps() {
		Piece piece = new Piece( PieceType.KnightBlack, 22 );
		assertThat( rule.canMovePiece( piece,  2 ) ).isFalse();
	}

	@Test
	public void testRuleBishopBasicSteps() {
		Piece piece = new Piece( PieceType.BishopBlack, 22 );
		assertThat( rule.canMovePiece( piece,  2 ) ).isTrue();
		assertThat( rule.canMovePiece( piece,  6 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 38 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 42 ) ).isTrue();
		
		piece = new Piece( PieceType.BishopRed, 63 );
		assertThat( rule.canMovePiece( piece, 47 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 83 ) ).isTrue();
	}
	
	@Test
	public void testRuleBishopIncorrectSteps() {
		Piece piece = new Piece( PieceType.BishopBlack, 22 );
		assertThat( rule.canMovePiece( piece, 18 ) ).isFalse();

		piece = new Piece( PieceType.BishopRed, 47 );
		assertThat( rule.canMovePiece( piece, 31 ) ).isFalse();
	}

	@Test
	public void testRuleAdvisorBasicSteps() {
		Piece piece = new Piece( PieceType.AdvisorBlack, 13 );
		assertThat( rule.canMovePiece( piece,  3 ) ).isTrue();
		assertThat( rule.canMovePiece( piece,  5 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 21 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 23 ) ).isTrue();
		
		piece = new Piece( PieceType.AdvisorRed, 76 );
		assertThat( rule.canMovePiece( piece, 66 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 68 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 84 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 86 ) ).isTrue();
	}
	
	@Test
	public void testRuleAdvisorIncorrectSteps() {
		Piece piece = new Piece( PieceType.AdvisorBlack, 13 );
		assertThat( rule.canMovePiece( piece,  4 ) ).isFalse();
		assertThat( rule.canMovePiece( piece, 12 ) ).isFalse();
		assertThat( rule.canMovePiece( piece, 14 ) ).isFalse();
		assertThat( rule.canMovePiece( piece, 22 ) ).isFalse();

		piece = new Piece( PieceType.AdvisorBlack, 3 );
		assertThat( rule.canMovePiece( piece,   4 ) ).isFalse();
		assertThat( rule.canMovePiece( piece,  11 ) ).isFalse();
		assertThat( rule.canMovePiece( piece,  12 ) ).isFalse();
		
		piece = new Piece( PieceType.AdvisorRed, 68 );
		assertThat( rule.canMovePiece( piece, 78 ) ).isFalse();
	}

	@Test
	public void testRuleKingBasicSteps() {
		Piece piece = new Piece( PieceType.KingBlack, 13 );
		assertThat( rule.canMovePiece( piece,  4 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 12 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 14 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 22 ) ).isTrue();
		
		piece = new Piece( PieceType.KingRed, 76 );
		assertThat( rule.canMovePiece( piece, 67 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 75 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 77 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 85 ) ).isTrue();
	}
	
	@Test
	public void testRuleKingIncorrectSteps() {
		Piece piece = new Piece( PieceType.KingBlack, 13 );
		assertThat( rule.canMovePiece( piece,  3 ) ).isFalse();
		assertThat( rule.canMovePiece( piece,  5 ) ).isFalse();

		piece = new Piece( PieceType.KingBlack, 3 );
		assertThat( rule.canMovePiece( piece,   2 ) ).isFalse();
		
		piece = new Piece( PieceType.KingRed, 67 );
		assertThat( rule.canMovePiece( piece, 58 ) ).isFalse();
	}

	@Test
	public void testRulePawnBasicSteps() {
		Piece piece = new Piece( PieceType.PawnBlack, 27 );
		assertThat( rule.canMovePiece( piece, 36 ) ).isTrue();
		
		piece = new Piece( PieceType.PawnBlack, 29 );
		assertThat( rule.canMovePiece( piece, 38 ) ).isTrue();

		piece = new Piece( PieceType.PawnBlack, 46 );
		assertThat( rule.canMovePiece( piece, 45 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 47 ) ).isTrue();
		assertThat( rule.canMovePiece( piece, 55 ) ).isTrue();

		piece = new Piece( PieceType.PawnRed, 58 );
		assertThat( rule.canMovePiece( piece, 49 ) ).isTrue();
	}
	
	@Test
	public void testRulePawnIncorrectSteps() {
		Piece piece = new Piece( PieceType.PawnBlack, 29 );
		assertThat( rule.canMovePiece( piece, 20 ) ).isFalse();
		assertThat( rule.canMovePiece( piece, 28 ) ).isFalse();
		assertThat( rule.canMovePiece( piece, 30 ) ).isFalse();

		piece = new Piece( PieceType.PawnRed, 49 );
		assertThat( rule.canMovePiece( piece, 48 ) ).isFalse();
	}
}
