package com.blogjava;


public class FenRecord {
	private String[] fenGroup;
	private String fenSequence;
	private boolean isRedGo;
	private int currentPeaceStep;
	private int totalStep;
	private int idxFenSequence = 0;
	private int posChessBoard = 0;

	public FenRecord( String strFen ) {
		String[] fenGroup = strFen.split( " " );
		fenSequence = fenGroup[ 0 ];
		isRedGo = fenGroup[ 1 ].equals( "r" ) ? true : false;
		currentPeaceStep = Integer.parseInt( fenGroup[ 4 ] );
		totalStep = Integer.parseInt( fenGroup[ 5 ] );
	}

	public boolean isRedGo() {
		return isRedGo;
	}

	public Piece fetchNextPiece() {
		Piece piece = null;
		
		while( idxFenSequence < fenSequence.length() ) {
			char ch = fenSequence.charAt( idxFenSequence );
			if( Character.isDigit( ch ) ) 
				posChessBoard += Character.getNumericValue( ch );
			else if( ch == '/' ) {
				if( posChessBoard % 9 != 0 )
					System.out.println( "Warning: Fen format error - " + posChessBoard + "  " + fenSequence );
			}
			for( PieceType pieceType : PieceType.values() ) {
				if( ch == pieceType.getType() ) {
					piece = new Piece( pieceType );
					posChessBoard++;
					break;
				}
			}
			idxFenSequence++;
			if( piece != null)
				break;
		}

		return piece;
	}

}
