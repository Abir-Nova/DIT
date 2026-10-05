# Create a program that takes an integer N as an argument and counts how many letters would be used if all
# the numbers from 1 to N (included) were written out in words.
# Spaces and hyphens should NOT be counted.
# The use of ”and” when writing out numbers is in compliance with British usage.
# For instance:
# ✓ 42 (forty-two) contains 8 letters;
# ✓ 115 (one hundred and fifteen) contains 20 letters.
# To guide you in your quest, here are some inputs and corresponding outputs:
# ✓ N = 5 returns 19 (one, two, three, four, five => 3 + 3 + 5 + 4 + 4 = 19);
# ✓ N = 42 returns 319;
# ✓ N = 1000 returns 21124

# what I think:
# first : iterate all the numbers from one to that number 
# then write every number in letters 
# then count the letters and sum them up 
# return the sum of letters

def number_to_words(n):
    if n == 0:
        return "zero"
    
    ones = ["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    teens = ["ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]
    tens = ["", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]
    hundreds=["", "hundred", "thousand"]
    words = ""
    if n >= 1000:
        words += ones[n // 1000] + " " + hundreds[2] + " "
        n %= 1000
    if n >= 100:
        words += ones[n // 100] + " " + hundreds[1] + " "
        n %= 100
        if n > 0:
            words += "and "
    if n >= 20:
        words += tens[n // 10] + " "
        n %= 10
        if n > 0:
            words += ones[n] + " "
    elif n >= 10:
        words += teens[n - 10]
    elif n > 0:
        words += ones[n]
    return words.strip()

def count_letters_in_numbers(limit):
    total_letters = 0
    for i in range(1, limit + 1):
        words = number_to_words(i)
        total_letters += len(words.replace(" ", ""))  # Count letters only, ignore spaces
    return total_letters

# Example usage:
N = int(input("Enter an integer N: "))
result = count_letters_in_numbers(N)
print(f"The number of letters used is: {result}")
