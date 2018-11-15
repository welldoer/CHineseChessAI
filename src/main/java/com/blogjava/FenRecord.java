package com.blogjava;

public class FenRecord {
	String[] fenGroup;
	String fenSequence;
	boolean isRedGo;
	int currentPeaceStep;
	int totalStep;

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

}
