import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (left, right) = input.map { line ->
            val first = line.substringBefore(" ").toInt()
            val second = line.substringAfterLast(" ").toInt()
            first to second
        }.unzip()

        val result = left.sorted().zip(right.sorted()).sumOf { (first, second) ->
            abs(first - second)
        }

        return result
    }

    fun part2(input: List<String>): Long {
        // Another solution to part1
        val (left, right) = input.map { line ->
            line.split(Regex("\\s+")).let {
                require(it.size == 2)
                it[0].toLong() to it[1].toLong()
            }
        }.unzip()

        val frequences = right.groupingBy { it }.eachCount()
        return left.fold(0) { acc, num ->
            acc + num * frequences.getOrDefault(num, 0)
        }
    }

    val lines = readInput("day01", false)

    part1(lines).println()
    part2(lines).println()
}
