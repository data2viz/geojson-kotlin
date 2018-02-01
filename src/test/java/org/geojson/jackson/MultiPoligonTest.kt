package org.geojson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import org.geojson.LngLatAlt
import org.geojson.MultiPolygon
import org.geojson.Polygon
import org.junit.Test

import org.junit.Assert.assertEquals

class MultiPoligonTest {

    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldSerialize() {
        val multiPolygon = MultiPolygon()
        multiPolygon.add(
            Polygon(
                LngLatAlt(102.0, 2.0), LngLatAlt(103.0, 2.0), LngLatAlt(103.0, 3.0),
                LngLatAlt(102.0, 3.0), LngLatAlt(102.0, 2.0)
            )
        )
        val polygon = Polygon(MockData.EXTERNAL)
        polygon.addInteriorRing(MockData.INTERNAL)
        multiPolygon.add(polygon)
        assertEquals(
            "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[102.0,2.0],[103.0,2.0],[103.0,3.0],[102.0,3.0],[102.0,2.0]]],"
                    + "[[[100.0,0.0],[101.0,0.0],[101.0,1.0],[100.0,1.0],[100.0,0.0]],"
                    + "[[100.2,0.2],[100.8,0.2],[100.8,0.8],[100.2,0.8],[100.2,0.2]]]]}",
            mapper.writeValueAsString(multiPolygon)
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldDeserialize() {
        val multiPolygon = mapper.readValue(
            "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[102.0,2.0],[103.0,2.0],[103.0,3.0],[102.0,3.0],[102.0,2.0]]],"
                    + "[[[100.0,0.0],[101.0,0.0],[101.0,1.0],[100.0,1.0],[100.0,0.0]],"
                    + "[[100.2,0.2],[100.8,0.2],[100.8,0.8],[100.2,0.8],[100.2,0.2]]]]}", MultiPolygon::class.java
        )
        assertEquals(2, multiPolygon.coordinates.size.toLong())
    }
}
