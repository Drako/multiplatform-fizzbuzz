package guru.drako.examples.fizzbuzz

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import kotlinx.html.body
import kotlinx.html.p

class BadPersonException(
  message: String? = null,
  cause: Throwable? = null
) : RuntimeException(message, cause)

fun Application.helloModule() {
  install(StatusPages) {
    exception<BadPersonException> { ex ->
      call.respondHtml(HttpStatusCode.BadRequest) {
        body {
          p {
            +"BadPersonException: ${ex.message}"
          }
        }
      }
    }
  }

  routing {
    route("/hello") {
      get("/") {
        call.respondHtml {
          body {
            p {
              +"Hello world!"
            }
          }
        }
      }

      get("/{person}") {
        val person = call.parameters["person"]!!
        when (person) {
          "Hitler" -> throw BadPersonException("We do not greet Hitler!")
          else -> call.respondHtml {
            body {
              p {
                +"Hello $person!"
              }
            }
          }
        }
      }
    }
  }
}
