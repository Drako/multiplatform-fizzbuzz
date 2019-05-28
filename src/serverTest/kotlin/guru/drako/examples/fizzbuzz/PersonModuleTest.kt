package guru.drako.examples.fizzbuzz

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationRequest
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PersonModuleTest {
  private val objectMapper = ObjectMapper().apply {
    registerKotlinModule()
  }

  /**
   * Convert [obj] to JSON and put it in request body. Also set Content-Type.
   *
   * @param obj The object to be sent.
   * @receiver The current request.
   */
  private fun TestApplicationRequest.setBodyJson(obj: Any) {
    setBody(objectMapper.writeValueAsBytes(obj))
    addHeader(HttpHeaders.ContentType, "${ContentType.Application.Json}")
  }

  @BeforeTest
  fun setup() {
    PersonStore::class.java
      .getDeclaredField("nextId")
      .apply {
        isAccessible = true
      }
      .set(PersonStore, 1)

    val persons = PersonStore::class.java
      .getDeclaredField("persons")
      .apply {
        isAccessible = true
      }
      .get(PersonStore) as MutableMap<*, *>
    persons.clear()
  }

  @Test
  fun `create new question`() {
    withTestApplication({
      personModule()
    }) {
      val testPerson = Person(
        name = "Felix Bytow",
        age = 28,
        city = "Chemnitz"
      )

      val expectedLocation = "/persons/1"

      val receivedPerson = handleRequest(HttpMethod.Post, "/persons") {
        setBodyJson(testPerson)
      }.run {
        assertEquals(expected = HttpStatusCode.Created, actual = response.status())
        assertEquals(expected = expectedLocation, actual = response.headers["Location"])
        objectMapper.readValue<Person>(response.content!!)
      }

      assertEquals(expected = testPerson, actual = receivedPerson)

      handleRequest(HttpMethod.Get, expectedLocation).apply {
        assertEquals(expected = HttpStatusCode.OK, actual = response.status())
        val person = objectMapper.readValue<Person>(response.content!!)
        assertEquals(expected = testPerson, actual = person)
      }
    }
  }

  @Test
  fun `get non existent question`() {
    withTestApplication({
      personModule()
    }) {
      handleRequest(HttpMethod.Get, "/persons/1").apply {
        assertEquals(expected = HttpStatusCode.NotFound, actual = response.status())
        assertEquals(expected = "Not found!", actual = response.content)
      }
    }
  }
}
