buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
        classpath(fileTree("../plugin/build/libs/") { include("plugin.jar") })
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")

    buildscript {
        repositories {
            mavenCentral()
        }
    }

    repositories {
        mavenCentral()
    }
}
