#Write a recursive function that prompts the user for a string of characters, strips out the spaces and punctuation signs, lowercases the string, then tests if it is a palindrome.

import string

def stripping_text(original_text):
    text =""
    for letter in original_text :
        if letter not in string.punctuation and letter != " ":
            text += letter
    return text.lower()


def is_palindrome(text):

    if (len(text)<=1):
        return True
    # first letter != last letter
    if (text [0] != text[-1]):
        return False 
    # check the middle recursively
    return is_palindrome(text[1:-1])

#main

original_text = input("enter a string ")
text = stripping_text(original_text)

if is_palindrome(text):
    print("this is a palindrome")

else :
    print("this isn't a palindrome")
        


