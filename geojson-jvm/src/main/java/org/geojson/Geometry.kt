package org.geojson


abstract class Geometry<T> : GeoJsonObject {

    val coordinates: MutableList<T> = mutableListOf()

    constructor()

    constructor(vararg elements: T) {
        elements.forEach { coordinate -> coordinates.add(coordinate) }
    }

    fun add(elements: T): Geometry<T> = apply { coordinates.add(elements) }
    
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Geometry<*>) return false
        if (!super.equals(o)) return false
        return coordinates == o.coordinates
    }

    override fun hashCode(): Int = 31 * super.hashCode() + coordinates.hashCode()

    override fun toString(): String = "Geometry{coordinates=$coordinates} ${super.toString()}"
}
