package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest {
	private ChessBoard chessBoard;

	@Before
	public void setUp() throws Exception {
		chessBoard = new ChessBoard();
	}

	@Test
	public void test() {
		assertThat( chessBoard ).isNotNull();
	}

}
