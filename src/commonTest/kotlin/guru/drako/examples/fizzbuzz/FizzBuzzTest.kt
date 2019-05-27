package guru.drako.examples.fizzbuzz

import kotlin.test.Test
import kotlin.test.assertEquals

class FizzBuzzTest {
  @Test
  fun `fizz should return Fizz for numbers divisible by 3`() {
    sequenceOf(3, 6, 9, 12, 15, 99).forEach {
      assertEquals(expected = "Fizz", actual = fizz(it))
    }
  }

  @Test
  fun `fizz should return nothing for numbers not divisible by 3`() {
    sequenceOf(1, 2, 4, 5, 7, 8, 10, 100).forEach {
      assertEquals(expected = "", actual = fizz(it))

    }
  }

  @Test
  fun `buzz should return Buzz for numbers divisible by 5`() {
    sequenceOf(5, 10, 15, 25, 95, 100).forEach {
      assertEquals(expected = "Buzz", actual = buzz(it))
    }
  }

  @Test
  fun `buzz should return nothing for numbers not divisible by 5`() {
    sequenceOf(1, 2, 3, 4, 6, 7, 8, 9, 96, 97, 98, 99).forEach {
      assertEquals(expected = "", actual = buzz(it))

    }
  }

  @Test
  fun `fizzbuzz should return the correct results`() {
    sequenceOf(
      1 to "1", 2 to "2", 3 to "Fizz", 4 to "4", 5 to "Buzz",
      6 to "Fizz", 7 to "7", 8 to "8", 9 to "Fizz", 10 to "Buzz",
      11 to "11", 12 to "Fizz", 13 to "13", 14 to "14", 15 to "FizzBuzz",
      30 to "FizzBuzz", 90 to "FizzBuzz", 95 to "Buzz", 99 to "Fizz", 100 to "Buzz"
    ).forEach { (n, expected) ->
      assertEquals(expected = expected, actual = fizzbuzz(n), message = "$n should become $expected")
    }
  }
}
