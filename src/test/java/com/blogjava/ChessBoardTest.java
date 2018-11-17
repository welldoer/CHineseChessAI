package com.blogjava;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.swing.fixture.Containers.showInFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChessBoardTest {
	private ChessBoard chessBoard;
	private FrameFixture window;

	@BeforeClass
	public static void setUpOnce() throws Exception {
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUp() throws Exception {
		chessBoard = GuiActionRunner.execute(() -> new ChessBoard() );
		window = showInFrame( chessBoard );
		window.show();
	}

	@After
	public void tearDown() throws Exception {
		window.cleanUp();
	}

	@Test
	public void test() {
		assertThat( chessBoard ).isNotNull();

		String name = chessBoard.getName();
		assertThat( name ).isEqualTo( "ChessBoard" );

		window.panel( "position_0" ).requireVisible();
	}

	@Test
	public void testLoadFromFen1() {
		FenRecord fenOrigRecord = new FenRecord( "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR r - - 0 1" );
		
		chessBoard.loadFromFen( fenOrigRecord );
		window.panel( "position_0" ).requireVisible();
	}

	@Test
	public void testMoveRook() {
		FenRecord fenSimpleRecord = new FenRecord( "1r7/2c6/9/1N7/9/9/9/9/9/9 b - - 20 50" );
		
		chessBoard.loadFromFen( fenSimpleRecord );
		window.panel( "position_0" ).requireVisible();
		
		Position[] positions = chessBoard.getPositions();
		assertThat( positions[ 1 ].getPiece().getType() ).isEqualTo( PieceType.RookBlack );
		window.panel( "position_1" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 1 );
//		window.dialog().button().click();
		window.panel( "position_10" ).click();
		assertThat( positions[ 1 ].getPiece() ).isNull();
		assertThat( positions[ 10 ].getPiece().getType() ).isEqualTo( PieceType.RookBlack );

		window.panel( "position_10" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 10 );
		assertThat( positions[ 10 ].getPiece().getType() ).isEqualTo( PieceType.RookBlack );
		assertThat( positions[ 28 ].getPiece().getType() ).isEqualTo( PieceType.KnightRed );
		window.panel( "position_28" ).click();
		assertThat( positions[ 10 ].getPiece() ).isNull();
		assertThat( positions[ 28 ].getPiece().getType() ).isEqualTo( PieceType.RookBlack );
		
		window.panel( "position_11" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 11 );
	}

	@Test
	public void testMoveCannon() {
		FenRecord fenSimpleRecord = new FenRecord( "1r7/2c6/1n7/1N7/9/9/9/2C6/9/9 b - - 20 50" );
		
		chessBoard.loadFromFen( fenSimpleRecord );
		window.panel( "position_0" ).requireVisible();
		
		Position[] positions = chessBoard.getPositions();
		assertThat( positions[ 65 ].getPiece().getType() ).isEqualTo( PieceType.CannonRed );
		window.panel( "position_65" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 65 );
		window.panel( "position_64" ).click();
		assertThat( positions[ 64 ].getPiece().getType() ).isEqualTo( PieceType.CannonRed );

		window.panel( "position_64" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 64 );
		assertThat( positions[  1 ].getPiece().getType() ).isEqualTo( PieceType.RookBlack );
		assertThat( positions[ 19 ].getPiece().getType() ).isEqualTo( PieceType.KnightBlack );
		assertThat( positions[ 28 ].getPiece().getType() ).isEqualTo( PieceType.KnightRed );
		window.panel( "position_1" ).click();
		assertThat( positions[  1 ].getPiece().getType() ).isEqualTo( PieceType.RookBlack );
		assertThat( positions[ 64 ].getPiece().getType() ).isEqualTo( PieceType.CannonRed );
		window.panel( "position_19" ).click();
		assertThat( positions[ 64 ].getPiece() ).isNull();
		assertThat( positions[ 19 ].getPiece().getType() ).isEqualTo( PieceType.CannonRed );
	}
}
