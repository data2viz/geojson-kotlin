package io.data2viz.geojson


typealias Position  = DoubleArray
typealias Positions = Array<Position>
typealias Line = Positions
typealias Lines     = Array<Line>
typealias Surface   = Array<Lines>

val Position.lon: Double
    get() = this[0]

val Position.lat: Double
    get() = this[1]

val Position.alt: Double?
    get() = if (size > 2) this[2] else null

/**
 * Marker interface to indicate a GeoJson object. It can be
 * a Geometry, a Feature or a FeatureCollection
 */
interface GeoJsonObject

interface Geometry : GeoJsonObject


data class Feature(
        val geometry: Geometry,
        val id:Any? = null,
        val properties: Any? = null
) : GeoJsonObject


data class FeatureCollection(val features: Array<Feature>): GeoJsonObject

data class Point(val coordinates: Position): Geometry

data class MultiPoint(val coordinates: Positions): Geometry

data class LineString(val coordinates: Line): Geometry

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

/**
 * Retrieve a list of Feature (FeatureCollection) which have properties.
 * You need to pass an extractionFunction to transform the extracted properties
 * to a specific Properties class.
 *
 * For instance:
 * ```
 * json.toFeaturesAndProperties { City( intProp("id"), stringProp("name") }
 * ```
 * @return a list of Pair<Feature, T>
 */
expect fun <T> String.toFeaturesAndProperties(extractFunction: FeatureProperties.() -> T): List<Pair<Feature, T>>
//expect fun <T> String.toFeature(extract: FeatureProperties.() -> T): Pair<Feature, T>

expect class FeatureProperties {
    fun stringProperty(name: String): String
    fun intProperty(name: String): Int
    fun booleanProperty(name: String): Boolean
}