import guru.drako.examples.fizzbuzz.helloModule
import guru.drako.examples.fizzbuzz.personModule
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.server.engine.embeddedServer
import io.ktor.server.tomcat.Tomcat

fun main() {
  embeddedServer(Tomcat, 8080) {
    install(CallLogging)
    install(DefaultHeaders)

    helloModule()
    personModule()
  }.start(wait = true)
}