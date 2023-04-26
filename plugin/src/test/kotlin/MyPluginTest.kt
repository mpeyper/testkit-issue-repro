
import org.assertj.core.api.Assertions.assertThat
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.io.File

class MyPluginTest {
    @Test
    fun `maven local logs plugin as expected`() {
        GradleRunner.create()
            .withArguments("--info", "--stacktrace", "publishToMavenLocal")
            .withProjectDir(File("."))
            .build()

        val result = GradleRunner.create()
            .withArguments("--info", "--stacktrace", "consumer-project:shadowJar")
            .withProjectDir(File("../test-project-maven-local"))
            .build()

        println(result.output)

        val task = result.task(":consumer-project:shadowJar") ?: fail("task not found")

        assertThat(task.outcome).isIn(TaskOutcome.SUCCESS, TaskOutcome.UP_TO_DATE)

        assertThat(result.output).contains("-- APPLYING MyPlugin --")
        assertThat(result.output).contains("-- SHADOW PLUGIN APPLIED --")
        assertThat(result.output).contains("-- EAGERLY found task ':consumer-project:shadowJar' --")
        assertThat(result.output).contains("-- LAZILY found task ':consumer-project:shadowJar' --")
        assertThat(result.output).contains("-- BYNAME found task ':consumer-project:shadowJar' (type class com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar_Decorated --")
        assertThat(result.output).contains("-- CONFIGURE CONSUMER PROJECT --")
    }

    @Test
    fun `testkit logs plugin as expected`() {
        val result = GradleRunner.create()
            .withPluginClasspath()
            .withArguments("--info", "--stacktrace", "consumer-project:shadowJar")
            .withProjectDir(File("../test-project-testkit"))
            .build()

        println(result.output)

        val task = result.task(":consumer-project:shadowJar") ?: fail("task not found")

        assertThat(task.outcome).isIn(TaskOutcome.SUCCESS, TaskOutcome.UP_TO_DATE)

        assertThat(result.output).contains("-- APPLYING MyPlugin --")
        assertThat(result.output).contains("-- SHADOW PLUGIN APPLIED --")
        assertThat(result.output).contains("-- EAGERLY found task ':consumer-project:shadowJar' --")
        assertThat(result.output).contains("-- LAZILY found task ':consumer-project:shadowJar' --")
        assertThat(result.output).contains("-- BYNAME found task ':consumer-project:shadowJar' (type class com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar_Decorated --")
        assertThat(result.output).contains("-- CONFIGURE CONSUMER PROJECT --")
    }
}