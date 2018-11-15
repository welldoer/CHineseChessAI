package com.blogjava;

public enum PieceType {
	KingRed			( 'K', "rk", "红帥", PieceBasicType.King ),
	AdvisorRed		( 'A', "ra", "红仕", PieceBasicType.Advisor ),
	BishopRed		( 'B', "rb", "红相", PieceBasicType.Bishop ),
	KnightRed		( 'N', "rn", "红馬", PieceBasicType.Knight ),
	RookRed			( 'R', "rr", "红車", PieceBasicType.Rook ),
	CannonRed		( 'C', "rc", "红砲", PieceBasicType.Cannon ),
	PawnRed			( 'P', "rp", "红兵", PieceBasicType.Pawn ),
	KingBlack		( 'k', "bk", "黑将", PieceBasicType.King ),
	AdvisorBlack	( 'a', "ba", "黑士", PieceBasicType.Advisor ),
	BishopBlack		( 'b', "bb", "黑象", PieceBasicType.Bishop ),
	KnightBlack		( 'n', "bn", "黑馬", PieceBasicType.Knight ),
	RookBlack		( 'r', "br", "黑車", PieceBasicType.Rook ),
	CannonBlack		( 'c', "bc", "黑炮", PieceBasicType.Cannon ),
	PawnBlack		( 'p', "bp", "黑卒", PieceBasicType.Pawn );
	
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
	King,				/* 红帥, 黑将 */
	Advisor,			/* 红仕, 黑士 */
	Bishop,				/* 红相, 黑象 */
	Knight,				/* 红馬, 黑馬 */
	Rook,				/* 红車, 黑車 */
	Cannon,				/* 红砲, 黑炮 */
	Pawn;				/* 红兵, 黑卒 */
}