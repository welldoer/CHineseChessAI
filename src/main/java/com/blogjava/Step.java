package com.blogjava;

import java.util.ArrayList;
import java.util.List;

public class Step {
	private Tiles tiles;
	int pos;
	private Rule rule = new Rule();

	public Step(Tiles tiles, int pos ) {
		this.tiles = tiles;
		this.pos = pos;
	}

	public List<Integer> getAvailableSteps() {
		List<Integer> ret = new ArrayList<>();

		List<Integer> steps = rule.getBasicSteps( tiles.getPiece( pos ) );
		for( Integer step : steps ) {
			if( rule.isRightPath( tiles, pos, step ) )
				ret.add( step );
		}
		
		return ret;
	}

}
