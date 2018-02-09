package org.geojson.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import org.geojson.Crs
import org.geojson.GeoJsonObject
import org.geojson.Point
import org.intellij.lang.annotations.Language
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class CrsTest {

    private val mapper = ObjectMapper()

    @Test
    @Throws(Exception::class)
    fun itShouldParseCrsWithLink() {
        
        @Language("JSON")
        val value = mapper.readValue(
            """{"crs": { 
                    "type": "link", 
                    "properties": { 
                        "href": "http://example.com/crs/42", 
                        "type": "proj4" 
                        }
                    },"type":"Point",
                    "coordinates":[100.0,5.0]
                }""".trimIndent(),
            GeoJsonObject::class.java
        )
        assertNotNull(value)
        assertEquals(CrsType.LINK, value.crs!!.type)
    }

    @Test
    @Throws(Exception::class)
    fun itShouldSerializeCrsWithLink() {
        val point = Point()
        val crs = Crs()
        crs.type = CrsType.LINK
        point.crs = crs
        val value = mapper.writeValueAsString(point)
        assertEquals("{\"type\":\"Point\",\"crs\":{\"type\":\"link\",\"properties\":{}}}", value)
    }
}
