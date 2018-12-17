package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void testGetAvailableSteps() {
		FenRecord fenSimpleRecord = new FenRecord( "1r7/2c6/9/1N7/9/9/8P/9/9/4K4 b - - 20 50" );
		tiles.loadFromFen( fenSimpleRecord );

		List<Integer> lstSteps = new ArrayList<>();
		lstSteps.add( 2809 );	lstSteps.add( 2811 );	lstSteps.add( 2821 );	lstSteps.add( 2839 );
		lstSteps.add( 2845 );	lstSteps.add( 2847 );	lstSteps.add( 6253 );	lstSteps.add( 8576 );
		lstSteps.add( 8584 );	lstSteps.add( 8586 );
		assertThat( tiles.getAvailableSteps( PieceSide.RED ) ).isEqualTo( lstSteps );
	}
}
