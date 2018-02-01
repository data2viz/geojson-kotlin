package org.geojson

/**
 * Visitor to handle all different types of [GeoJsonObject].
 *
 * @param <T>
 * return type of the visitor.
</T> */
interface GeoJsonObjectVisitor<T> {

    fun visit(geoJsonObject: GeometryCollection): T?

    fun visit(geoJsonObject: FeatureCollection): T?

    fun visit(geoJsonObject: Point): T?

    fun visit(geoJsonObject: Feature): T?

    fun visit(geoJsonObject: MultiLineString): T?

    fun visit(geoJsonObject: Polygon): T?

    fun visit(geoJsonObject: MultiPolygon): T?
    
    fun visit(geoJsonObject: MultiPoint): T?

    fun visit(geoJsonObject: LineString): T?

    /**
     * An abstract adapter class for visiting GeoJson objects.
     * The methods in this class are empty.
     * This class exists as convenience for creating listener objects.
     *
     * @param <T> Return type of the visitor
    </T> */
    class Adapter<T> : GeoJsonObjectVisitor<T> {

        override fun visit(geoJsonObject: GeometryCollection): T?  = GeometryCollection() as T

        override fun visit(geoJsonObject: FeatureCollection): T?  = FeatureCollection() as T

        override fun visit(geoJsonObject: Point): T? {
            return null
        }

        override fun visit(geoJsonObject: Feature): T? {
            return null
        }

        override fun visit(geoJsonObject: MultiLineString): T? {
            return null
        }

        override fun visit(geoJsonObject: Polygon): T? {
            return null
        }

        override fun visit(geoJsonObject: MultiPolygon): T? {
            return null
        }

        override fun visit(geoJsonObject: MultiPoint): T? {
            return null
        }

        override fun visit(geoJsonObject: LineString): T? {
            return null
        }
    }
}
