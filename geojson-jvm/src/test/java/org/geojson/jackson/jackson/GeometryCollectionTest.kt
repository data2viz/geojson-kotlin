package org.geojson.jackson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import org.geojson.*
import org.geojson.jackson.*
import org.junit.Test

import org.junit.Assert.*

class GeometryCollectionTest {

    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldSerialize() {
        val gc = GeometryCollection()
        gc.add(Point(100.0, 0.0))
        gc.add(
            LineString(
                LngLatAlt(101.0, 0.0),
                LngLatAlt(102.0, 1.0)
            )
        )
        assertEquals(
            "{\"type\":\"GeometryCollection\","
                    + "\"geometries\":[{\"type\":\"Point\",\"coordinates\":[100.0,0.0]},"
                    + "{\"type\":\"LineString\",\"coordinates\":[[101.0,0.0],[102.0,1.0]]}]}",
            mapper.writeValueAsString(gc)
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldDeserialize() {
        val geometryCollection = mapper
            .readValue(
                "{\"type\":\"GeometryCollection\","
                        + "\"geometries\":[{\"type\":\"Point\",\"coordinates\":[100.0,0.0]},"
                        + "{\"type\":\"LineString\",\"coordinates\":[[101.0,0.0],[102.0,1.0]]}]}",
                GeometryCollection::class.java
            )
        assertNotNull(geometryCollection)
    }

    @Test
    @Throws(Exception::class)
    fun itShouldDeserializeSubtype() {
        val collection = mapper
            .readValue(
                "{\"type\": \"FeatureCollection\","
                        + "  \"features\": ["
                        + "    {"
                        + "      \"type\": \"Feature\","
                        + "      \"geometry\": {"
                        + "        \"type\": \"GeometryCollection\","
                        + "        \"geometries\": ["
                        + "          {"
                        + "            \"type\": \"Point\","
                        + "            \"coordinates\": [100.0, 0.0]"
                        + "          }"
                        + "        ]"
                        + "      }"
                        + "    }"
                        + "  ]"
                        + "}",
                FeatureCollection::class.java
            )
        assertNotNull(collection)
        assertTrue(collection.getFeatures()[0].geometry is GeometryCollection)
    }
}
