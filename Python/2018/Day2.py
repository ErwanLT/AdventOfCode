with open('inputs/input2') as input_file:
    file = input_file.read()

input_lines = file.splitlines()

double_letter = 0
triple_letter = 0

for line in input_lines:
    char_done = []
    has_double = False
    has_triple = False
    for char in line:
        if not has_double and line.count(char) == 2:
            double_letter += 1
            has_double = True
        if not has_triple and line.count(char) == 3:
            triple_letter += 1
            has_triple = True

part1_answer = double_letter * triple_letter
print(part1_answer)


def match(box1,box2):
    differ = 0
    ans = ""
    for i in range(len(box1)):
        if(box1[i] != box2[i]):
            differ += 1
            if(differ > 1):
                return False
        else:
            ans += box1[i]
    if differ == 1:
        return ans
    else:
        return False


def find_match(boxes):
    for i in range(len(boxes)):
        for j in range(i+1, len(boxes)):
            ans = match(boxes[i], boxes[j])
            if ans != False:
                return ans


print(find_match(input_lines))
