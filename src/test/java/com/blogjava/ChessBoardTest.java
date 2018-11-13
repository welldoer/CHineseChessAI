package com.blogjava;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.swing.fixture.Containers.showInFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
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

	@Test
	public void test() {
		assertThat( chessBoard ).isNotNull();

		String name = chessBoard.getName();
		assertThat( name ).isNotNull();
	}

}
