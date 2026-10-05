def digit_sum(num):
    print(f"The number is: {num}")
    s = 0
    for digit in str(num):
        s += int(digit)
    print(f"The sum of the digits is: {s}")
    
digit_sum(123456789)
digit_sum(112233445566778899)
digit_sum(123456789 * 987654321)






