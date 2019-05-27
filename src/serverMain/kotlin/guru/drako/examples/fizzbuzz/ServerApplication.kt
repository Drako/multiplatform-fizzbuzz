import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.tomcat.Tomcat
import kotlinx.html.body
import kotlinx.html.p

fun main() {
  embeddedServer(Tomcat, 8080) {
    routing {
      get("/") {
        call.respondHtml {
          body {
            p {
              +"Hello world!"
            }
          }
        }
      }
    }
  }.start(wait = true)
}