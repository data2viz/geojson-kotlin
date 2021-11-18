package io.data2viz.geojson.js

import io.data2viz.geojson.*



external interface Typed {
    val type:String
}


fun Typed.asGeoJsonObject():GeoJsonObject = Point(doubleArrayOf(.0, .0))


private fun Typed.asFeatureCollection(): FeatureCollection {
    return FeatureCollection(arrayOf(Feature(Point(doubleArrayOf()))))
}
