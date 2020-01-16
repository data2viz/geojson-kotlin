package io.data2viz.geojson.jackson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import io.data2viz.geojson.jackson.LngLatAlt
import io.data2viz.geojson.jackson.MultiLineString
import org.junit.Test

import java.util.Arrays

import org.junit.Assert.assertEquals

class MultiLineStringTest {

    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldSerialize() {
        val multiLineString = MultiLineString()
        multiLineString.add(Arrays.asList(
            LngLatAlt(100.0, 0.0),
            LngLatAlt(101.0, 1.0)
        ))
        multiLineString.add(Arrays.asList(
            LngLatAlt(102.0, 2.0),
            LngLatAlt(103.0, 3.0)
        ))
        //language=JSON
        assertEquals(
            """{"type":"MultiLineString","coordinates":[[[100.0,0.0],[101.0,1.0]],[[102.0,2.0],[103.0,3.0]]]}""",
            mapper.writeValueAsString(multiLineString)
        )
    }
}
