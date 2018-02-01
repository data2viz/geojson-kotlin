package org.geojson

open class MultiPoint : Geometry<LngLatAlt> {

    constructor() 

    constructor(vararg points: LngLatAlt) : super(*points)

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
        return geoJsonObjectVisitor.visit(this)
    }

    override fun toString(): String {
        return "MultiPoint{} " + super.toString()
    }
}
