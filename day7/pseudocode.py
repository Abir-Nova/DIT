import random
from english_words import english_words_lower_set

# display the word and the attempts , penalties , underscores

def display_target(target,guessed_letters):
    Initialize result as an empty string

    for each letter in target
        if letter is in guessed_letters
            add letter to result
        else
            add "_" to result
    return result : display the output
    

# define the hangman game 

def hangman():
    # set the target that we should guess

    target = random.choice(list(english_words_lower_set))

    attempts = 6  # set the maximum of the attempts 
    penalties = 0  # initiate the penalties at 0 : so we can increment by 5 if the user commited a penalty
    guessed_letters=[] # initiate an empty list of the guessed letters 

    while attempts>0 and penalties <12 : # loop while we still in the game 
        print(the displayed output with the underscores)
        print(attempts left )
        print(penalties number)
        # since we can take the word guess let's add a step to take the user's choice : if he wants to guess a letter or the all word  
        # then accordinally take the guess from the user 
        choice = int (input("to guess a letter tap 1 to guess the target tap 2 : "))

        if choice == 1:
            guess= input("\nguess a letter:  ").lower()  # always lower cause we're using the english words lower set

            if guess in guessed_letters:
                print(u already guessed that letter)
                continue
            guessed_letters.append(guess)

            if guess in target :
                print("correct")
            else:
                print("wrong")
                attempts -=1 # decerement the attempts 

        elif choice == 2:
            guess = input("\nguess the word: ").lower()
            if guess == target :
                print(f"u won the word was {target} ")
                return # to end the loop and exit
            penalties +=5 # else incerement the penalties by 5 if the word is wrong 

        else : 
            print("\nchoice invalid !") # if the user enters another number other than 1 or 2 

        # result 

        if there are no underscores "_" left in the displayed word
            print(u won)
            return # end the game
        
        
        
        if penalties>=12 or attempts == 0:
                print(u lost and display the word )

# play
hangman() # launch the game 

