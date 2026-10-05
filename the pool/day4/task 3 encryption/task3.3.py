# if 
# the plaintext : attacking tonight 
# and the key : oculorhinolaryngology
# the encrypted texted is : ovnlqbpvt hznzeuz

# the first letter of the plaintext, a, is shifted by 14 positions in the alphabet (because the first letter of the key, o, is the 14th letter of the alphabet, counting from zero), yielding o;
# the second letter, t, is shifted by 2 (because the second letter of the key, c, is the 2nd letter of the alphabet, counting from zero) yielding v;
# the third letter, t, is shifted by 20 (u), yielding n, with wrap-around;

# Write a program that can encrypt and decrypt a text using a Vigenère cipher.

# we're going to ask the user if he wants to encrypt or decrypt or quit 
# then accordinly ..... 
# encrypt : plaintext and key --> encrypted texted
# decrypt : encrypted text and key  --> decrypted texted
# we see the first letter in key 
# --> find it's index in  alphabet starting from 0
# --> we shift the first letter in the plaintext with the position we found 
# we store and concatenate in the encrypted text 

alphabet ="abcdefghijklmnopqrstuvwxyz"

# encrypt 
def encrypt(message, key):
    result = "" 
    key_position=0

    for letter in message:
        if letter in alphabet :
            position_letter_message=alphabet.index(letter) # index of the each letter in the plain text
            position_letter_key=alphabet.index(key[key_position]) # index of the each letter in the key
            new_position = (position_letter_message + position_letter_key) % 26 # we shift each letter in the plaintext with the position we found  
            # %26 makes the result wrap around the alphabet  

            result+= alphabet[new_position] ## we store and concatenate in the encrypted text 
            key_position+=1 # move to the next key letter
            
            # Restart the key when necessary
            if key_position == len(key):
                key_position = 0
        else :
            result += letter
    return result

# decrypt 
def decrypt(message, key):
    result = "" 
    key_position=0

    for letter in message :
        if letter in alphabet :
            position_letter_message=alphabet.index(letter) # index of the each letter in the plain text
            position_letter_key=alphabet.index(i) # index of the each letter in the key
            new_position = (position_letter_message - position_letter_key)%26 # we shift each letter in the plaintext with the position we found  

            result+= alphabet[new_position] ## we store and concatenate in the encrypted text 
            key_position+=1

            # Restart the key when necessary
            if key_position == len(key):
                key_position = 0

        else :
            result += letter
    return result

while True : 
    choice = int (input("choose 1 : for encrypt and 2 for decrypt and 0 to quit : "))
    match choice :
        case 1:
            # encrypt : plaintext and key --> encrypted texted
            print ("u choosed to encrypt a plain text")
            plaintext = input ("type a plaintext  : ").lower()
            key = input ("type a key : ").lower()
            encrypted_text = encrypt(plaintext,key)
            print(f"the encrypted texted is : {encrypted_text}")
            
        case 2:
            # decrypt : encrypted text and key  --> decrypted texted
            print ("u choosed to decrypt an encrypted text ")
            encrypted_text = input ("type the encrypted_text  : ").lower()
            key = input ("type a key : ").lower()
            decrypted_text = decrypt(encrypted_text,key)
            print(f"the encrypted texted is : {decrypted_text}")
            
        case 0: 
            print("u choosed to quit bye")
            quit()
        case _: 
            print ("invalid choice ")
