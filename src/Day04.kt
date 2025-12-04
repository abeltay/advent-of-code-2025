fun main() {
    fun parseInput(input: List<String>): List<MutableList<Char>> {
        return input.map { str ->
            str.toMutableList()
        }
    }

    fun countSurrounding(floor: List<List<Char>>, y: Int, x: Int): Int {
        var count = 0
        for (ny in y - 1..y + 1) {
            for (nx in x - 1..x + 1) {
                if (ny == y && nx == x) {
                    continue
                }
                if (ny < 0 || ny >= floor.size || nx < 0 || nx >= floor[0].size) {
                    continue
                }
                if (floor[ny][nx] == '@') {
                    count++
                }
            }
        }
        return count
    }

    fun canRemove(floor: List<List<Char>>, y: Int, x: Int): Boolean {
        if (floor[y][x] != '@') {
            return false
        }
        val s = countSurrounding(floor, y, x)
        return s < 4
    }

    fun part1(input: List<String>): Int {
        val parsed = parseInput(input)
        var answer = 0
        for (y in 0 until parsed.size) {
            for (x in 0 until parsed[y].size) {
                val remove = canRemove(parsed, y, x)
                if (remove) {
                    answer++
                }
            }
        }
        return answer
    }

    fun part2(input: List<String>): Int {
        val parsed = parseInput(input)
        var answer = 0
        do {
            var changed = false
            for (y in 0 until parsed.size) {
                for (x in 0 until parsed[y].size) {
                    val remove = canRemove(parsed, y, x)
                    if (remove) {
                        parsed[y][x] = 'x'
                        answer++
                        changed = true
                    }
                }
            }
        } while (changed)
        return answer
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}