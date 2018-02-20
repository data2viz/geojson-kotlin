package org.geojson.jackson

open class MultiPoint : Geometry<LngLatAlt> {

    constructor() 

    constructor(vararg points: LngLatAlt) : super(*points)

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T = geoJsonObjectVisitor.visit(this)!!

    override fun toString(): String = "MultiPoint{} ${super.toString()}"
}
