fun main() {
    fun spin(current: Int, instruction: String): Pair<Int, Int> {
        val max = 100
        var next = current
        var moves = instruction.substring(1).toInt()
        var rounds = moves / max
        moves %= max
        for (i in 1..moves) {
            if (instruction.first() == 'L') {
                next--
                if (next < 0) {
                    next += max
                }
            } else {
                next++
                if (next >= max) {
                    next -= max
                }
            }
            if (next == 0) {
                rounds++
            }
        }
        return Pair(next, rounds)
    }

    fun part1(input: List<String>): Int {
        var current = 50
        var ans = 0
        input.forEach {
            val next = spin(current, it)
            current = next.first
            if (current == 0) {
                ans++
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var current = 50
        var ans = 0
        input.forEach {
            val next = spin(current, it)
            current = next.first
            ans += next.second
        }
        return ans
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
