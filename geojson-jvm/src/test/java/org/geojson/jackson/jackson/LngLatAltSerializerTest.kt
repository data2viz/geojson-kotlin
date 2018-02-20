package org.geojson.jackson.jackson

import org.geojson.jackson.LngLatAlt
import org.junit.Assert
import org.junit.Test

import com.fasterxml.jackson.databind.ObjectMapper

class LngLatAltSerializerTest {

    @Test
    @Throws(Exception::class)
    fun testSerialization() {
        val position = LngLatAlt(49.43245, 52.42345, 120.34626)
        val correctJson = "[49.43245,52.42345,120.34626]"
        val producedJson = ObjectMapper().writeValueAsString(position)
        Assert.assertEquals(correctJson, producedJson)
    }
}
