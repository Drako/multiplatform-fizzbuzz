package guru.drako.examples.fizzbuzz

/**
 * Determine whether a number can be divided by another without a remainder.
 *
 * @receiver The left hand side operand (dividend).
 * @param other The right hand side operand (divisor).
 * @return Whether the dividend is divisible by the divisor.
 */
infix fun Int.isDivisibleBy(other: Int) = this % other == 0
