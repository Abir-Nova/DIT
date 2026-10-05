#task 1.2
#Ask the user for an integer. If it's equal to 42, display ”This is correct!”.
while True:
    number = int(input("guess the number: "))
    if number == 42:
        print("This is correct!")
        break
    else:
        print("Try again.")