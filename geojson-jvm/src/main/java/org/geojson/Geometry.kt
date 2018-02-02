package org.geojson

import java.util.ArrayList

abstract class Geometry<T> : GeoJsonObject {

    var coordinates: MutableList<T> = ArrayList()

    constructor()

    constructor(vararg elements: T) {
        for (coordinate in elements) {
            coordinates.add(coordinate)
        }
    }

    fun add(elements: T): Geometry<T> {
        coordinates.add(elements)
        return this
    }
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o !is Geometry<*>) {
            return false
        }
        if (!super.equals(o)) {
            return false
        }
        val geometry = o as Geometry<*>?
        return coordinates == geometry!!.coordinates
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + coordinates.hashCode()
        return result
    }

    override fun toString(): String {
        return "Geometry{" + "coordinates=" + coordinates + "} " + super.toString()
    }
}
