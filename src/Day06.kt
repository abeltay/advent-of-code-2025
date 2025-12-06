fun main() {
    fun padSymbols(input: String, length: Int): List<Char> {
        val symbols = input.toMutableList()
        while (symbols.size < length) {
            symbols.add(' ') // Pad the input back to original values
        }
        return symbols
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
        for (j in input.indices) {
            if (j == input.size - 1) {
                break
            }
            val nextNum = input[j].substring(from, to)
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

    fun part1(input: List<String>): Long {
        val symbols = padSymbols(input[input.size - 1], input[0].length)
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

    fun formNumbers2(input: List<String>, from: Int, to: Int): List<Long> {
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

    fun part2(input: List<String>): Long {
        val symbols = padSymbols(input[input.size - 1], input[0].length)
        var ptr = 0
        var answer: Long = 0
        while (ptr < input[0].length) {
            val next = findNext(symbols, ptr)
            val num = formNumbers2(input, ptr, next)
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
