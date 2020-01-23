package io.data2viz.geojson

import com.fasterxml.jackson.databind.ObjectMapper
import io.data2viz.geojson.jackson.LngLatAlt


typealias JacksonGeoJsonObject 		= io.data2viz.geojson.jackson.GeoJsonObject
typealias JacksonPoint 				= io.data2viz.geojson.jackson.Point
typealias JacksonMultiPoint 		= io.data2viz.geojson.jackson.MultiPoint
typealias JacksonLineString 		= io.data2viz.geojson.jackson.LineString
typealias JacksonMultiLineString 	= io.data2viz.geojson.jackson.MultiLineString
typealias JacksonPolygon 			= io.data2viz.geojson.jackson.Polygon
typealias JacksonMultiPolygon 		= io.data2viz.geojson.jackson.MultiPolygon
typealias JacksonGeometryCollection = io.data2viz.geojson.jackson.GeometryCollection
typealias JacksonFeature 			= io.data2viz.geojson.jackson.Feature
typealias JacksonFeatureCollection 	= io.data2viz.geojson.jackson.FeatureCollection

/**
 * Parse the String as a GeoJsonObject
 */
actual fun String.toGeoJsonObject(): GeoJsonObject =
	ObjectMapper().readValue(this, JacksonGeoJsonObject::class.java).toGeoJsonObject()

fun io.data2viz.geojson.jackson.GeoJsonObject.toGeoJsonObject(): GeoJsonObject = when (this) {
	is JacksonPoint 					-> this.toPoint()
	is JacksonLineString 				-> this.toLineString() //be carefull to keep this order (LineString is a MultiPoint)
	is JacksonMultiPoint 				-> this.toMultiPoint()
	is JacksonMultiLineString 			-> this.toMultiLineString()
	is JacksonPolygon 					-> this.toPolygon()
	is JacksonMultiPolygon 				-> this.toMultiPolygon()
	is JacksonGeometryCollection 		-> this.toGeometryCollection()
	is JacksonFeature 					-> this.toFeature()
	is JacksonFeatureCollection 		-> this.toFeatureCollection()

	else -> {
		throw IllegalStateException("Unknown GeoJson type:: ${this.javaClass}")
	}
}

private fun JacksonPoint.toPoint()									= Point(this.coordinates.toPosition())
private fun io.data2viz.geojson.jackson.MultiPoint.toMultiPoint() 	= MultiPoint(this.coordinates.toLine())
private fun io.data2viz.geojson.jackson.LineString.toLineString() 	= LineString(this.coordinates.toLine())
private fun JacksonMultiLineString.toMultiLineString() 				= MultiLineString(this.coordinates.toSurface())
private fun JacksonPolygon.toPolygon() 								= Polygon(this.coordinates.toSurface())
private fun JacksonMultiPolygon.toMultiPolygon() 					= MultiPolygon(this.coordinates.toSurfaces())
private fun JacksonGeometryCollection.toGeometryCollection() 		= GeometryCollection(this.getGeometries().map { it.toGeoJsonObject() as Geometry }.toTypedArray())
private fun JacksonFeature.toFeature() 								= Feature(this.geometry!!.toGeoJsonObject() as Geometry, this.id, this.getProperties())
private fun JacksonFeatureCollection.toFeatureCollection() 			= FeatureCollection(this.getFeatures().map { it.toFeature() }.toTypedArray())


private fun LngLatAlt.toPosition():Position =
	if (hasAltitude()) doubleArrayOf(this.longitude, this.latitude, this.getAltitude())
	else doubleArrayOf(this.longitude, this.latitude)


fun Collection<LngLatAlt>.toLine(): Array<Position> = map { it.toPosition() }.toTypedArray()
fun Collection<Collection<LngLatAlt>>.toSurface(): Array<Positions> = map { it.toLine() }.toTypedArray()
fun Collection<Collection<Collection<LngLatAlt>>>.toSurfaces(): Array<Lines> = map { it.toSurface() }.toTypedArray()

actual class FeatureProperties {
	var properties: Map<String, Any?> = mapOf()
    actual fun stringProperty(name: String): String  	= properties[name] as String
	actual fun intProperty(name: String): Int  			= properties[name] as Int
	actual fun booleanProperty(name: String): Boolean  	= properties[name] as Boolean
}

actual fun <T> String.toFeaturesAndProperties(extractFunction: FeatureProperties.() -> T): List<Pair<Feature, T>> {
	val features = ObjectMapper().readValue(this, JacksonFeatureCollection::class.java)
	val properties = FeatureProperties()
	return features.getFeatures().map { feature ->
		properties.properties = feature.getProperties()!!
		Pair(feature.toFeature(), extractFunction(properties))
	}
}
