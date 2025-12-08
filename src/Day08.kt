import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    data class Euclidean(
        val distance: Long,
        val from: String,
        val to: String,
    )

    fun euclideanDistance(in1: String, in2: String): Long {
        val first = in1.split(',').map { it.toDouble() }
        val second = in2.split(',').map { it.toDouble() }
        val dist =
            sqrt(
                (first[0] - second[0]).pow(2) +
                        ((first[1] - second[1]).pow(2)) +
                        ((first[2] - second[2]).pow(2))
            )
        return dist.toLong()
    }

    fun createRelations(input: List<String>): List<Euclidean> {
        val all = mutableListOf<Euclidean>()
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                all.add(
                    Euclidean(
                        distance = euclideanDistance(input[i], input[j]),
                        from = input[i],
                        to = input[j],
                    )
                )
            }
        }
        return all.sortedBy { it.distance }
    }

    fun part1(input: List<String>, connect: Int): Int {
        val list = createRelations(input).toMutableList()
        var groups = listOf<MutableSet<String>>()
        for (i in 1..connect) {
            val first = list.removeFirst()
            val nextGroup = mutableListOf<MutableSet<String>>()
            val nextSet = mutableSetOf<String>()
            for (next in groups) {
                if (next.contains(first.from) || next.contains(first.to)) {
                    nextSet.addAll(next)
                    nextSet.add(first.from)
                    nextSet.add(first.to)
                } else {
                    nextGroup.add(next)
                }
            }
            if (nextSet.isEmpty()) {
                nextSet.add(first.from)
                nextSet.add(first.to)
            }
            nextGroup.add(nextSet)
            groups = nextGroup
        }
        groups = groups.sortedByDescending { it.size }
        var answer = 1
        for (i in 0 until 3) {
            answer *= groups[i].size
        }
        return answer
    }

    fun part2(input: List<String>): Long {
        val list = createRelations(input).toMutableList()
        var groups = listOf<MutableSet<String>>()
        var lastPair: Pair<String, String> = Pair("", "")
        while (list.isNotEmpty()) {
            val first = list.removeFirst()
            val nextGroup = mutableListOf<MutableSet<String>>()
            val nextSet = mutableSetOf<String>()
            for (next in groups) {
                if (!(next.contains(first.from) && next.contains(first.to))){
                    lastPair = Pair(first.from, first.to)
                }
                if (next.contains(first.from) || next.contains(first.to)) {
                    nextSet.addAll(next)
                    nextSet.add(first.from)
                    nextSet.add(first.to)
                } else {
                    nextGroup.add(next)
                }
            }
            if (nextSet.isEmpty()) {
                nextSet.add(first.from)
                nextSet.add(first.to)
            }
            nextGroup.add(nextSet)
            groups = nextGroup
        }
        return lastPair.first.split(',')[0].toLong() * lastPair.second.split(',')[0].toLong()
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput, 10) == 40)
    check(part2(testInput) == 25272.toLong())

    val input = readInput("Day08")
    println(part1(input, 1000))
    println(part2(input))
}
