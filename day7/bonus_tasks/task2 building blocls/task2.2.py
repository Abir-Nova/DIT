def letter_occurrences(word):
    frequency = {}
    occurrence = 0 
    for letter in word:
        if letter not in frequency:
            frequency[letter]=word.count(letter)
    return frequency


print(letter_occurrences(input("enter a word : ")))


def max_letter_frequency(dict_frequency):
    max_letter=None
    max_frequency=0
    for letter in dict_frequency:
        if dict_frequency[letter]>max_frequency:
            max_frequency=dict_frequency[letter]
            max_letter= letter
        elif dict_frequency[letter]==max_frequency:
            if letter<max_letter:
                max_letter=letter
    return max_letter

word = input("enter a word : ")
dict_frequency=letter_occurrences(word)
print(dict_frequency)
max_letter=max_letter_frequency(dict_frequency)
print(f"the most frequent letter in this word {word} is {max_letter}")

