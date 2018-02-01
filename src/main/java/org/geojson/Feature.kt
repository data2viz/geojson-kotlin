package org.geojson

import com.fasterxml.jackson.annotation.JsonInclude

import java.util.HashMap

class Feature : GeoJsonObject() {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private var properties: MutableMap<String, Any>? = HashMap()
    @JsonInclude(JsonInclude.Include.ALWAYS)
    var geometry: GeoJsonObject? = null
    var id: String? = null

    fun setProperty(key: String, value: Any) {
        properties!![key] = value
    }

    fun <T> getProperty(key: String): T {
        return properties!![key] as T
    }

    fun getProperties(): Map<String, Any>? {
        return properties
    }

    fun setProperties(properties: MutableMap<String, Any>) {
        this.properties = properties
    }

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
        return geoJsonObjectVisitor.visit(this)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o)
            return true
        if (o == null || javaClass != o.javaClass)
            return false
        if (!super.equals(o))
            return false
        val feature = o as Feature?
        if (if (properties != null) properties != feature!!.properties else feature!!.properties != null)
            return false
        return if (if (geometry != null) geometry != feature.geometry else feature.geometry != null) false else !if (id != null) id != feature.id else feature.id != null
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + if (properties != null) properties!!.hashCode() else 0
        result = 31 * result + if (geometry != null) geometry!!.hashCode() else 0
        result = 31 * result + if (id != null) id!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "Feature{properties=$properties, geometry=$geometry, id='$id'}"
    }
}
