package main

import (
	"fmt"
	"os"

	"github.com/abeltay/advent-of-code-2025/utilities"
)

func parseInput(filename string) [][]byte {
	in := utilities.ParseFile(filename)

	var arr [][]byte
	for _, row := range in {
		arr = append(arr, []byte(row))
	}
	return arr
}

func spin(current int, instruction string) (int, int) {
	const max = 100
	var direction byte
	var distance int
	_, err := fmt.Sscanf(instruction, "%c%d", &direction, &distance)
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
	var rounds int
	rounds += distance / max
	distance = distance % max
	for range distance {
		switch direction {
		case 'L':
			current--
			if current < 0 {
				current += max
			}
		case 'R':
			current++
			if current >= max {
				current -= max
			}
		default:
			fmt.Println("unknown direction", direction)
			os.Exit(1)
		}
		if current == 0 {
			rounds++
		}
	}
	return current, rounds
}

func part1(filename string) int {
	in := parseInput(filename)
	current := 50
	var ans int
	for _, row := range in {
		current, _ = spin(current, string(row))
		if current == 0 {
			ans++
		}
	}
	return ans
}

func part2(filename string) int {
	in := parseInput(filename)
	current := 50
	var ans int
	for _, row := range in {
		var rounds int
		current, rounds = spin(current, string(row))
		ans += rounds
	}
	return ans
}
