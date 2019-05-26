package guru.drako.examples.fizzbuzz

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ExceptionHelpersTest {
  @Test
  fun `throwing exceptions in style should work`() {
    val exceptionText = "My hair is a bird, your argument is invalid!"

    val ex = assertThrows<IllegalArgumentException> {
      `(╯°□°）╯︵` { IllegalArgumentException(exceptionText) }
    }
    assertEquals(expected = ex.message, actual = exceptionText)
  }
}
