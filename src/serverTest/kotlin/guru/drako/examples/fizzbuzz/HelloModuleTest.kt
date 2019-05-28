package guru.drako.examples.fizzbuzz

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class HelloModuleTest {
  @Test
  fun `index should return hello world page`() {
    withTestApplication({
      helloModule()
    }) {
      handleRequest(HttpMethod.Get, "/").apply {
        assertEquals(
          expected = HttpStatusCode.OK,
          actual = response.status()
        )

        val expectedContent = """
          <!DOCTYPE html>
          <html>
            <body>
              <p>Hello world!</p>
            </body>
          </html>

          """.trimIndent()

        assertEquals(
          expected = expectedContent,
          actual = response.content
        )
      }
    }
  }
}
