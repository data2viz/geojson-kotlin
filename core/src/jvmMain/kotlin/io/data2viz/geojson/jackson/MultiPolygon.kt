package io.data2viz.geojson.jackson

class MultiPolygon : Geometry<List<List<LngLatAlt>>> {

    constructor()

    constructor(polygon: Polygon) {
        add(polygon)
    }

    fun add(polygon: Polygon): MultiPolygon = this.apply { coordinates.add(polygon.coordinates) }

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T = geoJsonObjectVisitor.visit(this)!!
    override fun toString(): String = "MultiPolygon{} ${super.toString()}"
}
