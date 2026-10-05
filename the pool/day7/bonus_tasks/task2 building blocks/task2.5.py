import random
from english_words import english_words_lower_set


def shuffle(word):
    shuffled=""
    original=word
    letters=list(word)
    
    for letter in word:
        index=random.randint(0,len(letters)-1)
        shuffled+=letters.pop(index)
    
    if (original == shuffled):
        return shuffle(word)

    return shuffled


target = random.choice(list(english_words_lower_set))

word = shuffle(target)

attempts = 10
print(f"the shuffled word is{word}")

while attempts>0:
    guess = input("guess the original word ")
    guess=guess.lower()
    if (guess == target):
        print("correct")
        print(f"u have {attempts} attempts left")
        break
    else : 
        print("wrong")
        attempts-=1
    print(f"u have {attempts} attempts left")