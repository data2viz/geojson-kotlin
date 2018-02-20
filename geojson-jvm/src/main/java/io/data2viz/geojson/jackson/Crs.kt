package io.data2viz.geojson.jackson

import io.data2viz.geojson.jackson.jackson.CrsType

import java.io.Serializable
import java.util.HashMap



class Crs : Serializable {

    var type: CrsType? = CrsType.NAME
    var properties: Map<String, Any>? = HashMap()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Crs) return false
        val crs = o as Crs?
        return if (if (properties != null) properties != crs!!.properties else crs!!.properties != null) {
            false
        } else !if (type != null) type != crs.type else crs.type != null
    }

    override fun hashCode(): Int = 31 * (type?.hashCode() ?: 0) + (properties?.hashCode() ?: 0)

    override fun toString(): String = "Crs{type='$type', properties=$properties}"
}
