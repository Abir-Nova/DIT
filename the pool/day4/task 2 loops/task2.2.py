# Prompt the user for a string. Display each character of this string twice.
# For instance taxi will return ttaaxxii.

sentence = input("type a string : ")

for letter in sentence :
    print(letter+letter, end="") # end="" to let in the same line 

print("\n")