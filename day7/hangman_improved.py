import random
import argparse
import time
from english_words import english_words_lower_set

# display the word , the attempts , penalties 

def display_target(target,guessed_letters):
    result=""
    for letter in target :
        if letter in guessed_letters:
            result+=letter
        else:
            result+="_"
    return result 

# define the hangman game 

def hangman(attempts, max_penalties, word_length):
    # set the word that we should guess
    # Keep only words with the requested length
    words = [
        word
        for word in english_words_lower_set
        if len(word) == word_length
    ]

    # Check that we found words with this length
    if not words:
        print(f"No words found with {word_length} letters.")
        return

    # choose a random word

    target = random.choice(words)
    target_length=len(target)

    penalties = 0
    guessed_letters=[]

    # set a timer 

    start_time = time.time()
    time_limit = 120  # 120 seconds

    while attempts>0 and penalties < max_penalties :
        elapsed_time = time.time() - start_time
        remaining_time = int(time_limit - elapsed_time)
        if remaining_time <= 0:
            print("\nTime's up!")
            print(f"you lost - the word was {target}")
            return
        print(f"\nTime left: {remaining_time} seconds")
        print(f"\ntarget:{display_target(target,guessed_letters)}\n")
        print(f"{attempts} attempts left \n ")
        print(f"{penalties} penalty \n ")
        print(f"the word has {target_length} letters \n ")
        
        # take a guess 
        choice = int (input("to guess a letter tap 1 to guess the target tap 2 : "))

        if choice == 1:
            guess= input("\nguess a letter:  ").lower()

            # check if the user entered more than one letter
            if len(guess) != 1:
                print("\nPlease enter only one letter.")
                continue

            #check if the user already guessed this letter

            if guess in guessed_letters:
                print("\n u already guessed that letter")
                continue

            guessed_letters.append(guess)

            if guess in target :
                print("\ncorrect")
            else:
                print("\nwrong")
                attempts -=1

        elif choice == 2:
            guess = input("\nguess the word: ").lower()
            if guess == target :
                print(f"\nu won \t the word was {target} ")
                return
            penalties +=5

        else : 
            print("\nchoice invalid !")

        # result : Check if the player found every letter 

        if "_" not in display_target(target,guessed_letters):
            print(f"\nu won \t the word was {target} ")
            return
        
        if penalties>= max_penalties or attempts == 0:
                print(f"\nu lost \t the word was {target} ")

# Parse command-line arguments
parser = argparse.ArgumentParser(description="Play a customizable Hangman game.")

parser.add_argument(
    "--attempts",
    type=int,
    default=6,
    help="Number of attempts allowed (default: 6)"
)

parser.add_argument(
    "--penalties",
    type=int,
    default=12,
    help="Maximum number of penalties allowed (default: 12)"
)

parser.add_argument(
    "--length",
    type=int,
    default=None,
    help="Length of the word"
)


args = parser.parse_args()

# If no length is specified, choose a random word of any length
if args.length is None:
    target = random.choice(list(english_words_lower_set))
    hangman(args.attempts, args.penalties, len(target))
else:
    hangman(args.attempts, args.penalties, args.length)




