package day05

import println
import readText

data class Rule(val before: Int, val after: Int) {

    fun test(pageNo: Int, sequence: List<Int>): Boolean {
        if (pageNo == before) {
            val index = sequence.indexOf(pageNo)

            return sequence.mapIndexed { idx, no -> Pair(idx, no) }
                .filter { (_, no) -> no == after }
                .all { (idx, _) -> idx > index }
        }

        if (pageNo == after) {
            val index = sequence.indexOf(pageNo)

            return sequence.mapIndexed { idx, no -> Pair(idx, no) }
                .filter { (_, no) -> no == before }
                .all { (idx, _) -> idx < index }
        }

        return false
    }
}

fun main() {
    fun part1(rules: List<Rule>, pages: List<List<Int>>): Int {
        return pages
            .filter { page ->
                page.all { pageNo ->
                    rules
                        .filter { rule -> rule.after == pageNo || rule.before == pageNo }
                        .all { rule -> rule.test(pageNo, page) }
                }
            }
            .sumOf { page -> page[page.size / 2] }
    }

    fun part2(): Int {
        return 0
    }

    val input = readText("day05", false)
    val rules = input.substringBefore("\n\n")
        .lines()
        .map {
            Rule(it.substringBefore("|").toInt(), it.substringAfter("|").toInt())
        }
    val pages = input.substringAfter("\n\n")
        .lines()
        .map { line ->
            line.split(",").map { it.toInt() }
        }

    part1(rules, pages).println()
    part2().println()
}
