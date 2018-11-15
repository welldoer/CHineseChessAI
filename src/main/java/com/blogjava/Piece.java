package com.blogjava;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Piece {
	private PieceType pieceType;
	private URL urlImgPiece;
	private Image imgPiece;

	public Piece( PieceType pieceType ) {
		this.pieceType = pieceType;
		
		urlImgPiece = getClass().getClassLoader().getResource( pieceType.getGifName() + ".gif" );
		imgPiece = Toolkit.getDefaultToolkit().getImage( urlImgPiece );
	}

	public PieceType getType() {
		return pieceType;
	}

	public PieceBasicType getBasicType() {
		return pieceType.getBasicType();
	}

	public Image getImage() {
		return imgPiece;
	}

	public String getName() {
		return pieceType.getName();
	}
}
