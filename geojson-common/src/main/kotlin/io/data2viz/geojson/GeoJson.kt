package io.data2viz.geojson


typealias Position = Array<Double>
typealias Positions = Array<Position>
typealias Lines = Array<Positions>
typealias Surface = Array<Lines>

val Position.lon: Double
    get() = this[0]

val Position.lat: Double
    get() = this[1]

val Position.alt: Double?
    get() = if (size > 2) this[2] else null

external interface Point{
    val coordinates: Position
}

data class MultiPoint(val coordinates: Positions)
data class LineString(val coordinates: Positions)
data class MultiLineString(val coordinates: Lines)
data class Polygon(val coordinates: Lines) {
    val hasHoles = coordinates.size > 1
}
data class MultiPolygon(val coordinates: Array<Surface>)

