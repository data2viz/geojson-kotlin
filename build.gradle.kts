plugins {
    kotlin("jvm" ) version("1.2.71") apply false
}

// apply the plugins on the child projects from here so that the extensions, configurations etc. that they define is
// available in a type-safe, discoverable way from the child projects build scripts.

project(":geojson-common") {
    apply(plugin = "kotlin-platform-common")
}
project(":geojson-js") {
    apply(plugin = "kotlin-platform-js")
}

project(":geojson-jvm") {
    apply(plugin = "kotlin-platform-jvm")
}


subprojects {
    repositories {
        jcenter()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}