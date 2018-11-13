package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChineseChessAITest {
	private ChineseChessAI chineseChessAI;

	@Before
	public void setUp() throws Exception {
		chineseChessAI = new ChineseChessAI();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertThat( chineseChessAI ).isNotNull();
	}

}
