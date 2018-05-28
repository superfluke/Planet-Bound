package com.crypticmushroom.planetbound.blocks;

public interface PBBlockTinted extends PBBlock {
	
	TintType getTintType();
	
	enum TintType {
		GRASS, FOLIAGE
	}
}
