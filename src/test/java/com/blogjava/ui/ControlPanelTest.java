package com.blogjava.ui;

import static org.assertj.swing.fixture.Containers.showInFrame;
import static org.assertj.core.api.Assertions.*;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ControlPanelTest {
	private ControlPanel controlPanel;
	private FrameFixture window;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUp() throws Exception {
		controlPanel = GuiActionRunner.execute(() -> new ControlPanel( null ) );
		window = showInFrame( controlPanel );
		window.show();
	}

	@After
	public void tearDown() throws Exception {
		window.cleanUp();
	}

	@Test
	public void testControlPanel() {
		assertThat( controlPanel ).isNotNull();
		window.button( "btnRestart" ).requireVisible();
		assertThat( controlPanel.isRestarting() ).isEqualTo( false );
		window.button( "btnRestart" ).click();
		assertThat( controlPanel.isRestarting() ).isEqualTo( true );
	}

}
