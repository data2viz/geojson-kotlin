package org.geojson

import org.geojson.jackson.CrsType

import java.io.Serializable
import java.util.HashMap



class Crs : Serializable {

    var type: CrsType? = CrsType.name
    var properties: Map<String, Any>? = HashMap()

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o !is Crs) {
            return false
        }
        val crs = o as Crs?
        return if (if (properties != null) properties != crs!!.properties else crs!!.properties != null) {
            false
        } else !if (type != null) type != crs.type else crs.type != null
    }

    override fun hashCode(): Int {
        var result = if (type != null) type!!.hashCode() else 0
        result = 31 * result + if (properties != null) properties!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "Crs{" + "type='" + type + '\''.toString() + ", properties=" + properties + '}'.toString()
    }
}
