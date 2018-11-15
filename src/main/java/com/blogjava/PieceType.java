package com.blogjava;

import java.util.function.IntPredicate;

public enum PieceType {
	KingRed;

	public char getType() {
		return 'K';
	}

	public String getGifName() {
		return "rk";
	}

	public String getName() {
		return "红帥";
	}

	public PieceBasicType getBasicType() {
		return PieceBasicType.King;
	}

}

enum PieceBasicType {
	King;

}