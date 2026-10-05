import random
import argparse
import time
from datetime import date

def load_words(filename):
    try:
        with open(filename, "r") as file:
            words = []

            for line in file:
                word = line.strip()

                if word == "":
                    continue

                if " " in word:
                    print(f"Error: invalid line in file: {word}")
                    continue

                words.append(word.lower())

        if len(words) == 0:
            print("Error: the file contains no valid words.")
            return None

        return words

    except FileNotFoundError:
        print(f"Error: file '{filename}' not found.")
        return None

    except PermissionError:
        print(f"Error: cannot read file '{filename}'.")
        return None

    except OSError:
        print(f"Error: could not read file '{filename}'.")
        return None
        
# display the word , the attempts , penalties 

def display_target(target,guessed_letters):
    result=""
    for letter in target :
        if letter in guessed_letters:
            result+=letter
        else:
            result+="_"
    return result 


# return the win rate 
def win_rate(history): 
    if len(history) == 0: 
        return 0 

    won_games = 0 
    
    for game in history: 
        if game["won"]: 
            won_games += 1 
            
    return (won_games / len(history)) * 100

# return the average penalties on won games

def average_penalties_on_won_games(history): 
    total_penalties = 0 
    won_games = 0 

    for game in history: 
        if game["won"]: 
            total_penalties += game["penalties"] 
            won_games += 1 

    if won_games == 0: 
        return 0 
    
    return total_penalties / won_games

# return the longest word the player has found 
def longest_word_found(history): 
    longest_word = "" 

    for game in history: 
        if game["won"]: 
            if len(game["word"]) > len(longest_word): 
                longest_word = game["word"] 
    
    return longest_word


# display statistics 
def display_stats(history): 
    print("\n  GAME STATISTICS ") 

    if len(history) == 0: 
        print("No games played") 
        return 
    
    print(f"ur win rate: {win_rate(history):.2f}%") 
    
    average = average_penalties_on_won_games(history) 
    
    if average == 0: 
        print("Average penalties on won games: No won games") 
    else: 
        print(f"Average penalties on won games: {average:.2f}") 
        
    longest = longest_word_found(history)

    if longest == "": 
        print("Longest word found: No won games") 
    else: 
        print(f"Longest word found: {longest}")

# define the hangman game 

def hangman(attempts, max_penalties,words, word_length):
    
    original_attempts = attempts

    if word_length is not None:
        words = [
            word
            for word in words
            if len(word) == word_length
        ]

        if not words:
            print(f"No words found with {word_length} letters.")
            return

    target = random.choice(words)
    target_length=len(target)

    penalties = 0
    guessed_letters=[]

    # set a timer 

    start_time = time.time()
    time_limit = 60  # 60 seconds

    while attempts>0 and penalties < max_penalties :
        elapsed_time = time.time() - start_time
        remaining_time = int(time_limit - elapsed_time)
        if remaining_time <= 0:
            print("\nTime's up!")
            print(f"you lost - the word was {target}")
            
            return { 
                "word": target, 
                "won": False, 
                "penalties": penalties, 
                "attempts": original_attempts - attempts
            }
            
        print(f"\nTime left: {remaining_time} seconds")
        print(f"\ntarget:{display_target(target,guessed_letters)}\n")
        print(f"{attempts} attempts left \n ")
        print(f"{penalties} penalty \n ")
        print(f"the word has {target_length} letters \n ")
        
        # take a guess 
        choice = input("to guess a letter tap 1 to guess the target tap 2 or ? for a hint ")

        if choice == '?':
            
            if "_" not in display_target(target,guessed_letters):
                print("hint refused")
            elif penalties+2>=max_penalties:
                print("hint refused")
                
            else:
                #hint 
                #costs 2 penalties.
                penalties +=2
                #reveal one random letter that has not been found yet
                hints = []
                for letter in target :
                    if letter not in guessed_letters :
                        hints.append(letter)
                
                hint = random.choice(hints)
                guessed_letters.append(hint)
                print(display_target(target, guessed_letters))
                    
        elif choice == '1':
            guess= input("\nguess a letter:  ").lower()

            if not guess.isalpha():
                print("Please enter letters only.")
                continue

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

        elif choice == '2':
            guess = input("\nguess the word: ").lower()
            if guess == target :
                print(f"\nu won \t the word was {target} ")
                
                return { 
                    "word": target, 
                    "won": True, 
                    "penalties": penalties,
                    "attempts": original_attempts - attempts
                }
            print("wrong word")
            penalties +=5

        else : 
            print("\nchoice invalid !")

        # result : Check if the player found every letter 

        if "_" not in display_target(target,guessed_letters):
            print(f"\nu won \t the word was {target} ")
            
            return { 
                "word": target, 
                "won": True, 
                "penalties": penalties,
                "attempts": original_attempts - attempts
            }

        # Check if the player lost
        
        if penalties>= max_penalties or attempts == 0:
            print(f"\nu lost \t the word was {target} ")
            return { 
                "word": target, 
                "won": False, 
                "penalties": penalties,
                 "attempts": original_attempts - attempts 
            }

def check_high_score(result):
    filename = "high_scores.txt"

    today = date.today().isoformat()

    attempts = result["attempts"]
    word = result["word"]

    high_score = None
    high_score_date = None

    try:
        with open(filename, "r") as file:
            for line in file:
                parts = line.strip().split(",")

                if len(parts) != 2:
                    continue

                score = int(parts[0])
                score_date = parts[1]

                if high_score is None or score < high_score:
                    high_score = score
                    high_score_date = score_date

    except FileNotFoundError:
        pass

    if high_score is None or attempts < high_score:
        with open(filename, "a") as file:
            file.write(f"{attempts},{today}\n")

        print(f"Best ever! You guessed '{word}' in {attempts} attempts.")

    else:
        print(
            f"You guessed '{word}' in {attempts} attempts, "
            f"but the record from {high_score_date} is {high_score} attempts."
        )

# Parse command-line arguments
parser = argparse.ArgumentParser(description="Play a customizable Hangman game.")

parser.add_argument(
    "filename",
    help="File containing the words, one word per line"
)

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

words = load_words(args.filename)

if words is None:
    exit()

# Store the results of all games 
history = []

# Play multiple games 
while True: 

    # If no length is specified, choose a random word  
    
    result = hangman( 
        args.attempts, 
        args.penalties, 
        words,
        args.length 
    )

    # Store the result 
    if result is not None: 
        history.append(result)

        if result["won"]:
            check_high_score(result) 
    
    # Ask if the player wants another game 
    play_again = input("\nPlay again? (yes/no): ").lower() 
    
    if play_again not in ["yes", "y"]: 
        break

# Show statistics when the player quits 
display_stats(history)





