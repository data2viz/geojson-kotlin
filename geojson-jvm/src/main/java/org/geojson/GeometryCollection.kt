package org.geojson

import java.util.ArrayList

class GeometryCollection : GeoJsonObject(), Iterable<GeoJsonObject> {

    private var geometries: MutableList<GeoJsonObject>? = ArrayList()

    fun getGeometries(): List<GeoJsonObject>? {
        return geometries
    }

    fun setGeometries(geometries: MutableList<GeoJsonObject>) {
        this.geometries = geometries
    }

    override fun iterator(): Iterator<GeoJsonObject> {
        return geometries!!.iterator()
    }

    fun add(geometry: GeoJsonObject): GeometryCollection {
        geometries!!.add(geometry)
        return this
    }

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

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + if (geometries != null) geometries!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "GeometryCollection{" + "geometries=" + geometries + "} " + super.toString()
    }
}
