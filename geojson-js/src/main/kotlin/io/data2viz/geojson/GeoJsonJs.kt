package io.data2viz.geojson

typealias Position = Array<Double>
typealias Positions = Array<Position>
typealias Lines = Array<Positions>


val Position.lon: Double
    get() = this[0]

val Position.lat: Double
    get() = this[1]

val Position.alt: Double?
    get() = if (size > 2) this[2] else null

external interface Point{
    val coordinates: Position
}

data class MultiPoint(val coordinates: Positions)
data class LineString(val coordinates: Positions)
data class MultiLineString(val coordinates: Lines)
data class Polygon(val coordinates: Lines) {
    val hasHoles = coordinates.size > 1
}

external interface Typed {
    val type:String
}

external interface FeatureCollection: Typed {
    val features: Array<Feature>
}

external interface Feature: Typed {
    val geometry: Geometry
    val properties: Map<String, Any>
}


external interface Geometry : Typed {
    val coordinates: Array<*>
}

fun Typed.isPoint():Boolean = type == "Point"
fun Typed.isMultiPoint():Boolean = type == "MultiPoint"
fun Typed.isLineString():Boolean = type == "LineString"
fun Typed.isPolygon():Boolean = type == "Polygon"

fun Typed.asFeatureCollection(): FeatureCollection {
    check(type == "FeatureCollection") {"Cast impossible: type must be FeatureCollection but is $type"}
    return this as FeatureCollection
}

fun Typed.asPoint(): Point {
    check(type == "Point") { "Cast impossible, type must be Point but is $type" }
    return this as Point
}

fun Typed.asMultiPoint(): MultiPoint {
    check(type == "MultiPoint") { "Cast impossible, type must be MultiPoint but is $type" }
    return MultiPoint((this as Geometry).coordinates as Positions)
}

fun Typed.asLineString(): LineString {
    check(type == "LineString") { "Cast impossible, type must be LineString but is $type" }
    return LineString((this as Geometry).coordinates as Positions)
}

fun Typed.asMultiLineString(): Polygon {
    check(type == "MultiLineString") { "Cast impossible, type must be MultiLineString but is $type" }
    return Polygon((this as Geometry).coordinates as Lines)
}

fun Typed.asPolygon(): Polygon {
    check(type == "Polygon") { "Cast impossible, type must be Polygon but is $type" }
    return Polygon((this as Geometry).coordinates as Lines)
}

fun String.toGeoJson()  = JSON.parse<Typed>(this)



