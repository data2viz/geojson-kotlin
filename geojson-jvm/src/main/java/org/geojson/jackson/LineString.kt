package org.geojson.jackson

class LineString : MultiPoint {

    constructor()

    constructor(vararg points: LngLatAlt) : super(*points)

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T = geoJsonObjectVisitor.visit(this)!!

    override fun toString(): String = "LineString{} ${super.toString()}"
}
