package io.data2viz.geojson.jackson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import io.data2viz.geojson.jackson.LineString
import io.data2viz.geojson.jackson.LngLatAlt
import org.intellij.lang.annotations.Language
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class LineStringTest {

    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldSerializeMultiPoint() {
        val lineString = LineString(
            LngLatAlt(100.0, 0.0),
            LngLatAlt(101.0, 1.0)
        )
        //language=JSON
        assertEquals(
            """{"type":"LineString","coordinates":[[100.0,0.0],[101.0,1.0]]}""",
            mapper.writeValueAsString(lineString)
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldDeserializeLineString() {
        @Language("JSON")
        val lineString = mapper.readValue(
            """{"type":"LineString","coordinates":[[100.0,0.0],[101.0,1.0]]}""",
            LineString::class.java
        )
        assertNotNull(lineString)
        val coordinates = lineString.coordinates
        PointTest.assertLngLatAlt(100.0, 0.0, java.lang.Double.NaN, coordinates[0])
        PointTest.assertLngLatAlt(101.0, 1.0, java.lang.Double.NaN, coordinates[1])
    }
}
