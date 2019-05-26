package guru.drako.examples.fizzbuzz

/**
 * Throw an exception (and look cool while doing so).
 *
 * @param generator A function generating an exception to be thrown.
 */
inline fun `(╯°□°）╯︵`(generator: () -> Throwable): Nothing {
  throw generator()
}
