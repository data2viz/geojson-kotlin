package io.data2viz.geojson.js

import io.data2viz.geojson.*
import kotlinx.coroutines.experimental.*
import org.w3c.fetch.Request
import kotlin.browser.window
import kotlin.js.Date
import kotlin.test.*

val browserEnabled: Boolean = js("typeof document !== 'undefined'") as Boolean

class GeoJsonJsParsingTests {

    @Test
    fun loadBigJson() = promise {
        if (browserEnabled) {
            val request = window.fetch(Request("base/build/classes/kotlin/test/ny.json"))
            val response = request.await()
            val text = response.text().await()
            val start = Date.now()
            val featureCollection = text.toGeoJsonObject() as FeatureCollection
            val multi = featureCollection.features
                .filter { it.geometry is MultiPolygon }
                .map { it.geometry as MultiPolygon }
            val polygons = multi.flatMap { it.coordinates.toList() }
            println("Parsing in ${Date.now() - start} ms")
            assertEquals(104, polygons.size)
        } else {
            println("Not in a browser environment => skip loadBigJson test.")
        }

    }
}