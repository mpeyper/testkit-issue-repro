import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  kotlin("jvm") version "1.7.20"
  id("com.github.johnrengelman.shadow") version "7.1.2"
  id("com.example.plugin.test") version "0.0.1"
}

val jar by tasks.getting(Jar::class) {
  archiveBaseName.set("lib")
}

val shadowJar by tasks.getting(ShadowJar::class) {
  println("-- CONFIGURE CONSUMER PROJECT --")
  relocate("api", "consumerproject.shaded.api")
}
