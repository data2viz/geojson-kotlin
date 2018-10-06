import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

plugins {
    id("kotlin-platform-js")
}

dependencies {
    expectedBy (project(":geojson-common"))
    testCompile ("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutines_version")
    compile("org.jetbrains.kotlin:kotlin-stdlib-js:$kotlinVersion")
    testCompile("org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion")
}

tasks.named<KotlinJsCompile>("compileKotlin2Js") {
    kotlinOptions {
        metaInfo = true
        sourceMap = true
        sourceMapEmbedSources = "always"
        moduleKind = "umd"
        main = "call"
    }
}
tasks.named<KotlinJsCompile>("compileTestKotlin2Js") {
     kotlinOptions.moduleKind = "umd"
}

val copyJson = task<Copy>("copyJsonTestFiles") {
    from ("../geojson-common/src/test/resources")
    into ("${buildDir}/classes/kotlin/test")
}

kotlin {
    experimental {
        coroutines = Coroutines.ENABLE
    }
}

tasks["test"].dependsOn(copyJson)