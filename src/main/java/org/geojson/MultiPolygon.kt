package org.geojson

class MultiPolygon : Geometry<List<List<LngLatAlt>>> {

    constructor()

    constructor(polygon: Polygon) {
        add(polygon)
    }

    fun add(polygon: Polygon): MultiPolygon {
        coordinates.add(polygon.coordinates)
        return this
    }

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T = geoJsonObjectVisitor.visit(this)!!
    override fun toString(): String = "MultiPolygon{} " + super.toString()
}
