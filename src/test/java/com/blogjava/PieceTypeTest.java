package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class PieceTypeTest {
	private PieceType pieceType;

	@Before
	public void setUp() throws Exception {
		pieceType = PieceType.KingRed;
	}

	@Test
	public void testPieceType() {
		assertThat( pieceType.getType() ).isEqualTo( 'K' );
		assertThat( pieceType.getGifName() ).isEqualTo( "rk" );
		assertThat( pieceType.getName() ).isEqualTo( "红帥" );
		assertThat( pieceType.getBasicType() ).isEqualTo( PieceBasicType.King );
	}

}
