package org.geojson

import java.util.ArrayList

class GeometryCollection : GeoJsonObject(), Iterable<GeoJsonObject> {

    private var geometries: MutableList<GeoJsonObject>? = ArrayList()

    fun getGeometries(): List<GeoJsonObject>? = geometries

    fun setGeometries(geometries: MutableList<GeoJsonObject>) {
        this.geometries = geometries
    }

    override fun iterator(): Iterator<GeoJsonObject> = geometries!!.iterator()

    fun add(geometry: GeoJsonObject): GeometryCollection = this.apply { geometries!!.add(geometry)}

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
        return geoJsonObjectVisitor.visit(this)!!
    }

    override fun equals(o: Any?): Boolean {
        if (this === o)
            return true
        if (o !is GeometryCollection)
            return false
        if (!super.equals(o))
            return false
        val that = o as GeometryCollection?
        return !if (geometries != null) geometries != that!!.geometries else that!!.geometries != null
    }

    override fun hashCode(): Int = 31 * super.hashCode() + (geometries?.hashCode() ?:0)

    override fun toString(): String = "GeometryCollection{geometries=$geometries} ${super.toString()}"
}
