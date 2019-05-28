package guru.drako.examples.fizzbuzz

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.body
import kotlinx.html.p

fun Application.helloModule() {
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
}
