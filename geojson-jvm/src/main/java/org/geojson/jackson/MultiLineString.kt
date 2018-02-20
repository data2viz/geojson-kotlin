package org.geojson.jackson

class MultiLineString : Geometry<List<LngLatAlt>> {

    constructor()

    constructor(line: List<LngLatAlt>) {
        add(line)
    }

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T = geoJsonObjectVisitor.visit(this)!!

    override fun toString(): String = "MultiLineString{} ${super.toString()}"
}
