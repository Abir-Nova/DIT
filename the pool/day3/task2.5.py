#task2.5 and task2.6

from collections import Counter
LANGUAGE_FREQUENCIES = {
    "English": {
        'e': 12.7, 't': 9.1, 'a': 8.2, 'o': 7.5, 'i': 7.0,
        'n': 6.7, 's': 6.3, 'h': 6.1, 'r': 6.0, 'd': 4.3,
        'l': 4.0, 'c': 2., 'u': 2.8, 'm': 2.4, 'w': 2.4,
        'f': 2.2, 'g': 2.0, 'y': 2.0, 'p': 1.9, 'b': 1.5,
        'v': 1.0, 'k': 0.8, 'j': 0.15, 'x': 0.15, 'q': 0.10, 'z': 0.07
    },

    "French": {
        'e': 14.7, 'a': 7.6, 'i': 7.5, 's': 7.9, 't': 7.2,
        'n': 7.1, 'r': 6.6, 'u': 6.3, 'l': 5.5, 'o': 5.8,
        'd': 3.7, 'c': 3.3, 'm': 2.9, 'p': 2.5, 'v': 1.8,
        'q': 1.4, 'f': 1.1, 'b': 0.9, 'g': 1.0, 'h': 0.7,
        'j': 0.6, 'x': 0.4, 'y': 0.3, 'z': 0.1, 'w': 0.1, 'k': 0.05
    },

    "Spanish": {
        'e': 13.7, 'a': 12.5, 'o': 8.7, 's': 7.0, 'r': 6.9,
        'n': 6.7, 'i': 6.2, 'l': 5.0, 'd': 5.0, 't': 4.6,
        'c': 4.7, 'u': 3.9, 'm': 3.1, 'p': 2.5, 'b': 1.4,
        'g': 1.0, 'v': 1.0, 'y': 1.0, 'q': 0.9, 'h': 0.7,
        'f': 0.7, 'z': 0.5, 'j': 0.4, 'ñ': 0.3, 'x': 0.2, 'k': 0.1, 'w': 0.1
    }
}

input_string = input("Please enter a string: ")

# lowercase the input string to make the comparison case-insensitive
input_string = input_string.lower()

# Count how many times each letter appears in the input string
letter_counts = Counter(
    character for character in input_string if character.isalpha()
)   

# print the letter counts
print(f"The letter counts in the input string {input_string} are: {letter_counts}")

#convert the letter counts to frequencies
total_letters = sum(letter_counts.values())
letter_frequencies = {
    letter: (count / total_letters) * 100 for letter, count in letter_counts.items()
    }

best_language = None
best_difference = None

# compare the letter frequencies in the input string with the predefined frequencies for each language
for language in LANGUAGE_FREQUENCIES:
    language_freq = LANGUAGE_FREQUENCIES[language]
    difference = 0
    for letter in letter_frequencies:
        if letter in language_freq:
            difference += abs(letter_frequencies[letter] - language_freq[letter])
    print(f"Difference for {language}: {difference}")

    if best_difference is None or difference < best_difference:
        best_difference = difference
        best_language = language

# The language with the lowest difference is the most likely language of the input string.   
print(f"The most likely language of the input string is: {best_language}")


# test sentences
# I am a curious and determined person. I enjoy learning new things, solving problems, and improving myself. I can be quiet at first, but I become more comfortable and talkative when I get to know people.
# Je suis une personne curieuse et déterminée. J'aime apprendre de nouvelles choses, résoudre des problèmes et m'améliorer. Je peux être calme au début, mais je deviens plus à l'aise et plus bavard quand j'apprends à connaître les gens.
# Soy una persona curiosa y decidida. Me gusta aprender cosas nuevas, resolver problemas y mejorar. Al principio puedo ser tranquila, pero me siento más cómoda y hablo más cuando conozco mejor a las personas
