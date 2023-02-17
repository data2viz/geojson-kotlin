package io.data2viz.geojson.jackson

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.data2viz.geojson.jackson.jackson.LngLatAltDeserializer
import io.data2viz.geojson.jackson.jackson.LngLatAltSerializer

import java.io.Serializable
import java.util.Arrays

/**
 * Construct a LngLatAlt with additional elements.
 * The specification allows for any number of additional elements in a position, after lng, lat, alt.
 * http://geojson.org/geojson-spec.html#positions
 */
@JsonDeserialize(using = LngLatAltDeserializer::class)
@JsonSerialize(using = LngLatAltSerializer::class)
class LngLatAlt
(
    var longitude: Double,
    var latitude: Double,
    private var altitude: Double = Double.NaN,
    vararg additionalElements: Double
) : Serializable {

    private var additionalElements = DoubleArray(0)

    fun hasAltitude(): Boolean  = !java.lang.Double.isNaN(altitude)

    private fun hasAdditionalElements(): Boolean = additionalElements.isNotEmpty()

    fun getAltitude(): Double = altitude

    fun setAltitude(altitude: Double) {
        this.altitude = altitude
        checkAltitudeAndAdditionalElements()
    }

    fun getAdditionalElements(): DoubleArray {
        return additionalElements
    }

    fun setAdditionalElements(vararg additionalElements: Double) {
        require(additionalElements.none { java.lang.Double.isNaN(it) }) { "No additional elements may be NaN." }
        require(additionalElements.none { java.lang.Double.isInfinite(it) }) { "No additional elements may be infinite." }
        this.additionalElements = additionalElements
        checkAltitudeAndAdditionalElements()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LngLatAlt) return false
        val lngLatAlt = other as LngLatAlt?
        return (java.lang.Double.compare(
            lngLatAlt!!.latitude,
            latitude
        ) == 0 && java.lang.Double.compare(lngLatAlt.longitude, longitude) == 0
                && java.lang.Double.compare(lngLatAlt.altitude, altitude) == 0 &&
                Arrays.equals(lngLatAlt.getAdditionalElements(), additionalElements))
    }

    override fun hashCode(): Int {
        var temp = java.lang.Double.doubleToLongBits(longitude)
        var result = (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(latitude)
        result = 31 * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(altitude)
        result = 31 * result + (temp xor temp.ushr(32)).toInt()
        for (element in additionalElements) {
            temp = java.lang.Double.doubleToLongBits(element)
            result = 31 * result + (temp xor temp.ushr(32)).toInt()
        }
        return result
    }

    override fun toString(): String {
        var s = "LngLatAlt{longitude=$longitude, latitude=$latitude, altitude=$altitude"
        s += if (additionalElements.isNotEmpty()) additionalElements.joinToString(prefix = ", additionalElements=[",  postfix = "]}") else "}"
        return s
    }

    private fun checkAltitudeAndAdditionalElements() {
        require(hasAltitude() || !hasAdditionalElements()) { "Additional Elements are only valid if Altitude is also provided." }
    }

    init {
        setAdditionalElements(*additionalElements)
        checkAltitudeAndAdditionalElements()
    }
}
