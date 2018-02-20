package org.geojson.jackson


import com.fasterxml.jackson.annotation.JsonIgnore

class Polygon : Geometry<List<LngLatAlt>> {

    var exteriorRing: List<LngLatAlt>
        @JsonIgnore
        get() {
            assertExteriorRing()
            return coordinates[0]
        }
        set(points) = coordinates.add(0, points)

    val interiorRings: List<List<LngLatAlt>>
        @JsonIgnore
        get() {
            assertExteriorRing()
            return coordinates.subList(1, coordinates.size)
        }

    constructor() 

    constructor(polygon: List<LngLatAlt>) {
        add(polygon)
    }

    constructor(vararg polygon: LngLatAlt) {
        add(polygon.asList())
    }

    fun getInteriorRing(index: Int): List<LngLatAlt> {
        assertExteriorRing()
        return coordinates[1 + index]
    }

    fun addInteriorRing(points: List<LngLatAlt>) {
        assertExteriorRing()
        coordinates.add(points)
    }

    fun addInteriorRing(vararg points: LngLatAlt) {
        assertExteriorRing()
        coordinates.add(points.asList())
    }

    private fun assertExteriorRing() = check(coordinates.isNotEmpty()) {"No exterior ring defined"}

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
        return geoJsonObjectVisitor.visit(this) as T 
    }

    override fun toString(): String = "Polygon{} ${super.toString()}"
}
