import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  id("com.github.johnrengelman.shadow")
  id("com.example.plugin.test")
}

val jar by tasks.getting(Jar::class) {
  archiveBaseName.set("lib")
}

val shadowJar by tasks.getting(ShadowJar::class) {
  println("-- CONFIGURE CONSUMER PROJECT --")
  relocate("api", "consumerproject.shaded.api")
}
