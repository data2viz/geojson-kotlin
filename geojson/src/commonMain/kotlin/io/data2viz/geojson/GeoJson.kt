package io.data2viz.geojson


typealias Position  = Array<Double>
typealias Positions = Array<Position>
typealias Lines     = Array<Positions>
typealias Surface   = Array<Lines>

val Position.lon: Double
    get() = this[0]

val Position.lat: Double
    get() = this[1]

val Position.alt: Double?
    get() = if (size > 2) this[2] else null


interface GeoJsonObject

interface Geometry : GeoJsonObject

data class FeatureCollection(val features: Array<Feature>): GeoJsonObject

data class Feature(
    val geometry: Geometry,
    val id:String? = null
) : GeoJsonObject


data class Point(val coordinates: Position): Geometry
data class MultiPoint(val coordinates: Positions): Geometry
data class LineString(val coordinates: Positions): Geometry
data class MultiLineString(val coordinates: Lines): Geometry
data class Polygon(val coordinates: Lines): Geometry {
    val hasHoles = coordinates.size > 1
}
data class MultiPolygon(val coordinates: Surface):Geometry
data class GeometryCollection(val geometries: Array<Geometry>): Geometry


/**
 * Parse the String as a GeoJsonObject
 */
expect fun String.toGeoJsonObject():GeoJsonObject
