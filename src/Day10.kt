fun main() {
    data class Input(
        val endState: Int,
        val buttons: List<List<Int>>,
        val joltage: List<Int>,
    )

    fun parseInput(input: List<String>): List<Input> {
        val parsed = mutableListOf<Input>()
        for (i in input) {
            var endState = ""
            val buttons: MutableList<List<Int>> = mutableListOf()
            var joltage: List<Int> = listOf()
            var stage = 0
            val values = i.split(' ')
            for (v in values) {
                if (v.first() == '{') {
                    stage = 2
                }
                val content = v.substring(1, v.length - 1)
                when (stage) {
                    0 -> {
                        content.forEach {
                            endState += if (it == '#') {
                                "1"
                            } else {
                                "0"
                            }
                        }
                        stage++
                    }

                    1 -> {
                        buttons.add(content.split(',').map { it.toInt() })
                    }

                    else -> {
                        joltage = content.split(',').map { it.toInt() }
                    }
                }
            }
            parsed.add(Input(endState.toInt(2), buttons, joltage))
        }
        return parsed
    }

    fun fewestButtonPresses(input: Input): Long {
        var presses: Long = 0
        var states = listOf(0)
        val buttons = mutableListOf<Int>()
        for (i in input.buttons) {
            val button = MutableList(input.joltage.size) { 0 }
            i.forEach { button[it] = 1 }
            buttons.add(button.joinToString("").toInt(2))
        }

        while (states.isNotEmpty()) {
            val next = mutableListOf<Int>()
            presses++
            for (state in states) {
                val diff = input.endState xor state
                if (buttons.contains(diff)) {
                    return presses
                }
                val binary = diff.toString(2)
                var mask = ""
                var found = false
                for (i in binary) {
                    if (i == '1' && !found) {
                        found = true
                        mask += "1"
                    } else {
                        mask += '0'
                    }
                }
                val maskInt = mask.toInt(2)
                for (i in buttons) {
                    if (i and maskInt > 0) {
                        next.add(i xor state)
                    }
                }
            }
            states = next
        }
        return 0
    }

    fun part1(input: List<String>): Long {
        val parsed = parseInput(input)
        var answer: Long = 0
        for (i in parsed) {
            answer += fewestButtonPresses(i)
        }
        return answer
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 7.toLong())

    val input = readInput("Day10")
    println(part1(input))
}
