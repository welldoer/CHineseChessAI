package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class RuleTest {
	private Rule rule;

	@Before
	public void setUp() throws Exception {
		rule = new Rule();
	}

	@Test
	public void testRule() {
		assertThat( rule ).isNotNull();
	}

}
