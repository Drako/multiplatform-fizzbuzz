import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.31"
  kotlin("plugin.serialization") version "1.3.31"
}

group = "guru.drako.examples"
version = "1.0-SNAPSHOT"

object Versions {
  const val JUNIT = "5.4.2"
  const val KOTLINX_COROUTINES = "1.2.1"
  const val KOTLINX_IO = "0.1.8"
  const val KOTLINX_SERIALIZATION = "0.11.0"
  const val MOCKK = "1.9.3"
}

repositories {
  mavenCentral()
  maven(url = "https://kotlin.bintray.com/kotlinx")
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation(kotlin("reflect"))
  testImplementation(kotlin("test-junit5"))

  implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.KOTLINX_SERIALIZATION}")

  implementation("org.jetbrains.kotlinx:kotlinx-io-jvm:${Versions.KOTLINX_IO}")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-io-jvm:${Versions.KOTLINX_IO}")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.KOTLINX_COROUTINES}")

  testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT}")
  testImplementation("org.junit.jupiter:junit-jupiter-params:${Versions.JUNIT}")
  testRuntime("org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}")

  testImplementation("io.mockk:mockk:${Versions.MOCKK}")
}

tasks {
  "wrapper"(Wrapper::class) {
    version = "5.4.1"
  }

  withType(Test::class) {
    useJUnitPlatform()
  }
    
  withType(KotlinCompile::class) {
    kotlinOptions {
      jvmTarget = "1.8"
    }
  }
}
