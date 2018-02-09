package org.geojson

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id

import java.io.Serializable
import java.util.Arrays

@JsonTypeInfo(property = "type", use = Id.NAME)
@JsonSubTypes(JsonSubTypes.Type(Feature::class),JsonSubTypes.Type(Polygon::class),JsonSubTypes.Type(MultiPolygon::class),JsonSubTypes.Type(FeatureCollection::class),JsonSubTypes.Type(Point::class),JsonSubTypes.Type(MultiPoint::class),JsonSubTypes.Type(MultiLineString::class),JsonSubTypes.Type(GeometryCollection::class),JsonSubTypes.Type(LineString::class))
@JsonInclude(Include.NON_NULL)
abstract class GeoJsonObject: Serializable {

    var crs: Crs? = null
    var bbox: DoubleArray? = null

    abstract fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T

    override fun equals(o: Any?): Boolean {
        if (this === o)
            return true
        if (o == null || this::class != o::class)
            return false
        val that = o as GeoJsonObject
        return if (if (crs != null) crs != that.crs else that.crs != null) false else Arrays.equals(bbox, that.bbox)
    }

    override fun hashCode(): Int {
        var result = crs?.hashCode() ?: 0
        result = 31 * result + if (bbox != null) Arrays.hashCode(bbox) else 0
        return result
    }

    override fun toString(): String = "GeoJsonObject{}"
}
