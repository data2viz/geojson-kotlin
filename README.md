GeoJson Kotlin
=========================

[![Build Status](https://travis-ci.org/data2viz/geojson-kotlin.svg?branch=master)](https://travis-ci.org/data2viz/geojson-kotlin)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)


This project goal is to provide [GeoJson](https://tools.ietf.org/html/rfc7946) deserialization for kotlin multiplatform (JVM, JS).





The specific format of GeoJson files does not allow the use of kotlinx.serialization. JS and
JVM implementations are completely distinct but they share the same base objects and interfaces.

JVM implementation is based on the project [GeoJson-Jackson](https://github.com/opendatalab-de/geojson-jackson).

## Using in your projects

The library is published to data2viz space repository.



### Gradle

- Add the data2viz maven repository:

```kotlin
repositories {
    maven { url = uri("https://maven.pkg.jetbrains.space/data2viz/p/maven/public") }
}
```

The project is deployed using Gradle metadata. You can use the dependency
on Gradle Metadata. Depending on your platform (JS or JVM) the correct
artifact will be imported.

```groovy
    compile 'io.data2viz.geojson:core:0.6.6'
```

The JS version is available in [both modes](https://kotlinlang.org/docs/reference/js-ir-compiler.html), `Legacy` and `IR`.

You can then use the String extension toGeoJsonObject to transform any String into a GeoJsonObject:

```kotlin
val featureCollection = json.toGeoJsonObject() as FeatureCollection
```

If you deserialize a FeatureCollection that have properties (main use case) you
need to pass a function that transform the properties in a specific domain object.

```kotlin
class CountryProperties(val name: String, val id: Int)

val countries = countriesGeoJson.toFeatures {
        CountryProperties(stringProperty("name"), intProperty("id"))
}
```

You then retrieve a list of `Pair<Feature, CountryPropertiess>`

