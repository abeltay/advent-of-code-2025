fun main() {
    fun parseInput(input: List<String>): Pair<List<List<Int>>, List<String>> {
        val inputLineRegex = "\\s+".toRegex()

        val numbers = mutableListOf<List<Int>>()
        var symbol = listOf<String>()
        for (it in input) {
            if (it[0] != '*') {
                val line = it.trim().split(inputLineRegex)
                val ints = line.map { it.toInt() }
                numbers.add(ints)
                continue
            }
            symbol = it.split(inputLineRegex)
        }
        return Pair(numbers, symbol)
    }

    fun calculate(numbers: List<List<Int>>, symbol: List<String>, ptr: Int): Long {
        var answer: Long = 0
        for (i in numbers.indices) {
            if (i == 0) {
                answer = numbers[i][ptr].toLong()
                continue
            }
            if (symbol[ptr] == "*") {
                answer *= numbers[i][ptr].toLong()
            } else {
                answer += numbers[i][ptr].toLong()
            }
        }
        return answer
    }

    fun part1(input: List<String>): Long {
        val parsed = parseInput(input)
        var answer: Long = 0
        for (i in parsed.first.first().indices) {
            answer += calculate(parsed.first, parsed.second, i)
        }
        return answer
    }

    fun findNext(last: List<Char>, from: Int): Int {
        var ptr = from + 1
        while (ptr < last.size && last[ptr] == ' ') {
            ptr++
        }
        if (ptr != last.size) {
            ptr--
        }
        return ptr
    }

    fun formNumbers(input: List<String>, from: Int, to: Int): List<Long> {
        val numbers = mutableListOf<Long>()
        for (i in from until to) {
            var nextNum = ""
            for (j in input.indices) {
                if (j == input.size - 1) {
                    break
                }
                nextNum += input[j][i]
            }
            numbers.add(nextNum.trim().toLong())
        }
        return numbers
    }

    fun calculate(num: List<Long>, symbol: String): Long {
        return if (symbol == "*") {
            var answer: Long = 1
            num.forEach { answer *= it }
            answer
        } else {
            num.sum()
        }
    }

    fun part2(input: List<String>): Long {
        val symbols = input[input.size - 1].toMutableList()
        while (symbols.size < input[0].length) {
            symbols.add(' ') // Pad the input back to original values
        }
        var ptr = 0
        var answer: Long = 0
        while (ptr < input[0].length) {
            val next = findNext(symbols, ptr)
            val num = formNumbers(input, ptr, next)
            answer += calculate(num, input[input.size - 1][ptr].toString())
            ptr = next + 1
        }
        return answer
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556.toLong())
    check(part2(testInput) == 3263827.toLong())

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
