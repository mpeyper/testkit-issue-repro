rootProject.name = "test-project-maven-local"

include("consumer-project")

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
