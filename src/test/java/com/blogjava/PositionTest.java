package com.blogjava;

import static org.assertj.swing.fixture.Containers.showInFrame;
import static org.assertj.core.api.Assertions.*;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PositionTest {
	private Position position;
	private FrameFixture window;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FailOnThreadViolationRepaintManager.install();
	}

	@Before
	public void setUp() throws Exception {
		position = GuiActionRunner.execute(() -> new Position() );
		window = showInFrame( position );
		window.show();
	}

	@After
	public void tearDown() throws Exception {
		window.cleanUp();
	}

	@Test
	public void test() {
		assertThat( position ).isNotNull();
	}

}
