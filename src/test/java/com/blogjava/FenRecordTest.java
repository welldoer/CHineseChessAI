package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class FenRecordTest {
	private FenRecord fenOrigRecord, fenSimpleRecord;

	@Before
	public void setUp() throws Exception {
		fenOrigRecord = new FenRecord( "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR r - - 0 1" );
		fenSimpleRecord = new FenRecord( "1r7/2c6/9/9/9/9/9/9/9/9 b - - 20 50" );
	}

	@Test
	public void testFenSimpleRecord() {
		assertThat( fenOrigRecord.isRedGo() ).isEqualTo( true );
		assertThat( fenSimpleRecord.isRedGo() ).isEqualTo( false );
		
		Piece piece = fenSimpleRecord.fetchNextPiece();
		assertThat( piece.getType() ).isEqualTo( PieceType.RookBlack );
		assertThat( piece.getPosInBoard() ).isEqualTo( 1 );
		
		piece = fenSimpleRecord.fetchNextPiece();
		assertThat( piece.getType() ).isEqualTo( PieceType.CannonBlack );
		assertThat( piece.getPosInBoard() ).isEqualTo( 11 );

		piece = fenSimpleRecord.fetchNextPiece();
		assertThat( piece ).isNull();
	}

}
