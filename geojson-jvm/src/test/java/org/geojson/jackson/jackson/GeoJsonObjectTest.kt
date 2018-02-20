package org.geojson.jackson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import org.geojson.jackson.GeoJsonObject
import org.geojson.jackson.GeoJsonObjectVisitor

class GeoJsonObjectTest {

    private val mapper = ObjectMapper()


    private inner class TestGeoJsonObject : GeoJsonObject() {

        override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
            throw RuntimeException("not implemented")
        }
    }
}
