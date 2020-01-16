package io.data2viz.geojson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class FeatureTest {

    private val testObject = Feature().apply { 
        geometry = Point(100.0, 0.0)
    }
    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldHaveProperties() {
        assertNotNull(testObject.getProperties())
    }

    @Test
    @Throws(Exception::class)
    fun itShouldSerializeFeature() {
        // http://geojson.org/geojson-spec.html#feature-objects
        // A feature object must have a member with the name "properties".
        // The value of the properties member is an object (any JSON object or a JSON null value).
        
        //language=JSON
        assertEquals(
            """{"type":"Feature","properties":{},"geometry":{"type":"Point","coordinates":[100.0,0.0]}}""",
            mapper.writeValueAsString(testObject)
        )
    }
}