package day03

import println
import readInput

fun main() {
    fun findInstructions(memory: List<String>, regex: Regex): List<String> {
        return memory.flatMap { string -> regex.findAll(string).map { it.value } }
    }

    fun calcInstruction(instruction: String): Int {
        val (a, b) = instruction
            .removeSurrounding("mul(", ")").split(",")

        return a.toInt() * b.toInt()
    }

    fun part1(input: List<String>): Int {
        val regex = """mul\(\d{1,3},\d{1,3}\)""".toRegex()
        val all = findInstructions(input, regex)
        return all.sumOf { instruction -> calcInstruction(instruction) }
    }

    fun part2(input: List<String>): Int {
        val regex = """mul\(\d{1,3},\d{1,3}\)|do\(\)|don't\(\)""".toRegex()
        val all = findInstructions(input, regex)
        var enabled = true
        var acc = 0

        for (instruction in all) {
            when {
                instruction == "do()" -> enabled = true
                instruction == "don't()" -> enabled = false
                enabled && instruction.startsWith("mul(") -> {
                    acc += calcInstruction(instruction)
                }
            }
        }

        return acc
    }

    val input = readInput("day03")

    part1(input).println()
    part2(input).println()
}
