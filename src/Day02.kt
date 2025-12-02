fun main() {
    fun parseInput(input: List<String>): List<Pair<Long, Long>> {
        val ranges = input[0].split(',')
        return ranges.map {
            val new = it.split('-')
            Pair(new[0].toLong(), new[1].toLong())
        }
    }

    fun nextH1(num: Long): Long {
        val strNum = num.toString()
        var h1 = if (strNum.length % 2 == 0) {
            strNum.take(strNum.length / 2).toLong()
        } else {
            val len = (strNum.length / 2) + 1
            var str = "1"
            while (str.length < len) {
                str += "0"
            }
            str.toLong()
        }
        val next = (h1.toString() + h1.toString()).toLong()
        if (next < num) {
            h1++
        }
        return h1
    }

    fun totalInvalid(range: Pair<Long, Long>): Long {
        var answer: Long = 0
        var h1 = nextH1(range.first - 1)
        var next = (h1.toString() + h1.toString()).toLong()
        while (next <= range.second) {
            answer += next
            h1++
            next = (h1.toString() + h1.toString()).toLong()
        }
        return answer
    }

    fun part1(input: List<String>): Long {
        val nextInput = parseInput(input)
        var answer: Long = 0
        nextInput.forEach {
            val invalid = totalInvalid(it)
            answer += invalid
        }
        return answer
    }

    fun isInvalid(num: Long): Boolean {
        val strNum = num.toString()
        var len = 1
        while (len <= strNum.length / 2) {
            var i = len
            var failed = false
            while (i < strNum.length) {
                if (i + len > strNum.length || strNum.take(len) != strNum.substring(i, i + len)) {
                    failed = true
                    break
                }
                i += len
            }
            if (!failed) {
                return true
            }
            len++
        }
        return false
    }

    fun checkInvalid(range: Pair<Long, Long>): Long {
        var current = range.first
        var answer: Long = 0
        while (current <= range.second) {
            if (isInvalid(current)) {
                answer += current
            }
            current++
        }
        return answer
    }

    fun part2(input: List<String>): Long {
        val nextInput = parseInput(input)
        var answer: Long = 0
        nextInput.forEach {
            val invalid = checkInvalid(it)
            answer += invalid
        }
        return answer
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554.toLong())
    check(part2(testInput) == 4174379265.toLong())

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
