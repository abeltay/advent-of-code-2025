fun main() {
    fun parseInput(input: List<String>): Pair<List<Pair<Long, Long>>, List<Long>> {
        val inputLineRegex = """(\d+)-(\d+)""".toRegex()

        val pairs = mutableListOf<Pair<Long, Long>>()
        var i = 0
        while (input[i] != "") {
            val (start, end) = inputLineRegex
                .matchEntire(input[i])
                ?.destructured
                ?: throw IllegalArgumentException("Incorrect input line $(input[i])")
            pairs.add(Pair(start.toLong(), end.toLong()))
            i++
        }

        i++
        val num = mutableListOf<Long>()
        while (i < input.size) {
            num.add(input[i].toLong())
            i++
        }

        return Pair(pairs, num)
    }

    fun findValid(sortedRange: List<Pair<Long, Long>>, sortedValues: List<Long>): Int {
        var p1 = 0
        var p2 = 0
        var answer = 0
        while (p1 < sortedRange.size && p2 < sortedValues.size) {
            if (sortedRange[p1].first > sortedValues[p2]) {
                p2++
                continue
            }
            if (sortedRange[p1].second < sortedValues[p2]) {
                p1++
                continue
            }
            if (sortedRange[p1].first <= sortedValues[p2] && sortedRange[p1].second >= sortedValues[p2]) {
                answer++
                p2++
            }
        }
        return answer
    }

    fun part1(input: List<String>): Int {
        val parsed = parseInput(input)
        val sortedRange = parsed.first.sortedBy { it.first }
        val sortedValues = parsed.second.sorted()
        val answer = findValid(sortedRange, sortedValues)
        return answer
    }

    fun findAll(sortedRange: List<Pair<Long, Long>>): Long {
        var low = sortedRange[0].first
        var answer: Long = 1
        for (i in sortedRange) {
            if (low < i.first) {
                low = i.first
                answer++
            }
            if (i.second < low) {
                continue
            }
            answer += i.second - low
            low = i.second
        }
        return answer
    }

    fun part2(input: List<String>): Long {
        val parsed = parseInput(input)
        val sortedRange = parsed.first.sortedBy { it.first }
        val answer = findAll(sortedRange)
        return answer
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 14.toLong())

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
