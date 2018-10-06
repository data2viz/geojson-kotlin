import org.gradle.api.Project

/**
 * This extension property allows defining the kotlin version once and only once, and to access it from any project using
 * project.kotlinVersion (or simply kotlinVersion, since the `this` of a build script is the project)
 */
val Project.kotlinVersion get() = "1.2.71"
val Project.version get() ="0.6.0"
val Project.dokka_version get() ="0.9.16"
val Project.group get() ="io.data2viz.geojson"
val Project.junit_version  get() ="4.12"
val Project.bintray_version  get() ="1.7.3"
val Project.coroutines_version  get() ="0.26.1"
val Project.gradle_node_version  get() ="1.2.0"
val Project.node_version  get() ="8.9.3"
val Project.npm_version  get() ="5.5.1"

object Libs {
    val dokka = ""
}