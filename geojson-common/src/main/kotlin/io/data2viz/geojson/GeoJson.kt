package io.data2viz.geojson


typealias Position = Array<Double>
typealias Positions = Array<Position>
typealias Lines = Array<Positions>
typealias Surface = Array<Lines>

val Position.lon: Double
    get() = this[0]

val Position.lat: Double
    get() = this[1]

val Position.alt: Double?
    get() = if (size > 2) this[2] else null


interface Geometry

data class FeatureCollection(val features: Array<Feature>)

data class Feature(
    val geometry: Geometry,
    val id:String? = null
) 


data class Point(val coordinates: Position): Geometry
data class MultiPoint(val coordinates: Positions): Geometry
data class LineString(val coordinates: Positions): Geometry
data class MultiLineString(val coordinates: Lines): Geometry
data class Polygon(val coordinates: Lines): Geometry {
    val hasHoles = coordinates.size > 1
}
data class MultiPolygon(val coordinates: Array<Surface>):Geometry
data class GeometryCollection(val geometries: Array<Geometry>): Geometry


/**
 * Parse a GeoJson String representing a Point
 */
expect fun String.asPoint(): Point

