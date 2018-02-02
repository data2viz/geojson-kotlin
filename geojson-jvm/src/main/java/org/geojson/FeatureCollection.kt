package org.geojson

import java.util.ArrayList

class FeatureCollection : GeoJsonObject(), Iterable<Feature> {

    private var features: MutableList<Feature> = ArrayList()

    fun getFeatures(): List<Feature> = features

    fun setFeatures(features: MutableList<Feature>) {
        this.features = features
    }

    fun add(feature: Feature): FeatureCollection = this.apply { features.add(feature) }

    fun addAll(features: Collection<Feature>) {
        this.features.addAll(features)
    }

    override fun iterator(): Iterator<Feature> = features.iterator()

    override fun <T> accept(geoJsonObjectVisitor: GeoJsonObjectVisitor<T>): T = geoJsonObjectVisitor.visit(this)!!

    override fun equals(o: Any?): Boolean {
        if (this === o)
            return true
        if (o !is FeatureCollection)
            return false
        val features1 = o as FeatureCollection?
        return features == features1!!.features
    }

    override fun hashCode(): Int = features.hashCode()

    override fun toString(): String = "FeatureCollection{features=$features}"
}
