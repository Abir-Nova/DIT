y = 1

while True:
    divisible = True

    for x in range(1, 3): # we add 1 cause range is exclusive of the upper limit
        if y % x != 0:        #not divisible by x
            divisible = False
            break

    if divisible:
        break

    y += 1

print("The smallest number that is evenly divisible from 1 to 11 is:", y)
