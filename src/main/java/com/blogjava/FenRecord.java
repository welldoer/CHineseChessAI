package com.blogjava;


public class FenRecord {
	private String[] fenGroup;
	private String fenSequence;
	private boolean isRedGo;
	private int currentPeaceStep;
	private int totalStep;
	private int idxFenSequence = 0;
	private int posInBoard = 0;

	public FenRecord( String strFen ) {
		fenGroup = strFen.split( " " );
		fenSequence = fenGroup[ 0 ];
		isRedGo = fenGroup[ 1 ].equals( "r" );
		currentPeaceStep = Integer.parseInt( fenGroup[ 4 ] );
		totalStep = Integer.parseInt( fenGroup[ 5 ] );
		
		currentPeaceStep = currentPeaceStep + 1;
		totalStep = totalStep + 1;
	}

	public boolean isRedGo() {
		return isRedGo;
	}

	public void restart() {
		idxFenSequence = 0;
		posInBoard = 0;
	}
	
	public Piece fetchNextPiece() {
		Piece piece = null;
		
		while( idxFenSequence < fenSequence.length() ) {
			char ch = fenSequence.charAt( idxFenSequence );
			if( Character.isDigit( ch ) ) 
				posInBoard += Character.getNumericValue( ch );
			for( PieceType pieceType : PieceType.values() ) {
				if( ch == pieceType.getType() ) {
					piece = new Piece( pieceType, posInBoard );
					posInBoard++;
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
