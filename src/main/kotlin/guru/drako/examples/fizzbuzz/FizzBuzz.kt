package guru.drako.examples.fizzbuzz

fun fizz(n: Int): String = when {
  n isDivisibleBy 3 -> "Fizz"
  else -> ""
}

fun buzz(n: Int): String = when {
  n isDivisibleBy 5 -> "Buzz"
  else -> ""
}

fun fizzbuzz(n: Int): String {
  return (fizz(n) + buzz(n)).ifEmpty { "$n" }
}

fun Int.toStringAsFunction(): String {
  return toString()
}

fun Int.toStringAsExpansion(): String {
  return "$this"
}

fun main() {
  (1..100).forEach {
    println(fizzbuzz(it))
  }
}
