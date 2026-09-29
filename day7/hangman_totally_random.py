from random_word import RandomWords
# display the word and the attempts

def display_target(target,guessed_letters):
    result=""
    for letter in target :
        if letter in guessed_letters:
            result+=letter
        else:
            result+="_"
    return result 

# define the hangman game 

def hangman():
    # set the word that we should guess

    r = RandomWords()
    target=r.get_random_word()

    attempts = 6
    penalties = 0
    guessed_letters=[]

    while attempts>0 and penalties <12 :
        print(f"\ntarget:{display_target(target,guessed_letters)}\n")
        print(f"{attempts} attempts left \n ")
        print(f"{penalties} penalty \n ")

        # take a guess 
        choice = int (input("to guess a letter tap 1 to guess the target tap 2 : "))

        if choice == 1:
            guess= input("\nguess a letter:  ").lower()

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

        # result 

        if "_" not in display_target(target,guessed_letters):
            print(f"\nu won \t the word was {target} ")
            return
        
        if penalties>=12 or attempts == 0:
                print(f"\nu lost \t the word was {target} ")

# play
hangman()