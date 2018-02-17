package io.data2viz.geojson.js

import io.data2viz.geojson.*

external interface Typed {
    val type:String
}

fun Typed.asFeature(): Feature {
    check(type == "Feature") {"Cast impossible: type must be Feature but is $type"}
    val typed:Typed = asDynamic().geometry
    return Feature(typed.asGeometry())
}

fun Typed.asFeatureCollection(): FeatureCollection {
    check(type == "FeatureCollection") {"Cast impossible: type must be FeatureCollection but is $type"}
    val dyn:dynamic = this
    val featureJs:dynamic = dyn.features
    val features:dynamic = Array<Geometry>(0, {Point(arrayOf())})
    val size:Int = featureJs.length
    for (i in 0 until size) {
        val feature = featureJs[i]
        val typed:Typed = feature.geometry
        features[i] = Feature(typed.asGeometry())
    }
    return FeatureCollection(features)
}



fun Typed.asGeometry():Geometry =
    when (type) {
        "Point"                 -> asPoint()
        "MultiPoint"            -> asMultiPoint()
        "LineString"            -> asLineString()
        "MultiLineString"       -> asMultiLineString()
        "Polygon"               -> asPolygon()
        "MultiPolygon"          -> asMultiPolygon()
        "GeometryCollection"    -> asGeometryCollection()
        else                    -> throw IllegalStateException("${type} is not known")
    }

fun Typed.asPoint(): Point {
    check(type == "Point") { "Cast impossible, type must be Point but is $type" }
    return Point(asDynamic().coordinates)
}

fun Typed.asMultiPoint(): MultiPoint {
    check(type == "MultiPoint") { "Cast impossible, type must be MultiPoint but is $type" }
    return MultiPoint(asDynamic().coordinates)
}

fun Typed.asLineString(): LineString {
    check(type == "LineString") { "Cast impossible, type must be LineString but is $type" }
    return LineString(asDynamic().coordinates)
}

fun Typed.asMultiLineString(): Polygon {
    check(type == "MultiLineString") { "Cast impossible, type must be MultiLineString but is $type" }
    return Polygon(asDynamic().coordinates)
}

fun Typed.asPolygon(): Polygon {
    check(type == "Polygon") { "Cast impossible, type must be Polygon but is $type" }
    return Polygon(asDynamic().coordinates)
}

fun Typed.asGeometryCollection(): GeometryCollection {
    check(type == "GeometryCollection") { "Cast impossible, type must be GeometryCollection but is $type" }
    val types:Array<Typed> = asDynamic().geometries
    val geometries:Array<Geometry> = types.map{it.asGeometry()}.toTypedArray()
    return GeometryCollection(geometries)
}

fun Typed.asMultiPolygon(): MultiPolygon {
    check(type == "MultiPolygon") { "Cast impossible, type must be MultiPolygon but is $type" }
    return MultiPolygon(asDynamic().coordinates)
}

val FeatureCollection.multipolygons : List<MultiPolygon>
    get() = features
        .filter { it.geometry is MultiPolygon }
        .map { it.geometry as MultiPolygon}

fun String.toGeoJson()  = JSON.parse<Typed>(this)