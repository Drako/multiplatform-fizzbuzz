package guru.drako.examples.fizzbuzz

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.response.respondBytes
import io.ktor.response.respondText
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Person(
  val name: String,
  val age: Int,
  val city: String
)

data class UpdatePerson(
  val name: String? = null,
  val age: Int? = null,
  val city: String? = null
)

object PersonStore {
  private var nextId: Int = 1
    get() {
      return field++
    }

  private val persons = mutableMapOf<Int, Person>()

  fun save(person: Person, id: Int = nextId): Int {
    persons[id] = person
    return id
  }

  fun find(id: Int): Person? {
    return persons[id]
  }

  fun list(offset: Int = 0, limit: Int = 10): Map<Int, Person> {
    return persons.keys
      .asSequence()
      .sortedDescending()
      .drop(offset)
      .take(limit)
      .associateWith { persons[it]!! }
      .toMap()
  }

  fun delete(id: Int): Boolean {
    return persons.remove(id) != null
  }
}

fun Application.personModule() {
  install(ContentNegotiation) {
    jackson()
  }

  routing {
    route("/persons") {
      get {
        val limit = call.parameters["limit"]?.toInt() ?: 10
        val offset = call.parameters["offset"]?.toInt() ?: 0
        call.respond(PersonStore.list(offset, limit))
      }

      post {
        val person = call.receive<Person>()
        val id = PersonStore.save(person)
        call.response.status(HttpStatusCode.Created)
        call.response.header(HttpHeaders.Location, "/persons/$id")
        call.respond(person)
      }

      get("/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val person = PersonStore.find(id)
        if (person == null) {
          call.respondNotFound()
        } else {
          call.respond(person)
        }
      }

      patch("/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val update = call.receive<UpdatePerson>()
        val originalPerson = PersonStore.find(id)
        if (originalPerson == null) {
          call.respondNotFound()
        } else {
          val updated = Person(
            name = update.name ?: originalPerson.name,
            age = update.age ?: originalPerson.age,
            city = update.city ?: originalPerson.city
          )

          PersonStore.save(updated, id)

          call.respond(updated)
        }
      }

      delete("/{id}") {
        val id = call.parameters["id"]!!.toInt()
        if (!PersonStore.delete(id)) {
          call.respondNotFound()
        } else {
          call.respondText(
            status = HttpStatusCode.OK,
            text = "OK"
          )
        }
      }
    }
  }
}

suspend fun ApplicationCall.respondNotFound() {
  respondText(
    status = HttpStatusCode.NotFound,
    text = "Not found!"
  )
}
