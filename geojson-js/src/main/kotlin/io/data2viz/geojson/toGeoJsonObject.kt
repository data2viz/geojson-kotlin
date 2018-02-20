package io.data2viz.geojson

import io.data2viz.geojson.js.Typed
import io.data2viz.geojson.js.asGeoJsonObject

/**
 * Parse the String as a GeoJsonObject
 */
actual fun String.toGeoJsonObject(): GeoJsonObject = JSON.parse<Typed>(this).asGeoJsonObject()
