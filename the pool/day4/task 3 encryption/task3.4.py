from collections import Counter

alphabet = "abcdefghijklmnopqrstuvwxyz"


def find_key(ciphertext, key_length):

    key = ""

    # Find each letter of the key
    for key_position in range(key_length):

        group = ""

        # Take every key_length-th letter
        for i in range(key_position, len(ciphertext), key_length):

            if ciphertext[i] in alphabet:
                group += ciphertext[i]

        # Count the letters in this group
        counts = Counter(group)

        # Find the most common letter
        most_common_letter = counts.most_common(1)[0][0]

        # We assume the most common English letter is 'e'
        cipher_position = alphabet.index(most_common_letter)
        e_position = alphabet.index("e")

        # Find the shift
        shift = (cipher_position - e_position) % 26

        # Convert the shift into a letter
        key += alphabet[shift]

    return key


def decrypt(message, key):

    result = ""
    key_position = 0

    for letter in message:

        if letter in alphabet:

            message_position = alphabet.index(letter)
            key_position_value = alphabet.index(key[key_position])

            new_position = (
                message_position - key_position_value
            ) % 26

            result += alphabet[new_position]

            # Move to the next key letter
            key_position += 1

            # Restart the key
            if key_position == len(key):
                key_position = 0

        else:
            result += letter

    return result


# Main program

ciphertext = input("Enter the encrypted text: ").lower()
key_length = int(input("Enter the length of the key: "))

key = find_key(ciphertext, key_length)

print("Guessed key:", key)

plaintext = decrypt(ciphertext, key)

print("Decrypted text:", plaintext)


