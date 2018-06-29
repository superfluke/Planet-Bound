package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.crypticmushroom.planetbound.world.planet.Planet;
import com.crypticmushroom.planetbound.world.planet.PlanetRonne;

/**
 * A container for all planets in PlanetBound
 */
public class PBPlanets
{

    private static List<Planet> ALL_PLANETS;

    /**
     * Ronne
     */
    public static PlanetRonne RONNE;

    public static void init()
    {
        List<Planet> planets = new ArrayList<>();
        planets.add(RONNE = new PlanetRonne());

        ALL_PLANETS = Collections.unmodifiableList(planets);
    }

    public static List<Planet> allPlanets()
    {
        return ALL_PLANETS;
    }
}
