import guru.drako.examples.fizzbuzz.helloModule
import io.ktor.server.engine.embeddedServer
import io.ktor.server.tomcat.Tomcat

fun main() {
  embeddedServer(Tomcat, 8080) {
    helloModule()
  }.start(wait = true)
}