# for all integers from -30 to 30:
# ✓ if it's a multiple of 3, display ”Fizz”;
# ✓ if it's a multiple of 5, display ”Buzz”;
# ✓ if it's a multiple of 3 and 5, display ”FizzBuzz”;
# ✓ if it does not meet any of the previous conditions, just print the integer itself.

for i in range (-30,31) :
    found = False
    if((i%3 == 0) and (i%5 == 0)):
        print("FizzBuzz")
        found = True 
    elif(i%3 == 0):
        print("Fizz")
        found = True
    elif(i%5 == 0):
        print("Buzz")
        found = True
    if not found :
        print(i)
