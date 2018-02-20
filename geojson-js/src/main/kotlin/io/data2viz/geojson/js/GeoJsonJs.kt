package io.data2viz.geojson.js

import io.data2viz.geojson.*

external interface Typed {
    val type:String
}

fun String.toGeoJsonObject(): GeoJsonObject  = JSON.parse<Typed>(this).asGeoJsonObject()

fun Typed.asGeoJsonObject():GeoJsonObject =
    when (type) {
        "Point"                 -> Point(asDynamic().coordinates)
        "MultiPoint"            -> MultiPoint(asDynamic().coordinates)
        "LineString"            -> LineString(asDynamic().coordinates)
        "MultiLineString"       -> MultiLineString(asDynamic().coordinates)
        "Polygon"               -> Polygon(asDynamic().coordinates)
        "MultiPolygon"          -> MultiPolygon(asDynamic().coordinates)
        "GeometryCollection"    -> {
            val types:Array<Typed> = asDynamic().geometries
            val geometries:Array<Geometry> = types.map {it.asGeoJsonObject() as Geometry}.toTypedArray()
            GeometryCollection(geometries)
        }
        "Feature"               -> {
            val geometry:Typed = asDynamic().geometry  
            Feature(geometry.asGeoJsonObject() as Geometry)
        }
        "FeatureCollection"     -> asFeatureCollection() 
        else                    -> throw IllegalStateException("${type} is not known")
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
        features[i] = Feature(typed.asGeoJsonObject() as Geometry)
    }
    return FeatureCollection(features)
}
