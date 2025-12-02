prep:
	echo $(DAY) > day.txt

input: DAY=$(shell cat day.txt)
input: COOKIE=$(shell cat cookie.txt)
input: URL_DAY=$(shell echo $(DAY) | sed 's/^0*//')
input:
	curl --location --request GET "https://adventofcode.com/2025/day/$(URL_DAY)/input" \
		--header "Cookie: $(COOKIE)" --output "src/Day$(DAY).txt"

test: DAY = $(shell cat day.txt)
test:
	go test -v $(DAY)/*.go
