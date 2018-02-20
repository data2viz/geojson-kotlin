package io.data2viz.geojson.jackson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import io.data2viz.geojson.jackson.LngLatAlt
import io.data2viz.geojson.jackson.MultiPoint
import org.intellij.lang.annotations.Language
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class MultiPointTest {

    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldSerializeMultiPoint() {
        val multiPoint = MultiPoint(
            LngLatAlt(100.0, 0.0),
            LngLatAlt(101.0, 1.0)
        )
        //language=JSON
        assertEquals(
            """{"type":"MultiPoint","coordinates":[[100.0,0.0],[101.0,1.0]]}""",
            mapper.writeValueAsString(multiPoint)
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldDeserializeMultiPoint() {
        @Language("JSON")
        val multiPoint = mapper
            .readValue(
                """{"type":"MultiPoint","coordinates":[[100.0,0.0],[101.0,1.0]]}""",
                MultiPoint::class.java
            )
        assertNotNull(multiPoint)
        val coordinates = multiPoint.coordinates
        PointTest.assertLngLatAlt(100.0, 0.0, java.lang.Double.NaN, coordinates[0])
        PointTest.assertLngLatAlt(101.0, 1.0, java.lang.Double.NaN, coordinates[1])
    }
}
