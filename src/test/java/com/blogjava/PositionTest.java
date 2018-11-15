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
	private Piece piece;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FailOnThreadViolationRepaintManager.install();
	}

	@Before
	public void setUp() throws Exception {
		position = GuiActionRunner.execute(() -> new Position( 0 ) );
		window = showInFrame( position );
		window.show();
	}

	@After
	public void tearDown() throws Exception {
		window.cleanUp();
	}

	@Test
	public void testPosition() {
		assertThat( position ).isNotNull();
		window.panel( "position_0" ).requireVisible();

		assertThat( position.isSelected() ).isEqualTo( false );
		window.panel( "position_0" ).click();
		window.dialog().button().click();
		assertThat( position.isSelected() ).isEqualTo( true );
	}

	@Test
	public void testOnePiece() {
		piece = new Piece( PieceType.AdvisorBlack );
		position.setPiece( piece );
		position.setOpaque( true );

		assertThat( position.getPiece().getType() ).isEqualTo( PieceType.AdvisorBlack );
		
		window.panel().click();
		window.dialog().label( "OptionPane.label" ).requireText( "你点击了【黑士】！" );
		window.dialog().button().click();
	}
}
