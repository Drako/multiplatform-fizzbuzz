package guru.drako.examples.fizzbuzz

sealed class Tree {
  data class Node(val value: Int) : Tree()
  data class Branch(val left: Tree, val right: Tree): Tree()
}
