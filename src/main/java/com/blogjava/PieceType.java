package com.blogjava;

public enum PieceType {
	KingRed			( 'K', "rk", "红帥", PieceBasicType.King );
	
	private char type;
	private String gifName;
	private String name;
	private PieceBasicType pieceBasicType;

	PieceType( char type, String gifName, String name, PieceBasicType pieceBasicType ) {
		this.type = type;
		this.gifName = gifName;
		this.name = name;
		this.pieceBasicType = pieceBasicType;
	}
	
	public char getType() {
		return type;
	}

	public String getGifName() {
		return gifName;
	}

	public String getName() {
		return name;
	}

	public PieceBasicType getBasicType() {
		return pieceBasicType;
	}

}

enum PieceBasicType {
	King;
}