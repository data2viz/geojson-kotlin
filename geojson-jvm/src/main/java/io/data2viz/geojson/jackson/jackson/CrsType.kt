package io.data2viz.geojson.jackson.jackson

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonCreator



enum class CrsType {
    NAME, LINK;

    companion object {
        @JvmStatic
        @JsonCreator
        fun forValue(value: String): CrsType {
            return valueOf(value.toUpperCase())
        }

    }

    @JsonValue
    fun toValue(): String = name.toLowerCase()

}
