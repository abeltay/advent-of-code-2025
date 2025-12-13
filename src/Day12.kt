fun main() {
    fun parseShapes(input: List<String>): Pair<List<Int>, Int> {
        var i = 0
        val shapes = mutableListOf(0, 0, 0, 0, 0, 0)

        for (j in shapes.indices) {
            var stage = 0
            while (input[i].isNotEmpty()) {
                val inp = input[i]
                if (stage == 0) {
                    stage++
                } else {
                    shapes[j] += inp.count { it == '#' }
                }
                i++
            }
            i++
        }

        return Pair(shapes, i)
    }

    fun part1(input: List<String>): Int {
        val parsed = parseShapes(input)
        var i = parsed.second
        var answer = 0
        while (i < input.size) {
            val line = input[i]
            val split1 = line.split(": ")
            val multipliers = split1[0].split("x")
            val max = multipliers[0].toInt() * multipliers[1].toInt()
            val shapes = split1[1].split(" ")
            val areas = parsed.first.mapIndexed { index, value -> shapes[index].toInt() * value }
            if (areas.sum() < max * 0.8) {
                answer++
            }
            i++
        }
        return answer
    }

    // val testInput = readInput("Day12_test")
    // check(part1(testInput) == 2)

    val input = readInput("Day12")
    println(part1(input))
}
