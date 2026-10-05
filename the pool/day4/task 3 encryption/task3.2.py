def decrypt(message, key):
    alphabet ="abcdefghijklmnopqrstuvwxyz"
    result = ""

    for letter in message:
        if letter in alphabet :
            position=alphabet.index(letter)
            new_position = position - key
            if (new_position<0):
                new_position = new_position + 26 

            result+= alphabet[new_position]
        
        else :
            result += letter
    return result
        
while True :
    message = input ("type a message  : ")
    key = int(input ("type a key (type 0 to quit) "))
    if (key == 0):
        quit()
    result = decrypt(message,key)
    print(f"the decrypted message is {result}") 