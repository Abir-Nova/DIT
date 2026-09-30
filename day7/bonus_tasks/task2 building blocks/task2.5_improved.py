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


def display_progress(target):
    result=""

    for i in range(len(target)):
        if i in revealed:
            result+= target[i]
        else :
            result+="_ "
    return  result

target = random.choice(list(english_words_lower_set))

word = shuffle(target)

attempts = 10
penalties=0
max_penalties=12
revealed=[]



while attempts>0 and penalties < max_penalties:
    print(f"the shuffled word is{word}")
    print(f"\nWord: {display_progress(target)}")
    print(f"Attempts: {attempts}")
    print(f"Penalties: {penalties}")
    
    guess = input("Guess the next letter, the whole word, or ? for a hint: ").lower()
    
    #hint 

    if guess =='?':
        # find the next unreaveled posiiton
        next_position = None

        for i in range(len(target)):
            if i not in revealed:
                next_position=i
                break
        if next_position is None :
            print("all letters are already revealed")

        elif penalties + 2 > max_penalties:
            print("hint rejected : not enough penalty points")
        
        else : 
            print(f"hint : the next letter is :{target[next_position]}")
            revealed.append(next_position)
            penalties +=2
        
    # single letter guess
    elif len(guess)==1:
        # find the next unrevealed position 
        next_position = None

        for i in range(len(target)):
            if i not in revealed:
                next_position=i
                break

        if next_position is None :
            print("all letters are already revealed")
        
        elif guess == target[next_position]:
            print("Correct!")
            revealed.append(next_position)

        else:
            print("Wrong position!")
            attempts -= 1

    # whole word guess
    elif len(guess)== len(target):
        if(guess == target):
            # reveal everything
            revealed = list(range(len(target)))
            print("correct")
            print(f"u have {attempts} attempts left")
            break
        else : 
            print("Wrong word , but : ")

            # Check every position
            for i in range(len(target)):

                if guess[i] == target[i]:

                    if i not in revealed:
                        revealed.append(i)

            attempts -= 1

            print(
                f"Correct positions: "
                f"{display_progress(target)}"
            )
        
    else : 
        print("Wrong!")
        attempts -= 1

    # check win 
    if len(revealed) == len(target):
        print(f"\nYou won, The word was: {target}")
        break
    
    print(f"Attempts left: {attempts}")
#game over 

if len(revealed) != len(target):

    if attempts == 0:
        print(f"\nYou lost! The word was: {target}")

    elif penalties >= max_penalties:
        print(f"\nYou lost! You reached the maximum penalties.")
        print(f"The word was: {target}")
    

