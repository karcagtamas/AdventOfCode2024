package day01

import println
import readInput
import kotlin.math.abs

fun main() {
    fun part1(firstColumn: List<Int>, secondColumn: List<Int>): Int {
        val sortedColumnsPairs = firstColumn.sorted().zip(secondColumn.sorted())
        val distances = sortedColumnsPairs.sumOf { (first, second) ->
            abs(first - second)
        }

        return distances
    }

    fun part2(firstColumn: List<Int>, secondColumn: List<Int>): Int {
        val counts = secondColumn.groupingBy { it }.eachCount()
        val sum = firstColumn.sumOf { first ->
            first * (counts[first] ?: 0)
        }
        return sum
    }

    val (first, second) = readInput("day01", false)
        .map { line ->
            val (a, b) = line.split("   ")
            a.toInt() to b.toInt()
        }.unzip()

    part1(first, second).println()
    part2(first, second).println()
}
