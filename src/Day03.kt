fun main() {
    fun maxJoltage(line: String): Long {
        var largest: Long = 0
        for (i in 0 until line.length) {
            for (j in i + 1 until line.length) {
                val newNum = (line[i].toString() + line[j].toString()).toLong()
                if (largest < newNum) {
                    largest = newNum
                }
            }
        }
        return largest
    }

    fun part1(input: List<String>): Long {
        var answer: Long = 0
        for (line in input) {
            answer += maxJoltage(line)
        }
        return answer
    }

    fun key(line: String, pick: Int): String {
        return "$line,$pick"
    }

    fun pickX(line: String, pick: Int, cache: MutableMap<String, Long>): Long {
        val found = cache.getOrDefault(key(line, pick), -1)
        if (found != (-1).toLong()) {
            return found
        }
        var largest: Long = 0
        if (pick == 1) {
            for (i in 0 until line.length) {
                if (largest < line[i].toString().toLong()) {
                    largest = line[i].toString().toLong()
                }
            }
        } else {
            for (i in 0 until line.length - pick+1) {
                val num = pickX(line.substring(i + 1), pick - 1, cache)
                val newNum = (line[i].toString() + num.toString()).toLong()
                if (newNum > largest) {
                    largest = newNum
                }
            }
        }
        cache[key(line, pick)] = largest
        return largest
    }

    fun part2(input: List<String>): Long {
        var answer: Long = 0
        for (line in input) {
            val cache = mutableMapOf<String, Long>()
            val result = pickX(line, 12, cache)
            answer += result
        }
        return answer
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357.toLong())
    check(part2(testInput) == 3121910778619)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
