package io.data2viz.geojson.js

import io.data2viz.geojson.*



external interface Typed {
    val type:String
}


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
            val dyn: dynamic = asDynamic()
            val geometry:Typed = dyn.geometry
            Feature(geometry.asGeoJsonObject() as Geometry, dyn.id, dyn.properties)
        }
        "FeatureCollection"     -> asFeatureCollection()
        else                    -> throw IllegalStateException("${type} is not known")
    }



private fun Typed.asFeatureCollection(): FeatureCollection {
    val dyn:dynamic = this
    val featureJs:dynamic = dyn.features
    val features:dynamic = Array<Geometry>(0, {Point(doubleArrayOf())})
    val size:Int = featureJs.length
    for (i in 0 until size) {
        val feature = featureJs[i]
        val typed:Typed = feature.geometry
        features[i] = Feature(typed.asGeoJsonObject() as Geometry, feature.id, feature.properties)
    }
    return FeatureCollection(features)
}
