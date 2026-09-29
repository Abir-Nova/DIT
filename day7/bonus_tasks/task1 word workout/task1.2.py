
import string

def stripping_text(original_text):
    text =""
    for letter in original_text :
        if letter.isalpha():
            text += letter
    return text.lower()


def is_palindrome(text):

    if (len(text)<=1):
        return True
    for i in range (len(text)):
        if text[i] != text[len(text) - 1 - i]:
            return False
    return True

#main

original_text = input("enter a string : ")
text = stripping_text(original_text)

if is_palindrome(text):
    print("this is a palindrome")

else :
    print("this isn't a palindrome")
        


