import string

def stripping_text(original_text):
    text =""
    for letter in original_text :
        if letter.isalpha():
            text += letter
    return text.lower()


def is_anagram(word1,word2):
    word1=stripping_text(word1)
    word2=stripping_text(word2)
    if len(word1) != len(word2):
        return False
    for letter in word1:
        if word2.count(letter) != word1.count(letter):
            return False
    return True


 #test 

word1=input("enter the first word : ")
word2=input("enter the second word : ")

print(is_anagram(word1,word2))

