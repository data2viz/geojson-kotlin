import org.gradle.api.Project

val Project.kotlinVersion get() = Versions.kotlinVersion
val Project.version get() = Versions.version
val Project.dokka_version get() = Versions.dokka_version
val Project.group get() = Versions.group
val Project.junit_version get() = Versions.junit_version
val Project.bintray_version get() = Versions.bintray_version
val Project.coroutines_version get() = Versions.coroutines_version
val Project.gradle_node_version get() = Versions.gradle_node_version
val Project.node_version get() = Versions.node_version
val Project.npm_version get() = Versions.npm_version

object Versions {
    val kotlinVersion = "1.2.71"
    val version = "0.6.0"
    val dokka_version = "0.9.16"
    val group = "io.data2viz.geojson"
    val junit_version = "4.12"
    val bintray_version = "1.7.3"
    val coroutines_version = "0.26.1"
    val gradle_node_version = "1.2.0"
    val node_version = "8.9.3"
    val npm_version = "5.5.1"

}