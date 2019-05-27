package guru.drako.examples.fizzbuzz

import platform.windows.MB_ICONINFORMATION
import platform.windows.MB_OK
import platform.windows.MessageBoxW

fun main() {
  val output = (1..100).chunked(10).joinToString(separator = "\n") { chunk ->
    chunk.joinToString { fizzbuzz(it) }
  }

  @Suppress("EXPERIMENTAL_API_USAGE")
  MessageBoxW(null, output, "FizzBuzz", (MB_OK or MB_ICONINFORMATION).toUInt())
}
