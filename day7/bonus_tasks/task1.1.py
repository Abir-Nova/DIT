

def count_vowels_consonants(text):
    vowel_number=0
    consonant_number=0
    vowels = "aeiouy"
    consonants ="bcdfghjklmnpqrstvwxz"
    text = text.lower()
    for letter in text :
        if letter.isalpha():
            if letter in vowels:
                vowel_number +=1
            elif letter in consonants:
                consonant_number +=1
    
    print(f"{vowel_number} vowels , {consonant_number} consonants")


# test 

word = input("enter a string to count vowels and consonants ")
count_vowels_consonants(word)