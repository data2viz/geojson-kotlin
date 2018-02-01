package org.geojson

import java.util.Arrays

import com.fasterxml.jackson.annotation.JsonIgnore

class Polygon : Geometry<List<LngLatAlt>> {

    var exteriorRing: List<LngLatAlt>
        @JsonIgnore
        get() {
            assertExteriorRing()
            return getCoordinates()[0]
        }
        set(points) = getCoordinates().add(0, points)

    val interiorRings: List<List<LngLatAlt>>
        @JsonIgnore
        get() {
            assertExteriorRing()
            return getCoordinates().subList(1, getCoordinates().size)
        }

    constructor() {}

    constructor(polygon: List<LngLatAlt>) {
        add(polygon)
    }

    constructor(vararg polygon: LngLatAlt) {
        add(Arrays.asList(*polygon))
    }

    fun getInteriorRing(index: Int): List<LngLatAlt> {
        assertExteriorRing()
        return getCoordinates()[1 + index]
    }

    fun addInteriorRing(points: List<LngLatAlt>) {
        assertExteriorRing()
        getCoordinates().add(points)
    }

    fun addInteriorRing(vararg points: LngLatAlt) {
        assertExteriorRing()
        getCoordinates().add(Arrays.asList(*points))
    }

    private fun assertExteriorRing() {
        if (getCoordinates().isEmpty())
            throw RuntimeException("No exterior ring definied")
    }

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
        return geoJsonObjectVisitor.visit(this) as T 
    }

    override fun toString(): String {
        return "Polygon{} " + super.toString()
    }
}
