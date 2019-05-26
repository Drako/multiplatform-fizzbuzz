package guru.drako.examples.fizzbuzz

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class MathHelpersTest {
  companion object {
    @get:[JvmStatic JvmName("getTestData")]
    val TEST_DATA = Stream.of(
        arguments(1, 1, true),
        arguments(1, 2, false),
        arguments(2, 1, true),
        arguments(2, 2, true),
        arguments(2, 3, false),
        arguments(3, 1, true),
        arguments(3, 2, false),
        arguments(3, 3, true),
        arguments(5, 3, false),
        arguments(5, 5, true),
        arguments(6, 3, true),
        arguments(10, 5, true),
        arguments(15, 3, true),
        arguments(15, 5, true)
    )
  }

  @ParameterizedTest
  @MethodSource("getTestData")
  fun `isDivisibleBy should return correct results`(dividend: Int, divisor: Int, expected: Boolean) {
    assertEquals(expected = expected, actual = dividend isDivisibleBy divisor)
  }
}
