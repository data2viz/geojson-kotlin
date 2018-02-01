package org.geojson

class MultiLineString : Geometry<List<LngLatAlt>> {

    constructor() {}

    constructor(line: List<LngLatAlt>) {
        add(line)
    }

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
        return geoJsonObjectVisitor.visit(this)!!
    }

    override fun toString(): String {
        return "MultiLineString{} " + super.toString()
    }
}
