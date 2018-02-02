package org.geojson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import org.geojson.GeoJsonObject
import org.geojson.GeoJsonObjectVisitor

class GeoJsonObjectTest {

    private val mapper = ObjectMapper()


    private inner class TestGeoJsonObject : GeoJsonObject() {

        override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
            throw RuntimeException("not implemented")
        }
    }
}
