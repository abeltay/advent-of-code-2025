fun main() {
    fun parseInput(input: List<String>): List<String> {
        val inputLineRegex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

        for (it in input) {
            val (startX, startY, endX, endY) = inputLineRegex
                .matchEntire(it)
                ?.destructured
                ?: throw IllegalArgumentException("Incorrect input line $it")
        }

        return input
    }

    fun part1(input: List<String>): Long {
        val parsed = parseInput(input)
        println(parsed)
        var answer: Long = 0
        println(answer)
        return answer
    }

    fun part2(input: List<String>): Long {
        return 0
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 0.toLong())
    check(part2(testInput) == 0.toLong())

    val input = readInput("Day01")
    println(part1(input))
    // println(part2(input))
}
