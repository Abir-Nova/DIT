# user input 
# task2.1
name = input("enter your name: ")
name = name.lower() # convert the name to lowercase
name = name[0].upper() + name[1:] # capitalize the first letter of the name 
print(f"hello {name}!")

# task2.2
#Prompt the user for a number.
#Then, print the type of the provided input.
#It should return <class 'str'>

number_input = input("Please enter a number: ")
print(f"The type of the provided input is: {type(number_input)}")
# the input is always a string, even if the user enters a number.

# task 2.3
#Prompt the user for two numbers.
#Then, print ”The sum of the provided numbers is .”

def sum_2_numbers(a,b):
    return int(a) + int(b)

a = input("Please enter the first number: ")
b = input("Please enter the second number: ")
sum = sum_2_numbers(a, b)
print(f"The sum of the provided numbers {a} and {b} is: {sum}")

#task2.4
#Write a program that:
#✓ asks the user to type a string;
#✓ extracts the first letter of each word in the string;
#✓ joins these letters to make a word.

def acronym_from_string(input_string):
    # Split the input string into words
    words = input_string.split()
    # Extract the first letter of each word and join them to make a new word
    result = ''.join(word[0] for word in words)
    return result

#example usage
input_string = "Play your trumpet happily on nights"
result = acronym_from_string(input_string)
print(f"The acronym of the provided string '{input_string}' is: {result}")

#in general
input_string = input("Please enter a string: ")
result = acronym_from_string(input_string)
print(f"The result of the provided string '{input_string}' is: {result}")

