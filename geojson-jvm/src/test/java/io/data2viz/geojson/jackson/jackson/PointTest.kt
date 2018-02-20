package io.data2viz.geojson.jackson.jackson

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.data2viz.geojson.jackson.GeoJsonObject
import io.data2viz.geojson.jackson.LngLatAlt
import io.data2viz.geojson.jackson.Point
import org.intellij.lang.annotations.Language
import org.junit.Test

import java.io.IOException
import java.util.Arrays

import org.junit.Assert.*

class PointTest {

    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldSerializeAPoint() {
        val point = Point(100.0, 0.0)
        //language=JSON
        assertEquals(
            """{"type":"Point","coordinates":[100.0,0.0]}""",
            mapper.writeValueAsString(point)
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldDeserializeAPoint() {
        val value = mapper
            .readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}", GeoJsonObject::class.java)
        assertNotNull(value)
        assertTrue(value is Point)
        val point = value as Point
        assertLngLatAlt(100.0, 5.0, java.lang.Double.NaN, point.coordinates)
    }

    @Test
    @Throws(Exception::class)
    fun itShouldDeserializeAPointWithAltitude() {
        @Language("JSON")
        val value = mapper.readValue(
            """{"type":"Point","coordinates":[100.0,5.0,123]}""",
            GeoJsonObject::class.java
        )
        val point = value as Point
        assertLngLatAlt(100.0, 5.0, 123.0, point.coordinates)
    }

    @Test
    @Throws(Exception::class)
    fun itShouldSerializeAPointWithAltitude() {
        val point = Point(100.0, 0.0, 256.0)
        //language=JSON
        assertEquals(
            """{"type":"Point","coordinates":[100.0,0.0,256.0]}""",
            mapper.writeValueAsString(point)
        )
    }

    @Test
    @Throws(IOException::class)
    fun itShouldDeserializeAPointWithAdditionalAttributes() {
        @Language("JSON")
        val value = mapper.readValue(
            """{"type":"Point","coordinates":[100.0,5.0,123,456,789.2]}""",
            GeoJsonObject::class.java
        )
        val point = value as Point
        assertLngLatAlt(100.0, 5.0, 123.0, doubleArrayOf(456.0, 789.2), point.coordinates!!)
    }

    @Test
    @Throws(JsonProcessingException::class)
    fun itShouldSerializeAPointWithAdditionalAttributes() {
        val point = Point(100.0, 0.0, 256.0, 345.0, 678.0)
        //language=JSON
        assertEquals(
            """{"type":"Point","coordinates":[100.0,0.0,256.0,345.0,678.0]}""",
            mapper.writeValueAsString(point)
        )
    }

    @Test
    @Throws(JsonProcessingException::class)
    fun itShouldSerializeAPointWithAdditionalAttributesAndNull() {
        val point = Point(100.0, 0.0, 256.0, 345.0, 678.0)
        //language=JSON
        assertEquals(
            """{"type":"Point","coordinates":[100.0,0.0,256.0,345.0,678.0]}""",
            mapper.writeValueAsString(point)
        )
    }

    companion object {

        fun assertLngLatAlt(
            expectedLongitude: Double, expectedLatitude: Double, expectedAltitude: Double,
            point: LngLatAlt?
        ) {
            assertLngLatAlt(expectedLongitude, expectedLatitude, expectedAltitude, DoubleArray(0), point!!)
        }

        fun assertLngLatAlt(
            expectedLongitude: Double, expectedLatitude: Double, expectedAltitude: Double,
            expectedAdditionalElements: DoubleArray, point: LngLatAlt
        ) {
            assertEquals(expectedLongitude, point.longitude, 0.00001)
            assertEquals(expectedLatitude, point.latitude, 0.00001)
            if (java.lang.Double.isNaN(expectedAltitude)) {
                assertFalse(point.hasAltitude())
            } else {
                assertEquals(expectedAltitude, point.getAltitude(), 0.00001)
                assertTrue(Arrays.equals(expectedAdditionalElements, point.getAdditionalElements()))
            }
        }
    }
}
