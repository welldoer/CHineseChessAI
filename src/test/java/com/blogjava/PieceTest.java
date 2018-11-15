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

}
