package io.data2viz.geojson


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
fun Typed.isMultiPolygon():Boolean = type == "MultiPolygon"

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

fun Typed.asMultiPolygon(): MultiPolygon {
    check(type == "MultiPolygon") { "Cast impossible, type must be MultiPolygon but is $type" }
    return MultiPolygon((this as Geometry).coordinates as Array<Surface>)
}

val FeatureCollection.multipolygons : List<MultiPolygon>
    get() = features
        .filter { it.geometry.isMultiPolygon() }
        .map { it.geometry.asMultiPolygon()}
    



fun String.toGeoJson()  = JSON.parse<Typed>(this)



