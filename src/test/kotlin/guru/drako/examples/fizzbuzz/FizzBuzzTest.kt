package guru.drako.examples.fizzbuzz

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FizzBuzzTest {
  @ParameterizedTest
  @ValueSource(ints = [3, 6, 9, 12, 15, 99])
  fun `fizz should return Fizz for numbers divisible by 3`(n: Int) {
    assertEquals(expected = "Fizz", actual = fizz(n))
  }

  @ParameterizedTest
  @ValueSource(ints = [1, 2, 4, 5, 7, 8, 10, 100])
  fun `fizz should return nothing for numbers not divisible by 3`(n: Int) {
    assertEquals(expected = "", actual = fizz(n))
  }

  @ParameterizedTest
  @ValueSource(ints = [5, 10, 15, 25, 95, 100])
  fun `buzz should return Buzz for numbers divisible by 5`(n: Int) {
    assertEquals(expected = "Buzz", actual = buzz(n))
  }

  @ParameterizedTest
  @ValueSource(ints = [1, 2, 3, 4, 6, 7, 8, 9, 96, 97, 98, 99])
  fun `buzz should return nothing for numbers not divisible by 5`(n: Int) {
    assertEquals(expected = "", actual = buzz(n))
  }

  @ParameterizedTest
  @CsvSource(
    "1, 1", "2, 2", "3, Fizz", "4, 4", "5, Buzz", "6, Fizz", "7, 7", "8, 8", "9, Fizz", "10, Buzz",
    "11, 11", "12, Fizz", "13, 13", "14, 14", "15, FizzBuzz", "30, FizzBuzz", "90, FizzBuzz", "95, Buzz",
    "99, Fizz", "100, Buzz"
  )
  fun `fizzbuzz should return the correct results`(n: Int, expected: String) {
    assertEquals(expected = expected, actual = fizzbuzz(n))
  }
}
