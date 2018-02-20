package org.geojson.jackson.jackson

import java.util.Arrays

import org.geojson.jackson.LngLatAlt

object MockData {

    val EXTERNAL = Arrays.asList(
        LngLatAlt(100.0, 0.0),
        LngLatAlt(101.0, 0.0),
        LngLatAlt(101.0, 1.0),
        LngLatAlt(100.0, 1.0),
        LngLatAlt(100.0, 0.0)
    )
    val INTERNAL = Arrays.asList(
        LngLatAlt(100.2, 0.2),
        LngLatAlt(100.8, 0.2),
        LngLatAlt(100.8, 0.8),
        LngLatAlt(100.2, 0.8),
        LngLatAlt(100.2, 0.2)
    )
}
