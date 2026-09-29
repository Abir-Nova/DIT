import random
from english_words import english_words_lower_set

words =[]

for word in range(20):
    word = random.choice(list (english_words_lower_set))
    words.append(word)

print(f" the list of words are : \n {words}"  )

# filter by word's length

def filter_by_word_length(words,n):
    result=[]
    for word in words :
        if (len(word) == n):
            result.append(word)
    if not result : 
        print(f"no word found with this length {n} ")
    return result

while True:
    n=int(input("enter the length : "))
    if (n<=0):
        print("invalide number")
    else : 
        break
result = filter_by_word_length(words,n)
if result:
    print(f"the words with {n} letters are :  {result}")


# fct 2 : One that returns only the words made exclusively of letters (no digits, no -, no spaces).
def find_words_with_exclusive_letters(words):
    result=[]
    for word in words :
        if word.isalpha():
            result.append(word)
    return result
    
words = find_words_with_exclusive_letters(words)
print(f"the words with exclusively letters are :  {words}")

def group_words_by_length(words): 
    dict_words = {} 
    for word in words: 
        length = len(word) 
        if length not in dict_words: 
            dict_words[length] = [] 
        dict_words[length].append(word) 
    return dict_words


#fct 3 : One that returns a dict grouping the words by length
dict_words = group_words_by_length(words) 
print(f"The words grouped by length in a dictionary are:\n{dict_words}")


# Finally, ask the user for a length and pick a random word of that length

while True:
    n=int(input("enter the length : "))
    if (n<=0):
        print("invalide number")
    else : 
        break


result = filter_by_word_length(words, n) 
if result: 
    random_word = random.choice(result) 
    print(f"Random word with {n} letters: {random_word}") 
else: 
    print(f"No word found with this length: {n}")