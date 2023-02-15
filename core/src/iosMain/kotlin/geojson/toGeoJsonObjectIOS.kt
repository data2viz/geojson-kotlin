package io.data2viz.geojson

/**
 * Parse the String as a GeoJsonObject
 */
actual fun String.toGeoJsonObject(): GeoJsonObject = Point(doubleArrayOf(.0, .0))

actual class FeatureProperties {
    actual fun stringProperty(name: String): String     = ""
    actual fun intProperty(name: String): Int           = 0
    actual fun booleanProperty(name: String): Boolean   = true
}

actual fun <T> String.toFeaturesAndProperties(extractFunction: FeatureProperties.() -> T): List<Pair<Feature, T>> {
    val features = listOf(Feature(Point(doubleArrayOf())))
    val properties = FeatureProperties()
    return features.map { feature ->
        Pair(feature, extractFunction(properties))
    }

}
