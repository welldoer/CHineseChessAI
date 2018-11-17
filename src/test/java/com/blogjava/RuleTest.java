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
		Piece rook = new Piece( PieceType.RookBlack, 40 );
		assertThat( rule.canMovePiece( rook,  4 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 85 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 36 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 44 ) ).isTrue();
	}
	
	@Test
	public void testRuleRookIncorrectSteps() {
		Piece rook = new Piece( PieceType.RookBlack, 40 );
		assertThat( rule.canMovePiece( rook,  3 ) ).isFalse();
	}

	@Test
	public void testRuleKnightBasicSteps() {
		Piece rook = new Piece( PieceType.KnightBlack, 22 );
		assertThat( rule.canMovePiece( rook,  3 ) ).isTrue();
		assertThat( rule.canMovePiece( rook,  5 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 11 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 15 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 29 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 33 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 39 ) ).isTrue();
		assertThat( rule.canMovePiece( rook, 41 ) ).isTrue();
	}
	
	@Test
	public void testRuleKnightIncorrectSteps() {
		Piece rook = new Piece( PieceType.KnightBlack, 22 );
		assertThat( rule.canMovePiece( rook,  2 ) ).isFalse();
	}

}
