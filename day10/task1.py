#task1.1 : Dig this piece of code. Try to figure out its output. Then, run it to see if you were right
crazyFunction = lambda x, y: x * y 
meaningOfLife = crazyFunction(6, 7)
print(meaningOfLife)


# The code defines a lambda function called `crazyFunction` 
# that takes two arguments x and y and returns their product. 
# It then calls this function with the arguments 6 and 7, storing the result in the variable `meaningOfLife`. 
# Finally, it prints the value of `meaningOfLife`.

# task1.2 : Use the sorted() built-in function to build a new sorted list from a list of lists, 
# but by using the second value (count) inside each secondary list.
# Test it with a list such as animalsCounts = [['cat', 666], ['dog', 3], ['elephant', 42]].

animalsCounts = [['cat', 666], ['dog', 3], ['elephant', 42]]

sortedAnimals = sorted(animalsCounts, key=lambda x: x[1])

print(sortedAnimals)


# task  1.3 : Dig this code , Try to predict its output. Then, run it to check if you're right.

print(list(filter(lambda x: x > 10, [3.14, 101, 42, 666, -1])))
# this code uses the filter() function to create a new list containing only the elements from the original list that are greater than 10.

# task 1.4 : Test this code and try to explain it: 
print([*enumerate([42, 3, 4, 18, 3, 10])])
# this code uses the enumerate() function to create an iterable of tuples, where each tuple contains an index and the corresponding value from the original list. 
# The * operator is used to unpack the tuples into a list. 
# The output will be a list of tuples, where each tuple contains the index and value from the original list.

# task 1.5 : Create a function check_even that 
# returns True if a number is even and False otherwise.
# Use your previous function and the filter() built-in function in order to output a list of all even numbers contained in [1, 2, 3, 4, 5, 6].

def check_even(n):
    return n % 2 == 0

even_numbers = list(filter(check_even, [1, 2, 3, 4, 5, 6]))
print(even_numbers)

# task1.6 : Use filter to remove all strings with more than 4 characters from 
# ['apple', 'banana', 'kiwi', 'pear'].
short_strings = list(filter(lambda x: len(x) <= 4, ['apple', 'banana', 'kiwi', 'pear']))
print(short_strings)

# task 1.7 : Apply map to convert this list of temperatures [-10, 0, 17.6, 28, 100], from Celsius to Fahrenheit.
fahrenheit_temperatures = list(map(lambda x: x * 9/5 + 32, [-10, 0, 17.6, 28, 100]))
print(fahrenheit_temperatures)

# map is used to apply a function to each element of an iterable (in this case, a list of temperatures in Celsius) and return a new iterable with the results.
# map stands for "mapping" a function to each element of an iterable. In this case, the lambda function takes a temperature in Celsius and converts it to Fahrenheit using the formula (C * 9/5) + 32. The result is a new list of temperatures in Fahrenheit.

# task1.8 : test this code and explain it : 

first_names = ["Jackie", "Chuck", "Arnold", "Sylvester"]
last_names = ["Stallone", "Schwarzenegger", "Norris", "Chan"]
magic = [*zip(first_names, last_names[::-1])]
# ["Chan", "Norris", "Schwarzenegger", "Stallone"]
# zip(
    # ["Jackie", "Chuck", "Arnold", "Sylvester"],
    # ["Chan", "Norris", "Schwarzenegger", "Stallone"]
    # )
# The * unpacks the elements from that zip object into the list.
# [
#     ("Jackie", "Chan"), --> magic[0]
#     ("Chuck", "Norris"),--> magic[1]
#     ("Arnold", "Schwarzenegger"), --> magic[2]
#     ("Sylvester", "Stallone") --> magic[3]
# ]
print(magic[0])    # ('Jackie', 'Chan')
print(magic[1][0]) #  magic[1] ('Chuck', 'Norris')  --> magic[1][0] = 'Chuck'
print(magic[1][1]) # magic[1] ('Chuck', 'Norris') --> magic[1][1] = 'Norris'

# this code uses the zip() function 
# to combine two lists, 
# first_names and last_names, 
# into a list of tuples. 
# The last_names list is reversed using slicing (last_names[::-1]) before being zipped with first_names. 
# The * operator is used to unpack the tuples into a list called magic. 
# The code then prints the first tuple in magic, 
# the first element of the second tuple, 
# and the second element of the second tuple. 

# task 1.8 : 

# Create a function my_sum that accepts a variable number of arguments and prints their sum. For instance:
# ✓ my_sum(1) returns 1;
# ✓ my_sum(1,2,3) returns 6;
# ✓ my_sum(-20, -10, 5, 5, 10, 10) returns 0;
# ✓ my_sum(1, "toto") throws an Exception ValueError but no Traceback.

def my_sum(*args):
    total = 0
    for arg in args:
        if not isinstance(arg, (int, float)):
            raise ValueError("All arguments must be numbers.")
        total += arg
    return total

# usecase
print(my_sum(1))  # Output: 1
print(my_sum(1, 2, 3))  # Output: 6
print(my_sum(-20, -10, 5, 5, 10, 10))  # Output: 0

try:
    print(my_sum(1, "toto"))
except ValueError as e:
    print("Error:", e)

# take user input and call the function
try:
    user_input = input("Enter numbers separated by commas: ")
    numbers = [float(num.strip()) for num in user_input.split(",")]
    print("Sum:", my_sum(*numbers)) 
except ValueError as e:
    print("Error:", e)

#task 1.9 : 

# Write a function my_division that takes two integers as parameters, and returns both the quotient and the
# remainder of the euclidean division of the first by the second parameter. It should look like:
# my_division(42 , 4)
# 10
# 2
#Your function must handle errors.

def my_division(dividend, divisor):
    if not isinstance(dividend, int) or not isinstance(divisor, int):
        raise TypeError("Both parameters must be integers.")
    if divisor == 0:
        raise ValueError("The divisor cannot be zero.")
    quotient = dividend // divisor
    remainder = dividend % divisor
    return quotient, remainder

    # usecase
try:
    q, r = my_division(42, 4)
    print(q)  # Output: 10
    print(r)  # Output: 2

    q, r = my_division(10, 0)  # This will raise a ValueError
except ValueError as e:
    print("Error:", e)
except TypeError as e:
    print("Error:", e)

# take user input and call the function
try:
    dividend = int(input("Enter the dividend (integer): "))
    divisor = int(input("Enter the divisor (integer): "))
    q, r = my_division(dividend, divisor)
    print("Quotient:", q)
    print("Remainder:", r)
except ValueError as e:
    print("Error:", e)
except TypeError as e:
    print("Error:", e)

# task 1.10 :
# Create the function my_count that complies with the following conditions:
# ✓ it takes at least one parameter, named stop, which is an integer;
# ✓ it can accept a second argument, named start, which is also an integer;
# ✓ if the second argument is missing in the function call, assign the default value 0 to start;
# ✓ it prints all integers from start to stop.

def my_count(stop, start=0):
    for i in range(start, stop):
        print(i)

# usecase
my_count(5)  # Output: 0, 1, 2, 3, 4  
print("----")  
my_count(3, 1)  # Output: 1, 2

# task1.11 : 
# Upgrade your previous function with a second default argument, named step, which is an integer. If not
# provided, its default value is 1. The function prints all integers from start (included) to stop (excluded).
# Your function handles as many cases as possible.
# Your function should handle errors.
# ✓ What if step = 0?
# ✓ What if stop < start?
# ✓ What if stop = ”toto”?
# my_count(100, -100, 42)
# -100
# -58
# -16
# 26
# 68
# my_count(-100, 100, -42)
# 100
# 58
# 16
# -26
# -68

def my_count(stop, start=0,step=1):
    try :
        if step == 0:
            raise ValueError("Step cannot be zero.")
        if stop < start and step > 0:
            raise ValueError("Stop value cannot be less than start value when step is positive.")
        for i in range(start, stop, step    ):
            print(i)
    except ValueError as e:
        print("Error:", e)
    except TypeError as e:
        print("Error:", e)

# usecase
my_count(100, -100, 42)
print("----")
my_count(-100, 100, -42)

# take user input and call the function
   
stop = int(input("Enter the stop value (integer): "))
start = int(input("Enter the start value (integer, default is 0): ") or 0)
step = int(input("Enter the step value (integer, default is 1): ") or 1)
my_count(stop, start, step)

#task 1.12 :
# Create a function new_division that:
# ✓ takes two arguments, num and den, which are numbers;
# ✓ accepts an optional third argument, acc, which is an integer (default = 1);
# ✓ prints the result of the division of num by den with an accuracy of acc digits after the decimal point.
# For instance, new_division(8.4, 13)=0.6 and new_division(8.4, 13, 6)=0.646154
    
def new_division(num, den, acc=1):
    try:
        if not isinstance(num, (int, float)) or not isinstance(den, (int, float)):
            raise TypeError("Both num and den must be numbers.")
        if den == 0:
            raise ValueError("The denominator cannot be zero.")
        if not isinstance(acc, int) or acc < 0:
            raise ValueError("Accuracy must be a non-negative integer.")
        
        result = num / den
        print(f"{result:.{acc}f}")
    except ValueError as e:
        print("Error:", e)
    except TypeError as e:
        print("Error:", e)  

# usecase
new_division(8.4, 13)  # Output: 0.6
new_division(8.4, 13, 6)  # Output: 0.646154

# take user input and call the function
num = float(input("Enter the numerator (num): "))
den = float(input("Enter the denominator (den): "))
acc = int(input("Enter the accuracy (acc, default is 1): ") or 1)
new_division(num, den, acc) 

# task 1.13 :
# Create a ship function that:
# ✓ takes a variable number of arguments, corresponding to the full name of somebody;
# ✓ takes a variable number of keyword arguments, corresponding to the full address;
# ✓ prints the full shipping label in order to mail something to this person.
# For instance:
# ship("Batman", street="Mountain Drive", city="Gotham")
# Batman
# street: Mountain Drive
# city: Gotham
# ship("Superman", "The man of steel", apartment="3D", num= 344, street="Clinton Street",
# city="Metropolis")
# Superman The man of steel
# apartment: 3D
# num: 344
# street: Clinton Street
# city: Metropolis

def ship(*args, **kwargs):
    try:
        if not args:
            raise ValueError("At least one positional argument (name) is required.")
        if not kwargs:
            raise ValueError("At least one keyword argument (address) is required.")
        
        # Print the full name
        print(" ".join(args))
        
        # Print the address details
        for key, value in kwargs.items():
            print(f"{key}: {value}")
    except ValueError as e:
        print("Error:", e) 

# usecase 

ship("Batman", street="Mountain Drive", city="Gotham")
print("----")   
ship("Superman", "The man of steel", apartment="3D", num=344, street="Clinton Street", city="Metropolis")

# take user input and call the function
name = input("Enter the full name: ")
address = {}
while True:
    key = input("Enter address field (or 'done' to finish): ")
    if key.lower() == 'done':
        break
    value = input(f"Enter value for {key}: ")
    address[key] = value

ship(name, **address)






 