package com.crypticmushroom.planetbound.blocks;

import com.crypticmushroom.planetbound.world.planet.Planet;

public interface PBBlock {
	/**
	 * Should return an array of Planet instances corresponding
	 * to what planet(s) this block is found on.
	 * This method should return a new array each time it is called.
	 */
	Planet[] getPlanets();
}
