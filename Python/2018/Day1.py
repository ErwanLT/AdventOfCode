with open("inputs/input1", 'r') as input_file:
    input = input_file.read()

input_as_int = [int(line) for line in input.splitlines()]


def part1():
    frequency = 0
    for val in input_as_int:
        frequency += val

    print("Final frequency : ", frequency)


part1()


def part2():
    i = 0
    frequency = 0
    found = False
    reached = {}
    size = len(input_as_int)

    while not found:
        frequency += input_as_int[i]
        if frequency in reached:
            reached_twice = frequency
            found = True
        reached[frequency] = True
        i = (i + 1) % size

    print("First repeated frequency : ", reached_twice)


part2()
