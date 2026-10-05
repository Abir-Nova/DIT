# task1.1
sentence = "this is the task 1.1 of day 3"
print(sentence)

#task1.2
print(f"the first character is: {sentence[0]}")

#task1.3
print(f"the last character is : {sentence[-1]}")

#task1.4
print(f"the charachters from the fifth character to the tenth are : {sentence[4:10]}") 
#10 because the last index is not included
#4 because the first index is 0

#task1.5
print (f"the sentence in lowercase is: {sentence.lower()}")

#task1.6
# methode 1 : using the replace() method
print("methode 1 : using the replace() method")
def replace_characters(sentence, old_char, new_char):
    return sentence.replace(old_char, new_char)
    # to do it without using the replace() method 
    # we can use a for loop to iterate through the sentence 
    # and check if the character is equal to the old_char, 
    # if it is we add the new_char to a new string, 
    # if it is not we add the character to the new string. 
    # then we return the new string.

original_sentence = "tutu on the tuki-kata"
new_sentence = replace_characters(original_sentence, "tu", "ta")
print(f"the original sentence is: {original_sentence}")
print(f"the new sentence is: {new_sentence}")

# methode 2 : using a loop
print("methode 2 : using a loop")

def replace_characters_loop(sentence, old_char, new_char):
    new_sentence = ""
    i = 0

    while i < len(sentence):
        if sentence[i:i + len(old_char)] == old_char:
            new_sentence += new_char
            i += len(old_char)
        else:
            new_sentence += sentence[i]
            i += 1
    return new_sentence
new_sentence = replace_characters_loop(original_sentence, "tu", "ta")

print(f"the original sentence is: {original_sentence}")
print(f"the new sentence is: {new_sentence}")

#task1.7
#Explain the following code and predict its output. Then, test your prediction by running the code.
string = "Hello world!"
position = string.find("a") # find the position of the first occurrence of the character "a" in the string 
print(position) # since "a" is not present in the string, the find() method will return -1. Therefore, the output of this code will be -1.
# we can add a condition to check if the character is present in the string or not. If it is not present, we can print a message saying that the character is not found in the string. then if found we print the position of the character in the string.
if position != -1:
    print(f"The character 'a' is found at position {position}.")
else:
    print("The character 'a' is not found in the string.")

#task 1.8
#Can you predict the result of the following snippet of code?
p = "abcdefghij"
print(p[::-2][:5][::-1][3:])
# jhfdb
#jhfdb
#bdfhj
#hj

# to check we can print each step of the code to see the result of each operation
step1 = p[::-2]
step2 = step1[:5]
step3 = step2[::-1]
step4 = step3[3:]
print(f"step1: {step1}")
print(f"step2: {step2}")
print(f"step3: {step3}")
print(f"step4: {step4}")

#task 1.9 
#already done in task 1.8


#task 1.10
# Write code that prints 10 times a given string
def print_string_ten_times(string):
    for i in range(10):
        print(string)

#example usage
print_string_ten_times("repeat this string 10 times")

#task 1.11 Debug print("hello" + 42)
# + means concatenation in this case
# but we cannot concatenate a string and an integer
# We need to convert the integer to a string before concatenating it with the string 
# We can do this by using the str() function to convert the integer to a string
# The corrected code would be:

print("hello " + str(42))

# challenge 
#Write code that counts the total number of occurrences of the strings ”cat”, ”garden” and ”mice” in any string, considering that:
#✓ each substring can be read left to right OR right to left;
#✓ matches must be case insensitive;
#✓ you should not bother with overlaps.

def count_occurrences(input_string):
    # Convert the input string to lowercase for case insensitive matching
    input_string = input_string.lower()
    
    # Define the substrings to search for
    substrings = ["cat", "garden", "mice"]
    
    # Initialize a counter for total occurrences
    count = 0
    
    # Loop through each substring and count occurrences in both directions
    for substring in substrings:
        # Count occurrences of the substring in the original string
        count_forward = input_string.count(substring)
        
        # Count occurrences of the substring in the reversed string
        count_backward = input_string[::-1].count(substring)
        
        # Add both counts to the total count
        count += count_forward + count_backward
    
    # we can add a condition to check if the substring is present in the string or not. If it is not present, we can print a message saying that the substring is not found in the string. then if found we print the number of occurrences of the substring in the string.
    if count == 0:
        print(f"The substrings 'cat', 'garden', and 'mice' are not found in the string '{input_string}'.")
    else:
        print(f"The total number of occurrences of the substrings 'cat', 'garden', and 'mice' in the string '{input_string}' is: {count}")

# Example usage
count_occurrences("the CataCat attaCk a Cat")
count_occurrences("thE Cat's tactic wAS tO surpRISE thE mIce iN tHE gArdeN")







