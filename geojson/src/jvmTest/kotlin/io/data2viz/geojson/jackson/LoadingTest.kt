package io.data2viz.geojson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.data2viz.geojson.JacksonGeoJsonObject
import io.data2viz.geojson.toGeoJsonObject
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class LoadingTest {


    @Test
    @Throws(Exception::class)
    fun loadNyGeoJson() {
        val input = this.javaClass.getResourceAsStream("/ny.json")
        val time = System.currentTimeMillis()
        val geojson = ObjectMapper().readValue(input, JacksonGeoJsonObject::class.java)
        val geoJsonObject = geojson.toGeoJsonObject()
        println("loading in ${System.currentTimeMillis()  -time} ms.")
        
    }
}