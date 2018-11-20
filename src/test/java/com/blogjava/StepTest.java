package com.blogjava;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StepTest {
	private Tiles tiles;
	private Step step;

	@Before
	public void setUp() throws Exception {
		tiles = new Tiles( null );
	}

	@Test
	public void testStepRook() {
		FenRecord fenRecord = new FenRecord( "r6PP/9/9/9/9/9/9/B8/9/9 b - - 0 0" );
		tiles.loadFromFen( fenRecord );
		step = new Step( tiles, 0 );
		Integer[] ret1 = { 1, 2, 3, 4, 5, 6, 7, 9, 18, 27, 36, 45, 54, 63 };
		List<Integer> lst = new ArrayList<>();
		for( Integer ret : ret1 )		lst.add( ret );
		assertThat( step.getAvailableSteps() ).isEqualTo( lst );

		fenRecord = new FenRecord( "4k4/9/4r4/9/9/9/9/9/9/9 b - - 0 0" );
		tiles.loadFromFen( fenRecord );
		step = new Step( tiles, 22 );
		Integer[] ret2 = { 18, 19, 20, 21, 23, 24, 25, 26, 13, 31, 40, 49, 58, 67, 76, 85 };
		lst.clear();
		for( Integer ret : ret2 )		lst.add( ret );
		assertThat( step.getAvailableSteps() ).isEqualTo( lst );
	}

	@Test
	public void testStepCannon() {
		FenRecord fenRecord = new FenRecord( "c5P1P/9/9/p8/9/9/9/B8/9/9 b - - 0 0" );
		tiles.loadFromFen( fenRecord );
		step = new Step( tiles, 0 );
		Integer[] ret1 = { 1, 2, 3, 4, 5, 8, 9, 18, 63 };
		List<Integer> lst = new ArrayList<>();
		for( Integer ret : ret1 )		lst.add( ret );
		assertThat( step.getAvailableSteps() ).isEqualTo( lst );

		fenRecord = new FenRecord( "4R4/9/4r4/4c4/9/9/9/9/9/9 b - - 0 0" );
		tiles.loadFromFen( fenRecord );
		step = new Step( tiles, 31 );
		Integer[] ret2 = { 27, 28, 29, 30, 32, 33, 34, 35, 4, 40, 49, 58, 67, 76, 85 };
		lst.clear();
		for( Integer ret : ret2 )		lst.add( ret );
		assertThat( step.getAvailableSteps() ).isEqualTo( lst );
	}

}
