fun main() {
    fun manifold(input: List<String>): Pair<Int, List<Long>> {
        var state = emptyList<Long>()
        var answer = 0
        for (i in input.indices) {
            if (i == 0) {
                for (j in input[i].indices) {
                    if (input[i][j] == 'S') {
                        state = MutableList(input[i].length) { 0.toLong() }
                        state[j] = 1
                        break
                    }
                }
                continue
            }
            val next = MutableList(input[i].length) { 0.toLong() }
            for (j in input[i].indices) {
                if (state[j] > 0 && input[i][j] == '^') {
                    next[j - 1] += state[j]
                    next[j + 1] += state[j]
                    answer++
                } else {
                    next[j] += state[j]
                }
            }
            state = next
        }
        return Pair(answer, state)
    }

    fun part1(input: List<String>): Int {
        val answer = manifold(input)
        return answer.first
    }

    fun part2(input: List<String>): Long {
        val answer = manifold(input)
        return answer.second.sum()
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 40.toLong())

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
