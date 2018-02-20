package org.geojson.jackson

import org.junit.Test

import java.util.Arrays

import org.junit.Assert.assertEquals

class ToStringTest {

    @Test
    @Throws(Exception::class)
    fun itShouldToStringCrs() {
        assertEquals("Crs{type='NAME', properties={}}", Crs().toString())
    }

    @Test
    @Throws(Exception::class)
    fun itShouldToStringFeature() {
        assertEquals("Feature{properties={}, geometry=null, id='null'}", Feature().toString())
    }

    @Test
    @Throws(Exception::class)
    fun itShouldToStringFeatureCollection() {
        assertEquals("FeatureCollection{features=[]}", FeatureCollection().toString())
    }

    @Test
    @Throws(Exception::class)
    fun itShouldToStringPoint() {
        val geometry = Point(10.0, 20.0)
        assertEquals(
            "Point{coordinates=LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}} GeoJsonObject{}",
            geometry.toString()
        )
    }

    @Test
    fun itShouldToStringPointWithAdditionalElements() {
        val geometry = Point(10.0, 20.0, 30.0, 40.0, 50.0)
        assertEquals(
            "Point{coordinates=LngLatAlt{longitude=10.0, latitude=20.0, altitude=30.0, additionalElements=[40.0, 50.0]}} GeoJsonObject{}",
            geometry.toString()
        )
    }

    @Test
    fun itShouldToStringPointWithAdditionalElementsAndIgnoreNulls() {
        val geometry = Point(10.0, 20.0, 30.0, 40.0, 50.0)
        assertEquals(
            "Point{coordinates=LngLatAlt{longitude=10.0, latitude=20.0, altitude=30.0, additionalElements=[40.0, 50.0]}} GeoJsonObject{}",
            geometry.toString()
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldToStringPolygon() {
        val geometry = Polygon(
            LngLatAlt(10.0, 20.0),
            LngLatAlt(30.0, 40.0),
            LngLatAlt(10.0, 40.0),
            LngLatAlt(10.0, 20.0)
        )
        assertEquals(
            "Polygon{} Geometry{coordinates=[[LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=30.0, latitude=40.0, altitude=NaN}, LngLatAlt{longitude=10.0, latitude=40.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}]]} GeoJsonObject{}",
            geometry.toString()
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldToStringMultiPolygon() {
        val geometry = MultiPolygon(
            Polygon(
                LngLatAlt(10.0, 20.0), LngLatAlt(30.0, 40.0),
                LngLatAlt(10.0, 40.0), LngLatAlt(10.0, 20.0)
            )
        )
        geometry.add(
            Polygon(
                LngLatAlt(5.0, 20.0),
                LngLatAlt(30.0, 40.0),
                LngLatAlt(10.0, 40.0),
                LngLatAlt(
                    5.0,
                    20.0
                )
            )
        )
        assertEquals(
            "MultiPolygon{} Geometry{coordinates=[[[LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=30.0, latitude=40.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=10.0, latitude=40.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}]], "
                    + "[[LngLatAlt{longitude=5.0, latitude=20.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=30.0, latitude=40.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=10.0, latitude=40.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=5.0, latitude=20.0, altitude=NaN}]]]} GeoJsonObject{}",
            geometry.toString()
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldToStringLineString() {
        val geometry = LineString(
            LngLatAlt(49.0, 9.0),
            LngLatAlt(41.0, 1.0)
        )
        assertEquals(
            "LineString{} MultiPoint{} Geometry{coordinates=["
                    + "LngLatAlt{longitude=49.0, latitude=9.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=41.0, latitude=1.0, altitude=NaN}]} GeoJsonObject{}",
            geometry.toString()
        )
    }

    @Test
    @Throws(Exception::class)
    fun itShouldToStringMultiLineString() {
        val geometry = MultiLineString(
            Arrays.asList(
                LngLatAlt(49.0, 9.0),
                LngLatAlt(41.0, 1.0)
            )
        )
        geometry.add(Arrays.asList(LngLatAlt(10.0, 20.0), LngLatAlt(30.0, 40.0)))
        assertEquals(
            "MultiLineString{} Geometry{coordinates=[[LngLatAlt{longitude=49.0, latitude=9.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=41.0, latitude=1.0, altitude=NaN}], "
                    + "[LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}, "
                    + "LngLatAlt{longitude=30.0, latitude=40.0, altitude=NaN}]]} GeoJsonObject{}",
            geometry.toString()
        )
    }
}
