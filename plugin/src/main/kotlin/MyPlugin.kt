
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        println("-- APPLYING MyPlugin --")
        project.pluginManager.withPlugin("com.github.johnrengelman.shadow") {
            println("-- SHADOW PLUGIN APPLIED --")
            project.tasks.withType(ShadowJar::class.java) {
                println("-- EAGERLY found $this --")
            }
            project.tasks.withType(ShadowJar::class.java).configureEach {
                println("-- LAZILY found $this --")
            }
            project.tasks.named("shadowJar").configure {
                println("-- BYNAME found $this (type ${this::class.java} --")
            }
//            project.tasks.named("shadowJar", ShadowJar::class.java).configure {
//                println("-- BYNAME and typed found $this (type ${this::class.java} --")
//            }
        }
    }
}
