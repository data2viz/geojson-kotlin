import org.gradle.api.Project

val Project.kotlinVersion get() = Versions.kotlin
val Project.version get() = Versions.version
val Project.dokka_version get() = Versions.dokka
val Project.group get() = Versions.group
val Project.junit_version get() = Versions.junit
val Project.bintray_version get() = Versions.bintray
val Project.coroutines_version get() = Versions.coroutines
val Project.gradle_node_version get() = Versions.gradle_node
val Project.node_version get() = Versions.node
val Project.npm_version get() = Versions.npm

object Versions {
    val group = "io.data2viz.geojson"
    val version = "0.6.1-SNAPSHOT"


    val kotlin = "1.2.71"
    val dokka = "0.9.16"
    val junit = "4.12"
    val bintray = "1.7.3"
    val coroutines = "0.26.1"
    val gradle_node = "1.2.0"
    val node = "8.9.3"
    val npm = "5.5.1"

}