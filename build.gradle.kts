buildscript {
    repositories {
        jcenter()
        mavenCentral()
        maven(uri("https://kotlin.bintray.com/kotlinx"))
        maven(uri("https://plugins.gradle.org/m2/"))
    }

    dependencies {
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintray}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.moowork.gradle:gradle-node-plugin:${Versions.gradle_node}")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}")
    }
}

plugins {
    kotlin("jvm") version ("1.2.71") apply false
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


    apply(plugin = "com.jfrog.bintray")
    apply(plugin = "maven")
    apply(plugin = "maven-publish")

    if(project.hasProperty("bintray.user")){
        bintray
    }
}