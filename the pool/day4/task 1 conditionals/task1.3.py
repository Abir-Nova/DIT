#task 1.3
# Prompt the user for an integer. Then, if it is:
# ✓ odd, display ”This integer is odd”;
# ✓ even, display ”This integer is even”.

while True:
    number=int(input("enter an int to see if it's even or odd (press 0 to quit)"))
    if (number == 0) : 
        break 
    if ( number%2 == 0) :
        print(f"ur number {number} is even")
    else :
        print(f"ur number {number} is odd")
    