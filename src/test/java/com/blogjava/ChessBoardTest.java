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
		
		Tiles tiles = chessBoard.getTiles();
		assertThat( tiles.getPiece( 1 ).getType() ).isEqualTo( PieceType.ROOK_BLACK );
		window.panel( "position_1" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 1 );
		window.panel( "position_10" ).click();
		assertThat( tiles.getPiece( 1 ) ).isNull();
		assertThat( tiles.getPiece( 10 ).getType() ).isEqualTo( PieceType.ROOK_BLACK );

		window.panel( "position_10" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 10 );
		assertThat( tiles.getPiece( 10 ).getType() ).isEqualTo( PieceType.ROOK_BLACK );
		assertThat( tiles.getPiece( 28 ).getType() ).isEqualTo( PieceType.KNIGHT_RED );
		window.panel( "position_28" ).click();
		assertThat( tiles.getPiece( 10 ) ).isNull();;
		assertThat( tiles.getPiece( 28 ).getType() ).isEqualTo( PieceType.ROOK_BLACK );
		
		window.panel( "position_11" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 11 );
	}

	@Test
	public void testMoveCannon() {
		FenRecord fenSimpleRecord = new FenRecord( "1r7/2c6/1n7/1N7/9/9/9/2C6/9/9 b - - 20 50" );
		
		chessBoard.loadFromFen( fenSimpleRecord );
		window.panel( "position_0" ).requireVisible();
		
		Tiles tiles = chessBoard.getTiles();
		assertThat( tiles.getPiece( 65 ).getType() ).isEqualTo( PieceType.CANNON_RED );
		window.panel( "position_65" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 65 );
		window.panel( "position_64" ).click();
		assertThat( tiles.getPiece( 64 ).getType() ).isEqualTo( PieceType.CANNON_RED );

		window.panel( "position_64" ).click();
		assertThat( chessBoard.getSelectedPos() ).isEqualTo( 64 );
		assertThat( tiles.getPiece(  1 ).getType() ).isEqualTo( PieceType.ROOK_BLACK );
		assertThat( tiles.getPiece( 19 ).getType() ).isEqualTo( PieceType.KNIGHT_BLACK );
		assertThat( tiles.getPiece( 28 ).getType() ).isEqualTo( PieceType.KNIGHT_RED );
		window.panel( "position_1" ).click();
		assertThat( tiles.getPiece(  1 ).getType() ).isEqualTo( PieceType.ROOK_BLACK );
		assertThat( tiles.getPiece( 64 ).getType() ).isEqualTo( PieceType.CANNON_RED );
		window.panel( "position_19" ).click();
		assertThat( tiles.getPiece( 64 ) ).isNull();;
		assertThat( tiles.getPiece( 19 ).getType() ).isEqualTo( PieceType.CANNON_RED );
	}

	@Test
	public void testMoveBishop() {
		FenRecord fenSimpleRecord = new FenRecord( "1rb6/2c6/1n7/1N7/9/9/9/2C6/9/9 b - - 20 50" );
		
		chessBoard.loadFromFen( fenSimpleRecord );
		window.panel( "position_2" ).requireVisible();

		Tiles tiles = chessBoard.getTiles();
		window.panel( "position_2" ).click();
		window.panel( "position_22" ).click();
		assertThat( tiles.getPiece(  2 ) ).isNull();;
		assertThat( tiles.getPiece( 22 ).getType() ).isEqualTo( PieceType.BISHOP_BLACK );
		
		window.panel( "position_22" ).click();
		window.panel( "position_38" ).click();

		window.panel( "position_38" ).click();
		window.panel( "position_18" ).click();
		assertThat( tiles.getPiece( 28 ).getType() ).isEqualTo( PieceType.KNIGHT_RED );
		assertThat( tiles.getPiece( 18 ) ).isNull();;
		assertThat( tiles.getPiece( 38 ).getType() ).isEqualTo( PieceType.BISHOP_BLACK );
	}

	@Test
	public void testMoveKnight() {
		FenRecord fenSimpleRecord = new FenRecord( "1rb6/2c6/1n7/1N7/9/9/9/2C6/9/9 b - - 20 50" );
		
		chessBoard.loadFromFen( fenSimpleRecord );
		Tiles tiles = chessBoard.getTiles();

		window.panel( "position_19" ).click();
		assertThat( tiles.getPiece( 19 ).getType() ).isEqualTo( PieceType.KNIGHT_BLACK );
		window.panel( "position_38" ).click();
		assertThat( tiles.getPiece( 19 ).getType() ).isEqualTo( PieceType.KNIGHT_BLACK );
		assertThat( tiles.getPiece( 38 ) ).isNull();;
	}

	@Test
	public void testMovePawn() {
		FenRecord fenSimpleRecord = new FenRecord( "1rb6/2c6/1n7/1Np6/2R6/1CN6/9/2C6/9/9 b - - 20 50" );
		
		chessBoard.loadFromFen( fenSimpleRecord );
		Tiles tiles = chessBoard.getTiles();

		window.panel( "position_29" ).click();
		assertThat( tiles.getPiece( 29 ).getType() ).isEqualTo( PieceType.PAWN_BLACK );
		window.panel( "position_38" ).click();

		window.panel( "position_38" ).click();
		window.panel( "position_47" ).click();
		
		window.panel( "position_47" ).click();
		window.panel( "position_46" ).click();
		assertThat( tiles.getPiece( 46 ).getType() ).isEqualTo( PieceType.PAWN_BLACK );
		assertThat( tiles.getPiece( 47 ) ).isNull();;
	}

	@Test
	public void testMoveKingAndAdvisor() {
		FenRecord fenSimpleRecord = new FenRecord( "1rb2a3/2c1Pk3/1n2cN3/1Np6/2R6/1CN6/9/2C6/9/9 b - - 20 50" );
		
		chessBoard.loadFromFen( fenSimpleRecord );
		Tiles tiles = chessBoard.getTiles();

		window.panel( "position_5" ).click();
		window.panel( "position_13" ).click();
		assertThat( tiles.getPiece( 13 ).getType() ).isEqualTo( PieceType.ADVISOR_BLACK );

		window.panel( "position_14" ).click();
		window.panel( "position_23" ).click();
		assertThat( tiles.getPiece( 23 ).getType() ).isEqualTo( PieceType.KING_BLACK );

		window.panel( "position_23" ).click();
		window.panel( "position_22" ).click();
		assertThat( tiles.getPiece( 23 ).getType() ).isEqualTo( PieceType.KING_BLACK );
	}
}
