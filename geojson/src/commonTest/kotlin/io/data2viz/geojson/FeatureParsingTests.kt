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
            Commune(stringProp("name"), intProp("code"))
        }
        assertEquals(2, features.size)
        val archamps = features[0]
        assertEquals("Archamps", archamps.second.name)
    }


    fun europeanCountriesProperties() {
        //language=JSON
        val json = """{
      "type": "Feature",
       "geometry": {
           "type": "LineString",
           "coordinates": [
               [102.0, 0.0],
               [105.0, 1.0]
           ]
       },            
        "properties": {
            "labelrank": 5.0, 
            "gu_a3": "HUN", 
            "scalerank": 0, 
            "income_grp": "1. High income: OECD", 
            "wikipedia": -99.0, "woe_id": 23424844.0, 
            "su_dif": 0.0, "pop_est": 9905596.0, 
            "sovereignt": "Hungary", 
            "pop_year": -99.0, 
            "homepart": 1.0, 
            "region_wb": "Europe & Central Asia", 
            "adm0_a3": "HUN", 
            "continent": "Europe", 
            "adm0_a3_us": "HUN", 
            "gdp_md_est": 196600.0, 
            "brk_name": "Hungary", 
            "mapcolor8": 6.0, 
            "name_sort": "Hungary", 
            "wb_a3": "HUN", 
            "formal_en": "Republic of Hungary", 
            "sov_a3": "HUN", 
            "tiny": -99.0, 
            "geounit": "Hungary", 
            "adm0_a3_is": "HUN", 
            "iso_n3": "348", "brk_a3": "HUN", 
            "abbrev_len": 4.0, "adm0_a3_wb": -99.0, 
            "type": "Sovereign country", 
            "adm0_a3_un": -99.0, "long_len": 7.0, "economy": "2. Developed region: nonG7", 
            "featurecla": "Admin-0 country", 
            "mapcolor13": 5.0, "fips_10": "HU", "woe_id_eh": 23424844.0, "brk_diff": 0.0, 
            "wb_a2": "HU", 
            "region_un": "Europe", "lastcensus": 2001.0, "iso_a3": "HUN", "iso_a2": "HU", 
            "woe_note": "Exact WOE match as country", "postal": "HU", "geou_dif": 0.0, "name_len": 7.0, 
            "subunit": "Hungary", "name": "Hungary", "su_a3": "HUN", "level": 2.0, "gdp_year": -99.0, 
            "mapcolor7": 4.0, 
            "adm0_dif": 0.0, 
            "admin": "Hungary", 
            "abbrev": "Hun.", 
            "subregion": "Eastern Europe", 
            "mapcolor9": 1.0, 
            "un_a3": "348", 
            "name_long": "Hungary"
}}
        """.trimIndent()
    }


}

