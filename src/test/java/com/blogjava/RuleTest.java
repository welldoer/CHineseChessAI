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

}
