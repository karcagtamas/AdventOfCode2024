package day04

import println
import readLines

data class Vec2(val x: Int, val y: Int)

data class Grid(private val elems: List<List<Char>>) {
    private val allowedDirections = listOf(
        Vec2(1, 0), // east
        Vec2(1, 1), // southeast
        Vec2(0, 1), // south
        Vec2(-1, 1), // southwest
        Vec2(-1, 0), // west
        Vec2(-1, -1), // northwest
        Vec2(0, -1), // north
        Vec2(1, -1), // northeast
    )

    val indices = sequence {
        for (y in elems[0].indices) {
            for (x in elems.indices) {
                yield(Pair(x, y))
            }
        }
    }

    private fun getAtPos(x: Int, y: Int): Char? = elems.getOrNull(y)?.getOrNull(x)

    fun countXmasWordsForPosition(startX: Int, startY: Int): Int {
        return allowedDirections.count { dir ->
            checkXmasWordForDirection(startX, startY, dir)
        }
    }

    private fun checkXmasWordForDirection(startX: Int, startY: Int, dir: Vec2): Boolean {
        var runningX = startX
        var runningY = startY

        for (letter in listOf('X', 'M', 'A', 'S')) {
            if (getAtPos(runningX, runningY) != letter) {
                return false
            }
            runningX += dir.x
            runningY += dir.y
        }

        return true
    }

    fun isMASCrossAtPosition(centerX: Int, centerY: Int): Boolean {
        if (getAtPos(centerX, centerY) != 'A') {
            return false
        }

        val isFallingDiagonalMAS = getAtPos(centerX - 1, centerY - 1) == 'M' && getAtPos(centerX + 1, centerY + 1) == 'S'
        val isFallingDiagonalSAM = getAtPos(centerX - 1, centerY - 1) == 'S' && getAtPos(centerX + 1, centerY + 1) == 'M'
        val isFallingDiagonalOk = isFallingDiagonalMAS || isFallingDiagonalSAM

        val isRisingDiagonalMAS = getAtPos(centerX - 1, centerY + 1) == 'M' && getAtPos(centerX + 1, centerY - 1) == 'S'
        val isRisingDiagonalSAM = getAtPos(centerX - 1, centerY + 1) == 'S' && getAtPos(centerX + 1, centerY - 1) == 'M'
        val isRisingDiagonalOk = isRisingDiagonalMAS || isRisingDiagonalSAM

        return  isFallingDiagonalOk && isRisingDiagonalOk
    }
}

fun main() {
    fun part1(grid: Grid): Int {
        var xmasWordCount = 0
        for ((startX, startY) in grid.indices) {
            xmasWordCount += grid.countXmasWordsForPosition(startX, startY)
        }
        return xmasWordCount
    }

    fun part2(grid: Grid): Int {
        var crossCount = 0
        for ((centerX, centerY) in grid.indices) {
            crossCount += if (grid.isMASCrossAtPosition(centerX, centerY)) 1 else 0
        }
        return crossCount
    }

    val input = readLines("day04")
    val grid = Grid(input.map { it.toCharArray().toList() })

    part1(grid).println()
    part2(grid).println()
}
