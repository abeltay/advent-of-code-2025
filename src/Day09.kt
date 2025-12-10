import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToLong

fun main() {
    fun parseInput(input: List<String>): List<Pair<Int, Int>> {
        return input.map {
            val split = it.split(',')
            Pair(split[0].toInt(), split[1].toInt())
        }
    }

    fun euclideanDistance(from: Pair<Int, Int>, to: Pair<Int, Int>): Long {
        val one = (from.first.toDouble() - to.first.toDouble()).pow(2).roundToLong()
        val two = (from.second.toDouble() - to.second.toDouble()).pow(2).roundToLong()
        return one + two
    }

    fun absoluteDiff(i: Int, j: Int): Int {
        return if (i > j) {
            i - j + 1
        } else {
            j - i + 1
        }
    }

    fun area(a: Pair<Int, Int>, b: Pair<Int, Int>): Long {
        val length = absoluteDiff(a.first, b.first)
        val height = absoluteDiff(a.second, b.second)
        return length.toLong() * height.toLong()
    }

    fun part1(input: List<String>): Long {
        val parsed = parseInput(input)
        var distance: Long = 0
        var answer: Long = 0
        for (i in parsed.indices) {
            for (j in i + 1 until parsed.size) {
                val dist = euclideanDistance(parsed[i], parsed[j])
                if (dist > distance) {
                    distance = dist
                    answer = area(parsed[i], parsed[j])
                }
            }
        }
        return answer
    }

    fun isValid(data: List<Pair<Int, Int>>, top: Int, bottom: Int, left: Int, right: Int): Boolean {
        // A path can be filled out for part 2 under these two conditions
        // > There mustn't be any dots inside the square you're looking at (not counting border indexes)
        // > There mustn't be any lines that partially or fully intersect the area
        for (i in data) {
            if (i.first in (left + 1)..<right &&
                i.second in (top + 1)..<bottom
            ) {
                return false
            }
        }
        var prev = data[data.size - 1]
        for (i in 0 until data.size) {
            val cur = data[i]
            val aLeft = min(cur.first, prev.first)
            val aRight = max(cur.first, prev.first)
            val aTop = min(cur.second, prev.second)
            val aBottom = max(cur.second, prev.second)

            val AisToTheRightOfB = aLeft > right - 1
            val AisToTheLeftOfB = aRight < left + 1
            val AisAboveB = aBottom < top + 1
            val AisBelowB = aTop > bottom - 1
            if (!(AisToTheRightOfB
                        || AisToTheLeftOfB
                        || AisAboveB
                        || AisBelowB)
            ) {
                return false
            }
            prev = cur
        }
        return true
    }

    fun part2(input: List<String>): Long {
        val data = parseInput(input)
        var answer: Long = 0
        for (i in data.indices) {
            for (j in i + 1 until data.size) {
                val cur = data[i]
                val next = data[j]
                if (cur.first == next.first || cur.second == next.second) {
                    // not a diagonal
                    continue
                }
                val area = area(cur, next)
                if (area <= answer) {
                    continue
                }
                val left = min(cur.first, next.first)
                val right = max(cur.first, next.first)
                val top = min(cur.second, next.second)
                val bottom = max(cur.second, next.second)
                if (isValid(data, top, bottom, left, right)) {
                    answer = area
                }
            }
        }
        return answer
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 50.toLong())
    check(part2(testInput) == 24.toLong())

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
