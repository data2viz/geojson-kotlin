package io.data2viz.geojson

import io.data2viz.geojson.js.Typed
import io.data2viz.geojson.js.asGeoJsonObject

/**
 * Parse the String as a GeoJsonObject
 */
actual fun String.toGeoJsonObject(): GeoJsonObject = JSON.parse<Typed>(this).asGeoJsonObject()

actual class FeatureProperties {

    var properties: dynamic = null

    actual fun stringProperty(name: String): String     = properties[name] as String
    actual fun intProperty(name: String): Int           = properties[name] as Int
    actual fun booleanProperty(name: String): Boolean   = properties[name] as Boolean
}

actual fun <T> String.toFeaturesAndProperties(extractFunction: FeatureProperties.() -> T): List<Pair<Feature, T>> {
    val features = JSON.parse<Typed>(this).asGeoJsonObject() as FeatureCollection
    val properties = FeatureProperties()
    return features.features.map { feature ->
        properties.properties = feature.properties
        Pair(feature, extractFunction(properties))
    }
}