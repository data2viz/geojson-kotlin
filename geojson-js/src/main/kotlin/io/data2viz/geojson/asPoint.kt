package io.data2viz.geojson

import io.data2viz.geojson.js.Typed
import io.data2viz.geojson.js.asPoint


actual fun String.asPoint():Point = JSON.parse<Typed>(this).asPoint()