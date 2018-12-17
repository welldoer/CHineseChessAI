package com.blogjava;

public enum PieceType {
	KING_RED		( 'K', "rk", "红帥", PieceBasicType.KING,	PieceSide.RED	),
	ADVISOR_RED		( 'A', "ra", "红仕", PieceBasicType.ADVISOR,	PieceSide.RED	),
	BISHOP_RED		( 'B', "rb", "红相", PieceBasicType.BISHOP,	PieceSide.RED	),
	KNIGHT_RED		( 'N', "rn", "红馬", PieceBasicType.KNIGHT,	PieceSide.RED	),
	ROOK_RED		( 'R', "rr", "红車", PieceBasicType.ROOK,	PieceSide.RED	),
	CANNON_RED		( 'C', "rc", "红砲", PieceBasicType.CANNON,	PieceSide.RED	),
	PAWN_RED		( 'P', "rp", "红兵", PieceBasicType.PAWN,	PieceSide.RED	),
	KING_BLACK		( 'k', "bk", "黑将", PieceBasicType.KING,	PieceSide.BLACK	),
	ADVISOR_BLACK	( 'a', "ba", "黑士", PieceBasicType.ADVISOR,	PieceSide.BLACK	),
	BISHOP_BLACK	( 'b', "bb", "黑象", PieceBasicType.BISHOP,	PieceSide.BLACK	),
	KNIGHT_BLACK	( 'n', "bn", "黑馬", PieceBasicType.KNIGHT,	PieceSide.BLACK	),
	ROOK_BLACK		( 'r', "br", "黑車", PieceBasicType.ROOK,	PieceSide.BLACK	),
	CANNON_BLACK	( 'c', "bc", "黑炮", PieceBasicType.CANNON,	PieceSide.BLACK	),
	PAWN_BLACK		( 'p', "bp", "黑卒", PieceBasicType.PAWN,	PieceSide.BLACK	);
	
	private char type;
	private String gifName;
	private String name;
	private PieceBasicType pieceBasicType;
	private PieceSide pieceSide;

	PieceType( char type, String gifName, String name, PieceBasicType pieceBasicType, PieceSide pieceSide ) {
		this.type = type;
		this.gifName = gifName;
		this.name = name;
		this.pieceBasicType = pieceBasicType;
		this.pieceSide = pieceSide;
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

	public PieceSide getPieceSide() {
		return pieceSide;
	}
}

enum PieceBasicType {
	KING,				/* 红帥, 黑将 */
	ADVISOR,			/* 红仕, 黑士 */
	BISHOP,				/* 红相, 黑象 */
	KNIGHT,				/* 红馬, 黑馬 */
	ROOK,				/* 红車, 黑車 */
	CANNON,				/* 红砲, 黑炮 */
	PAWN,				/* 红兵, 黑卒 */
}

enum PieceSide {
	RED,				/* 红方 */
	BLACK,				/* 黑方 */
}
