# testkit-issue-repro

## Setup

Note that this project was intended to be used with [hermit](https://github.com/cashapp/hermit), however it should work
without it, so long as you use the included gradle binaries or a local gradle installation when running the commands.

## Points of interest

* [Plugin code](/plugin/src/main/kotlin/MyPlugin.kt)
* [Test code](/plugin/src/test/kotlin/MyPluginTest.kt)
* Shadow configuration
  * [maven-local](/test-project-maven-local/consumer-project/build.gradle.kts#L12)
  * [testkit](/test-project-testkit/consumer-project/build.gradle.kts#L12)

## Running the code

Run all tests:

```shell
./bin/gradle test
```

Or,

Run `maven local` tests:

```shell
./bin/gradle test --tests "*maven local*"
```

Run `maven local` tests:

```shell
./bin/gradle test --tests "*testkit*"
```

## Analysing the results

Observe the output between the two tests.

The `maven local` tests produce all the following logs:

1. `-- APPLYING MyPlugin --`
2. `-- SHADOW PLUGIN APPLIED --`
3. `-- EAGERLY found task ':consumer-project:shadowJar' --`
4. `-- LAZILY found task ':consumer-project:shadowJar' --`
5. `-- BYNAME found task ':consumer-project:shadowJar' (type class com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar_Decorated --`
6. `-- CONFIGURE CONSUMER PROJECT --`

The `testkit` tests only produce these logs:

1. `-- APPLYING MyPlugin --`
2. `-- SHADOW PLUGIN APPLIED --`
3. `-- BYNAME found task ':consumer-project:shadowJar' (type class com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar_Decorated --`
4. `-- CONFIGURE CONSUMER PROJECT --`

The `EAGERLY found...` and `LAZILY found ...` logs are missing when the plugin is added by `testkit`.

Note that if the plugin is attempted to be cast to `ShadowJar` (by uncommenting [this block](/plugin/src/main/kotlin/MyPlugin.kt#L20-L22)),
a runtime error is produced in the `testkit` version only as well.

## Expectation

The plugin should function the same when running with testkit.
