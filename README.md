GeoJson Kotlin
=========================

[![Download](https://api.bintray.com/packages/data2viz/geojson-kotlin/geojson-kotlin/images/download.svg) ](https://bintray.com/data2viz/geojson-kotlin/geojson-kotlin/_latestVersion)
[![Build Status](https://travis-ci.org/data2viz/geojson-kotlin.svg?branch=master)](https://travis-ci.org/data2viz/geojson-kotlin)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) 


This project goal is to provide GeoJson serialization for kotlin multiplatform (JVM, JS).


https://tools.ietf.org/html/rfc7946


The specific format of GeoJson files does not allow the use of kotlinx.serialization. JS and
JVM implementations are completely distinct but they share the same base objects and interfaces. 

JVM implementation is based on the project [GeoJson-Jackson](https://github.com/opendatalab-de/geojson-jackson).

The project is deployed on maven central so you have to define it in your repositories.

```groovy
repositories {
    mavenCentral()
}
```


To use it in a JS context add the following dependency: 
```groovy
    compile 'io.data2viz.geojson:geojson-js:0.6.0'
```

To use it in a JVM context add the following dependency: 
```groovy
    compile 'io.data2viz.geojson:geojson-jvm:0.6.0'
```

You can then use the String extension toGeoJsonObject to transform any String into a GeoJsonObject:

```kotlin
val featureCollection = json.toGeoJsonObject() as FeatureCollection
```


