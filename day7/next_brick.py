import random
def return_random_int(numbers):
    number = random.choice(list(numbers))
    return number

# main 
numbers ={1, 2, 3, 4, 5, 6 }
number = return_random_int(numbers)

print(f"the returned number is : {number}")

