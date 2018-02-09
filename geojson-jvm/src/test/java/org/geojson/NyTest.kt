package org.geojson

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.serialization.json.JSON
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import java.io.File



interface GeoJson{
    val type:String
    val coordinates:Array<*>

    fun asPoint() = Point(coordinates as Array<Double>)
}


class GeoJsonImpl(override val type: String, override val coordinates: Array<Double>) : GeoJson {
    
}

data class Point(val coordinates:Array<Double>)


fun main(args: Array<String>) {
    val json = """{"type":"Line", "coordinates":[[1.0, 2.0],[3.0,4.0]]}"""
    val obj = JSON.parse<GeoJson>(json).asPoint()
    println("${obj.coordinates}")
}


class NyTest {

    private val testObject = Feature()
    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldHaveProperties() {
        assertNotNull(testObject.getProperties())
    }

    @Test
    @Throws(Exception::class)
    fun loadNy() {
        val time = System.currentTimeMillis()
        val ny = mapper.readValue(NyTest::class.java.getResourceAsStream("/ny.json"), FeatureCollection::class.java)
        println("Time to load ny ${System.currentTimeMillis() - time} ms")

        println(ny.getFeatures().size)
    }
}