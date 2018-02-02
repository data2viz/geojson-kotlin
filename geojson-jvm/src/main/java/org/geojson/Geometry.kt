package org.geojson


abstract class Geometry<T> : GeoJsonObject {

    var coordinates: MutableList<T> = mutableListOf()

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

    override fun hashCode(): Int = 31 * super.hashCode() + coordinates.hashCode()

    override fun toString(): String = "Geometry{coordinates=$coordinates} ${super.toString()}"
}
