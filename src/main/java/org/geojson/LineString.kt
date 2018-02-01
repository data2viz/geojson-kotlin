package org.geojson

class LineString : MultiPoint {

    constructor() {}

    constructor(vararg points: LngLatAlt) : super(*points) {}

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
        return geoJsonObjectVisitor.visit(this)
    }

    override fun toString(): String {
        return "LineString{} " + super.toString()
    }
}
