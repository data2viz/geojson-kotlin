package org.geojson

import java.util.ArrayList

class FeatureCollection : GeoJsonObject(), Iterable<Feature> {

    private var features: MutableList<Feature> = ArrayList()

    fun getFeatures(): List<Feature> {
        return features
    }

    fun setFeatures(features: MutableList<Feature>) {
        this.features = features
    }

    fun add(feature: Feature): FeatureCollection {
        features.add(feature)
        return this
    }

    fun addAll(features: Collection<Feature>) {
        this.features.addAll(features)
    }

    override fun iterator(): Iterator<Feature> {
        return features.iterator()
    }

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T {
        return geoJsonObjectVisitor.visit(this)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o)
            return true
        if (o !is FeatureCollection)
            return false
        val features1 = o as FeatureCollection?
        return features == features1!!.features
    }

    override fun hashCode(): Int {
        return features.hashCode()
    }

    override fun toString(): String {
        return "FeatureCollection{" + "features=" + features + '}'.toString()
    }
}
