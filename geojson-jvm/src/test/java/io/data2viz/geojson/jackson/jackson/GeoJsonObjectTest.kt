package io.data2viz.geojson.jackson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import io.data2viz.geojson.jackson.GeoJsonObject
import io.data2viz.geojson.jackson.GeoJsonObjectVisitor

class GeoJsonObjectTest {

    private val mapper = ObjectMapper()


    private inner class TestGeoJsonObject : GeoJsonObject() {

        override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
            throw RuntimeException("not implemented")
        }
    }
}
