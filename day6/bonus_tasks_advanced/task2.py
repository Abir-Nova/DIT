#task 2.1
def find_longest_word(words):
    sorted_words = sorted(words, key=len)
    return sorted_words[-1]

#example 

words =["apple", "banana", "cherry", "kiwi"]
print("methode 1 :")
print(f"the longest word is : {find_longest_word(words)}")

print("methode 2 :")

def find_longest_word(words):
    sorted_words = sorted(words, key=len,reverse=True)
    return sorted_words[0]

print(f"the longest word is : {find_longest_word(words)}")


print("methode 3 :")

def find_longest_word(words):
    return max(words, key=len)

print(f"the longest word is : {find_longest_word(words)}")

# task 2.2

# Write a recursive function count_digits(n) that takes a positive integer and returns the total number of
# digits it contains.
# count_digits(42000) should return 5.
# You are not allowed to convert the number into a string (no len(str(n)))! Instead, use mathematics.
# If you divide a number by 10 (using integer division //), you remove its last digit. What is the base
# case?

def count_digits(n):
    if n==0 : 
        return 0
    else :
        return 1 + count_digits(n//10)



# example 

n = int(input("enter a positif int : "))
if n<=0:
    print("erreur number negative or null")
else :
    print(f"the total of digits in ur number {n} = {count_digits(n)}")

