package day02

import println
import readInput

fun main() {
    fun isLineSafe(numbers: List<Int>): Boolean {
        val differences = numbers.zipWithNext { a, b -> a - b }
        return differences.all { it in -3..3 } && (differences.all { it > 0 } || differences.all { it < 0 })
    }

    fun part1(input: List<List<Int>>): Int {
        return input.count { isLineSafe(it) }
    }

    fun part2(input: List<List<Int>>): Int {
        return input.count { numbers ->
            numbers.indices.any {
                val skipped = numbers.toMutableList().apply { removeAt(it) }
                isLineSafe(skipped)
            }
        }
    }

    val input = readInput("day02").map { line -> line.split(" ").map { it.toInt() } }

    part1(input).println()
    part2(input).println()
}
