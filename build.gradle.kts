plugins {
  kotlin("multiplatform") version "1.3.31"
}

repositories {
  mavenCentral()
  jcenter()
  maven("https://kotlin.bintray.com/kotlinx")
}

object Versions {
  const val JACKSON = "2.9.9"
  const val JUNIT = "5.4.2"
  const val KTOR = "1.2.0"
  const val LOGBACK = "1.2.3"
}

kotlin {
  jvm("console") {
    sequenceOf("main", "test").forEach {
      compilations[it].kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }

  mingwX64("windows") {
    binaries {
      executable("fizzbuzz_win") {
        entryPoint = "guru.drako.examples.fizzbuzz.main"

        linkerOpts("-Wl,-subsystem,windows")
      }
    }
  }

  jvm("server") {
    sequenceOf("main", "test").forEach {
      compilations[it].kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }

  sourceSets {
    getByName("commonMain") {
      dependencies {
        implementation(kotlin("stdlib-common"))
      }
    }

    getByName("commonTest") {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }

    jvm("console") {
      compilations["main"].defaultSourceSet {
        dependencies {
          implementation(kotlin("stdlib-jdk8"))
          implementation(kotlin("reflect"))
        }
      }

      compilations["test"].defaultSourceSet {
        dependencies {
          implementation(kotlin("test-junit5"))

          implementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT}")
          implementation("org.junit.jupiter:junit-jupiter-params:${Versions.JUNIT}")
          runtimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}")
        }
      }
    }

    jvm("server") {
      compilations["main"].defaultSourceSet {
        dependencies {
          implementation(kotlin("stdlib-jdk8"))
          implementation(kotlin("reflect"))

          implementation("io.ktor:ktor-server-tomcat:${Versions.KTOR}")
          implementation("io.ktor:ktor-html-builder:${Versions.KTOR}")
          implementation("io.ktor:ktor-jackson:${Versions.KTOR}")

          implementation("ch.qos.logback:logback-classic:${Versions.LOGBACK}")

          implementation("com.fasterxml.jackson.core:jackson-core:${Versions.JACKSON}")
          implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.JACKSON}")
          implementation("com.fasterxml.jackson.core:jackson-annotations:${Versions.JACKSON}")
          implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON}")
        }
      }

      compilations["test"].defaultSourceSet {
        dependencies {
          implementation(kotlin("test-junit5"))

          implementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT}")
          implementation("org.junit.jupiter:junit-jupiter-params:${Versions.JUNIT}")
          runtimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}")

          implementation("io.ktor:ktor-server-test-host:${Versions.KTOR}")
        }
      }
    }
  }
}

tasks {
  "wrapper"(Wrapper::class) {
    version = "5.4.1"
  }

  sequenceOf("consoleTest", "serverTest").forEach { 
    it(Test::class) {
      useJUnitPlatform()
    }
  }
}
