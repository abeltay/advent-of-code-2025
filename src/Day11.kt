fun main() {
    fun parseInput(input: List<String>): Map<String, List<String>> {
        val nodes = mutableMapOf<String, List<String>>()
        for (it in input) {
            val first = it.split(": ")
            val second = first[1].split(" ")
            nodes[first[0]] = second
        }
        return nodes
    }

    fun findOut(nodes: Map<String, List<String>>, current: String, to: String): Int {
        if (current == to) {
            return 1
        }
        val options = nodes.getOrDefault(current, emptyList())
        var answer = 0
        for (option in options) {
            answer += findOut(nodes, option, to)
        }
        return answer
    }

    fun part1(input: List<String>): Int {
        val nodes = parseInput(input)
        return findOut(nodes, "you", "out")
    }

    fun bfs(nodes: Map<String, List<String>>, current: String, to: String, depth: Int): Int {
        var queue = mapOf(current to 1)

        var answer = 0
        for (i in 0..depth) {
            val nextQ = mutableMapOf<String, Int>()
            for (q in queue) {
                val options = nodes.getOrDefault(q.key, emptyList())
                for (option in options) {
                    if (option == to) {
                        answer += q.value
                    } else {
                        nextQ[option] = nextQ.getOrDefault(option, 0) + q.value
                    }
                }
                queue = nextQ
            }
        }
        return answer
    }

    fun path(nodes: Map<String, List<String>>, path: List<String>, depth: Int): Long {
        var answer: Long = 1
        for (i in 1..<path.size) {
            val num = bfs(nodes, path[i - 1], path[i], depth)
            answer *= num
        }
        return answer
    }

    fun part2(input: List<String>): Long {
        val nodes = parseInput(input)
        val depth = 20
        val path1 = path(nodes, listOf("svr", "fft", "dac", "out"), depth)
        val path2 = path(nodes, listOf("svr", "dac", "fft", "out"), depth)
        return path1 + path2
    }

    val testInput = readInput("Day11_test")
    check(part1(testInput) == 5)
    val testInput2 = readInput("Day11_2_test")
    check(part2(testInput2) == 2.toLong())

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
