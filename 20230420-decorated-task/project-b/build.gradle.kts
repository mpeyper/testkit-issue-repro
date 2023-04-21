import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  id("com.github.johnrengelman.shadow")
  id("com.squareup.cash.decoratedtask.test")
}

val jar by tasks.getting(Jar::class) {
  archiveBaseName.set("lib")
}

val shadowJar by tasks.getting(ShadowJar::class) {
  println("-- CONFIGURE PROJECT --")
  relocate("api", "projectb.shaded.api")
}
