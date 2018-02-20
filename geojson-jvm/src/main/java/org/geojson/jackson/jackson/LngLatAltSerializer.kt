package org.geojson.jackson.jackson

import java.io.IOException

import org.geojson.jackson.LngLatAlt

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

class LngLatAltSerializer : JsonSerializer<LngLatAlt>() {

    @Throws(IOException::class)
    override fun serialize(value: LngLatAlt, jgen: JsonGenerator, provider: SerializerProvider) {
        jgen.writeStartArray()
        jgen.writeNumber(value.longitude)
        jgen.writeNumber(value.latitude)
        if (value.hasAltitude()) {
            jgen.writeNumber(value.getAltitude())

            for (d in value.getAdditionalElements()) {
                jgen.writeNumber(d)
            }
        }
        jgen.writeEndArray()
    }
}
