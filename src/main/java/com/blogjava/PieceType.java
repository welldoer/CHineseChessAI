package com.blogjava;

public enum PieceType {
	KingRed			( 'K', "rk", "红帥", PieceBasicType.King,	PieceSide.RED	),
	AdvisorRed		( 'A', "ra", "红仕", PieceBasicType.Advisor,	PieceSide.RED	),
	BishopRed		( 'B', "rb", "红相", PieceBasicType.Bishop,	PieceSide.RED	),
	KnightRed		( 'N', "rn", "红馬", PieceBasicType.Knight,	PieceSide.RED	),
	RookRed			( 'R', "rr", "红車", PieceBasicType.Rook,	PieceSide.RED	),
	CannonRed		( 'C', "rc", "红砲", PieceBasicType.Cannon,	PieceSide.RED	),
	PawnRed			( 'P', "rp", "红兵", PieceBasicType.Pawn,	PieceSide.RED	),
	KingBlack		( 'k', "bk", "黑将", PieceBasicType.King,	PieceSide.Black	),
	AdvisorBlack	( 'a', "ba", "黑士", PieceBasicType.Advisor,	PieceSide.Black	),
	BishopBlack		( 'b', "bb", "黑象", PieceBasicType.Bishop,	PieceSide.Black	),
	KnightBlack		( 'n', "bn", "黑馬", PieceBasicType.Knight,	PieceSide.Black	),
	RookBlack		( 'r', "br", "黑車", PieceBasicType.Rook,	PieceSide.Black	),
	CannonBlack		( 'c', "bc", "黑炮", PieceBasicType.Cannon,	PieceSide.Black	),
	PawnBlack		( 'p', "bp", "黑卒", PieceBasicType.Pawn,	PieceSide.Black	);
	
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
	King,				/* 红帥, 黑将 */
	Advisor,			/* 红仕, 黑士 */
	Bishop,				/* 红相, 黑象 */
	Knight,				/* 红馬, 黑馬 */
	Rook,				/* 红車, 黑車 */
	Cannon,				/* 红砲, 黑炮 */
	Pawn,				/* 红兵, 黑卒 */
}

enum PieceSide {
	RED,				/* 红方 */
	Black,				/* 黑方 */
}
