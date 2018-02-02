package org.geojson

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.geojson.jackson.LngLatAltDeserializer
import org.geojson.jackson.LngLatAltSerializer

import java.io.Serializable
import java.util.Arrays

@JsonDeserialize(using = LngLatAltDeserializer::class)
@JsonSerialize(using = LngLatAltSerializer::class)
class LngLatAlt : Serializable {

    var longitude: Double = .0
    var latitude: Double = .0
    private var altitude = Double.NaN
    private var additionalElements = DoubleArray(0)

    constructor() {}

    constructor(longitude: Double, latitude: Double) {
        this.longitude = longitude
        this.latitude = latitude
    }

    constructor(longitude: Double, latitude: Double, altitude: Double) {
        this.longitude = longitude
        this.latitude = latitude
        this.altitude = altitude
    }

    /**
     * Construct a LngLatAlt with additional elements.
     * The specification allows for any number of additional elements in a position, after lng, lat, alt.
     * http://geojson.org/geojson-spec.html#positions
     * @param longitude The longitude.
     * @param latitude The latitude.
     * @param altitude The altitude.
     * @param additionalElements The additional elements.
     */
    constructor(longitude: Double, latitude: Double, altitude: Double, vararg additionalElements: Double) {
        this.longitude = longitude
        this.latitude = latitude
        this.altitude = altitude

        setAdditionalElements(*additionalElements)
        checkAltitudeAndAdditionalElements()
    }

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

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is LngLatAlt) return false
        val lngLatAlt = o as LngLatAlt?
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
        
        if (hasAdditionalElements()) s += additionalElements.joinToString(prefix = ", additionalElements=[",  postfix = "]")

        s += '}'.toString()

        return s
    }

    private fun checkAltitudeAndAdditionalElements() {
        require(hasAltitude() || !hasAdditionalElements()) { "Additional Elements are only valid if Altitude is also provided." }
    }
}
