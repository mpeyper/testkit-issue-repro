plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
}

gradlePlugin {
    plugins {
        create("decorated-task-test-plugin") {
            id = "com.squareup.cash.decoratedtask.test"
            implementationClass = "MyPlugin"
        }
    }
}
