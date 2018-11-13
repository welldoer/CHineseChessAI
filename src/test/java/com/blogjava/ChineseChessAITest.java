package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChineseChessAITest {
	private ChineseChessAI chineseChessAI;
	private FrameFixture window;

	@BeforeClass
	public static void setUpOnce() throws Exception {
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUp() throws Exception {
		chineseChessAI = GuiActionRunner.execute( () -> new ChineseChessAI() );
		window = new FrameFixture( chineseChessAI );
		window.show();
	}

	@After
	public void tearDown() throws Exception {
		window.cleanUp();
	}

	@Test
	public void test() {
		assertThat( chineseChessAI ).isNotNull();
		window.panel( "ChessBoard" ).requireVisible();
	}

}
