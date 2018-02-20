package io.data2viz.geojson.jackson

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import java.util.Arrays

@RunWith(Parameterized::class)
class GeoJsonObjectVisitorTest(private val geoJsonObject: GeoJsonObject) {
    private val instance = object :
        GeoJsonObjectVisitor<GeoJsonObject> {

        override fun visit(geoJsonObject: GeometryCollection): GeoJsonObject? {
            Assert.assertEquals(GeometryCollection::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }

        override fun visit(geoJsonObject: FeatureCollection): GeoJsonObject? {
            Assert.assertEquals(FeatureCollection::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }

        override fun visit(geoJsonObject: Point): GeoJsonObject? {
            Assert.assertEquals(Point::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }

        override fun visit(geoJsonObject: Feature): GeoJsonObject? {
            Assert.assertEquals(Feature::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }

        override fun visit(geoJsonObject: MultiLineString): GeoJsonObject? {
            Assert.assertEquals(MultiLineString::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }

        override fun visit(geoJsonObject: Polygon): GeoJsonObject? {
            Assert.assertEquals(Polygon::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }

        override fun visit(geoJsonObject: MultiPolygon): GeoJsonObject? {
            Assert.assertEquals(MultiPolygon::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }

        override fun visit(geoJsonObject: MultiPoint): GeoJsonObject? {
            Assert.assertEquals(MultiPoint::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }

        override fun visit(geoJsonObject: LineString): GeoJsonObject? {
            Assert.assertEquals(LineString::class.java, geoJsonObject.javaClass)
            return geoJsonObject
        }
    }

    @Test
    fun should_visit_right_class() {
        // When
        val result = geoJsonObject.accept(this.instance)
        // Then
        Assert.assertEquals(geoJsonObject, result)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return Arrays.asList(
                *arrayOf(
                    arrayOf<Any>(GeometryCollection()),
                    arrayOf<Any>(FeatureCollection()),
                    arrayOf<Any>(Point(12.0, 13.0)),
                    arrayOf<Any>(Feature()),
                    arrayOf<Any>(
                        MultiLineString(
                            Arrays.asList(
                                LngLatAlt(
                                    12.0,
                                    13.0
                                )
                            )
                        )
                    ),
                    arrayOf<Any>(Polygon()),
                    arrayOf<Any>(MultiPolygon()),
                    arrayOf<Any>(MultiPoint()),
                    arrayOf<Any>(LineString())
                )
            )
        }
    }

    //	@Test
    //	public void itShouldAdapter() throws Exception {
    //		Assert.assertNull(geoJsonObject.accept(new GeoJsonObjectVisitor.Adapter<Void>()));
    //	}
}