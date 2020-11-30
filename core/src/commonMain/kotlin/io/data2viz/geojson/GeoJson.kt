package io.data2viz.geojson


/**
 * Marker interface to indicate a GeoJson object. It can be
 * a Geometry, a Feature or a FeatureCollection
 */
interface GeoJsonObject

/**
 * A feature contains a Geometry, an optional id, and optional properties.
 */
data class Feature(
        val geometry: Geometry,
        val id:Any? = null,
        val properties: Any? = null
) : GeoJsonObject

/**
 * A feature collection is an array of features.
 */
data class FeatureCollection(val features: Array<Feature>): GeoJsonObject


interface Geometry : GeoJsonObject


data class Point            (val coordinates: Position)     : Geometry
data class MultiPoint       (val coordinates: Positions)    : Geometry
data class LineString       (val coordinates: Line)         : Geometry
data class MultiLineString  (val coordinates: Lines)        : Geometry
data class Polygon          (val coordinates: Lines)        : Geometry {
    val hasHoles = coordinates.size > 1
}
data class MultiPolygon     (val coordinates: Surface)      :Geometry
data class GeometryCollection(val geometries: Array<Geometry>): Geometry


/**
 * Position is an alias on a DoubleArray that represents the coordinates
 * in degrees for longitude (index = 0), latitude (index = 1) and altitude (meters).
 * The altitude is not a mandatory information. The position can be 2 length array or
 * a 3 length array (with altitude)
 */
typealias Position  = DoubleArray

/**
 * Type alias of an array of Positions
 */
typealias Positions = Array<Position>

/**
 * Type alias for an array of Positions
 */
typealias Line = Positions

/**
 * Type alias of an array of lines.
 */
typealias Lines     = Array<Line>


typealias Surface   = Array<Lines>


val Position.lon: Double
    get() = this[0]

val Position.lat: Double
    get() = this[1]

val Position.alt: Double?
    get() = if (size > 2) this[2] else null


/**
 * Parse the String as a GeoJsonObject. The implementation depends on
 * the platform
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

/**
 * This class simplifies the access to feature properties. It should only
 * be used as a way to deserialize the properties of a feature using
 * the String.toFeaturesAndProperties function.
 */
expect class FeatureProperties {
    fun stringProperty(name: String): String
    fun intProperty(name: String): Int
    fun booleanProperty(name: String): Boolean
}

