package io.data2viz.geojson


import kotlin.test.*


class FeatureParsingTests {

    @Test
    fun feature() {
        //language=JSON
        val json = """
   {
       "type": "Feature",
       "geometry": {
           "type": "LineString",
           "coordinates": [
               [102.0, 0.0],
               [105.0, 1.0]
           ]
       }
   }
        """.trimIndent()

        val feature = json.toGeoJsonObject() as Feature
        assertEquals(2, (feature.geometry as? LineString)?.coordinates?.size)
        assertNull(feature.id)
    }

    @Test
    fun featureWithStringId() {
        //language=JSON
        val json = """
   {
       "type": "Feature",
       "id": "1234",
       "geometry": {
           "type": "LineString",
           "coordinates": [
               [0.0, 0.0],
               [1.0, 1.0]
           ]
       }
   }
        """.trimIndent()
        val feature = json.toGeoJsonObject() as Feature
        assertEquals("1234", feature.id)
        assertTrue { feature.geometry is LineString }
    }

    @Test
    fun featureWithIntId() {
        //language=JSON
        val json = """
   {
       "type": "Feature",
       "id": 1234,
       "geometry": {
           "type": "LineString",
           "coordinates": [
               [0.0, 0.0],
               [1.0, 1.0]
           ]
       }
   }
        """.trimIndent()
        val feature = json.toGeoJsonObject() as Feature
        assertEquals(1234, feature.id)
        assertTrue { feature.geometry is LineString }

    }

    @Test
    fun featureWithProperties() {
        //language=JSON
        val json = """
   {
       "type": "Feature",
       "geometry": { "type": "LineString", "coordinates": [ [102.0, 0.0], [105.0, 1.0] ] },
       "properties": { "name" : "Archamps", "code" : 74016}
   }
        """.trimIndent()

        val feature = json.toGeoJsonObject() as Feature
        assertNotNull(feature.properties)
    }

    @Test
    fun featuresWithProperties() {
        //language=JSON
        val json = """
            {"type":"FeatureCollection","features":[
               {
                   "type": "Feature",
                   "geometry": { "type": "LineString", "coordinates": [ [102.0, 0.0], [105.0, 1.0] ] },
                   "properties": { "name" : "Archamps", "code" : 74016}
               },
              {
                  "type": "Feature",
                  "geometry": { "type": "LineString", "coordinates": [ [102.0, 0.0], [105.0, 1.0] ] },
                  "properties": { "name" : "Collonges", "code" : 74017}
              }
            ]}
        """.trimIndent()

        data class Commune(val name:String, val code: Int)

        val features: List<Pair<Feature, Commune>> = json.toFeaturesAndProperties {
            Commune(stringProperty("name"), intProperty("code"))
        }
        assertEquals(2, features.size)
        val archamps = features[0]
        assertEquals("Archamps", archamps.second.name)
    }


}

