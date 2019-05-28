package guru.drako.examples.fizzbuzz

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import java.util.concurrent.Executors

fun fib(): Sequence<Int> = sequence {
  var a = 1
  var b = 1
  while (true) {
    yield(b)
    a += b.also { b = a }
  }
}

fun CoroutineScope.fib2() = produce {
  send(42)
}

fun main() {

  runBlocking {
    fib2()
  }

  val singleThread = Executors.newFixedThreadPool(1).asCoroutineDispatcher()

  runBlocking(singleThread) {

    val chan = Channel<Int>()

    launch {
      for (n in 23..42) {
        delay(1000L)
        chan.send(n)
      }
      chan.close()
    }

    for (n in chan) {
      println(n)
    }
  }

  val answer = GlobalScope.async {
    delay(1000L)
    6 * 7
  }

  runBlocking {
    println("The answer is: ${answer.await()}")
  }

  Executors.newFixedThreadPool(32).asCoroutineDispatcher().use { pool ->
    runBlocking {
      val valueComesLater = async {
        withContext(pool) {
          42
        }
      }

      launch {
        (1..5).forEach {
          delay(2000L)
          println("slow: $it")
        }
      }

      println("The result was ${valueComesLater.await()}")
    }
  }


}
