GeoJson Kotlin
=========================

This project goal is to provide GeoJson serialization for kotlin multiplatform (JVM, JS).


https://tools.ietf.org/html/rfc7946


The specific format of GeoJson files does not allow the use of kotlinx.serialization. JS and
JVM implementations are completely distinct but they share the same base objects and interfaces. 

JVM implementation is based on the project [GeoJson-Jackson](https://github.com/opendatalab-de/geojson-jackson).


