package guru.drako.examples.fizzbuzz

import kotlinx.coroutines.*
import kotlinx.coroutines.time.delay
import java.time.Duration
import java.util.*

data class RationalNumber @JvmOverloads constructor(
  val numerator: Int,
  val denominator: Int = 1
)

/*
  public final class Point {
    private final int x;
    private final int y;

    public Point(final int x, final int y) {
      this.x = x;
      this.y = y;
    }

    int getX() {
      return x;
    }

    int getY() {
      return y;
    }

    public int component1() { return x; }
    public int component2() { return y; }
  }
 */

@DslMarker
annotation class HtmlDsl

@HtmlDsl
abstract class AbstractHtmlBuilder {
  protected val elements = mutableListOf<HtmlTag>()

  fun build(name: String, attributes: Map<String, String>): HtmlTag {
    return HtmlTag(
      name = name,
      attributes = attributes,
      children = elements
    )
  }
}

class HtmlTag(
  val name: String,
  val attributes: Map<String, String> = mapOf(),
  val children: List<HtmlTag> = listOf()
)

class HtmlBuilder : AbstractHtmlBuilder() {
  fun table(
    vararg attributes: Pair<String, String>,
    block: HtmlTableBuilder.() -> Unit
  ) {
    elements.add(HtmlTableBuilder().apply(block).build("table", mapOf(*attributes)))
  }

  fun p(
    vararg attributes: Pair<String, String>,
    block: HtmlBuilder.() -> Unit
  ) {
    elements.add(HtmlBuilder().apply(block).build("p", mapOf(*attributes)))
  }
}

class HtmlTableBuilder : AbstractHtmlBuilder() {

}

fun buildHtml(builder: HtmlBuilder.() -> Unit): HtmlTag {
  return HtmlBuilder().apply(builder).build("html", mapOf())
}

fun main() {
  val html = buildHtml {
    p {
      p("id" to "23") {
        table {
        }
      }
    }
  }
}
