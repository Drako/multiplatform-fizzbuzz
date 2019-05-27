package guru.drako.examples.fizzbuzz

import kotlin.test.Test
import kotlin.test.assertEquals

class MathHelpersTest {
  data class TestData(val dividend: Int, val divisor: Int, val isDivisible: Boolean)

  @Test
  fun `isDivisibleBy should return correct results`() {
    sequenceOf(
      TestData(1, 1, true),
      TestData(1, 2, false),
      TestData(2, 2, true),
      TestData(2, 3, false),
      TestData(3, 1, true),
      TestData(3, 2, false),
      TestData(3, 3, true),
      TestData(5, 3, false),
      TestData(5, 5, true),
      TestData(6, 3, true),
      TestData(10, 5, true),
      TestData(15, 3, true),
      TestData(15, 5, true)
    ).forEach { (dividend, divisor, isDivisible) ->
      assertEquals(expected = isDivisible, actual = dividend isDivisibleBy divisor)
    }
  }
}
