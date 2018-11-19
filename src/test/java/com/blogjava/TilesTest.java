package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TilesTest {
	private Tiles tiles;

	@Before
	public void setUp() throws Exception {
		tiles = new Tiles( null );
	}

	@Test
	public void testTiles() {
		assertThat( tiles.size() ).isEqualTo( 90 );
	}

	@Test
	public void testMoveRook() {
		FenRecord fenSimpleRecord = new FenRecord( "1r7/2c6/9/1N7/9/9/9/9/9/9 b - - 20 50" );
		
		tiles.loadFromFen( fenSimpleRecord );

		assertThat( tiles.getPiece(  1 ).getType() ).isEqualTo( PieceType.RookBlack );
		tiles.movePiece( 1, 10 );
		assertThat( tiles.getPiece(  1 ) ).isNull();
		assertThat( tiles.getPiece( 10 ).getType() ).isEqualTo( PieceType.RookBlack );
		tiles.movePiece( 10, 28 );
		assertThat( tiles.getPiece( 10 ) ).isNull();
		assertThat( tiles.getPiece( 28 ).getType() ).isEqualTo( PieceType.RookBlack );
		assertThat( tiles.getPiece( 28 ).getPosInTiles() ).isEqualTo( 28 );
	}
}
