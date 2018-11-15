package com.blogjava;

public class Piece {
	private PieceType pieceType;

	public Piece( PieceType pieceType ) {
		this.pieceType = pieceType;
	}

	public PieceType getType() {
		return pieceType;
	}

	public PieceBasicType getBasicType() {
		return pieceType.getBasicType();
	}

}
