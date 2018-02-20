package org.geojson.jackson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import org.geojson.jackson.LngLatAlt
import org.junit.Assert
import org.junit.Test

/**
 * Created by babbleshack on 27/11/16.
 */
class LngLatAltDeserializerTest {
    @Test
    @Throws(Exception::class)
    fun deserializeMongoLngLatAlt() {
        val lngLatAlt = LngLatAlt(10.0, 15.0, 5.0)
        val lngLatAltJson = ObjectMapper().writeValueAsString(lngLatAlt)
        lngLatAltJson.replace("10.0", "\"10.0\"")
        lngLatAltJson.replace("15.0", "\"15.0\"")
        val lngLatAlt1 = ObjectMapper().readValue(lngLatAltJson, LngLatAlt::class.java)
        Assert.assertTrue(lngLatAlt == lngLatAlt)
    }
}
