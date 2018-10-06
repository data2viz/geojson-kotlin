import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("kotlin-platform-jvm")
    id("org.jetbrains.dokka")
}

dependencies {

    expectedBy(project(":geojson-common"))

    compile("com.fasterxml.jackson.core:jackson-core:2.7.3")
    compile("com.fasterxml.jackson.core:jackson-databind:2.7.3")
    compile("com.fasterxml.jackson.core:jackson-annotations:2.7.0")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.0")

    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    testCompile("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testCompile("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")

    testCompile("org.mockito:mockito-core:1.10.19")
    testCompile("junit:junit:4.12")
}


val copyJson = task<Copy>("copyJson") {
    from("../geojson-common/src/test/resources")
    into("${buildDir}/classes/kotlin/test")
}

tasks["test"].dependsOn(copyJson)

tasks {
    named<DokkaTask>("dokka") {
        jdkVersion = 8
        includes = listOf("README.md")
        outputFormat = "kotlin-website"
    }
}


val dokkaJavadoc = task<DokkaTask>("dokkaJavadoc") {
    outputFormat = "javadoc"
    outputDirectory = "$buildDir/javadoc"
}

val javadocJar = task<Jar>("javadocJar"){
    classifier = "javadoc"
    from("$buildDir/javadoc")
    dependsOn(dokkaJavadoc)
}

