scores={
    "A,E,I,O,U,L, N, S, T, R" : 1,
    "D,G":2,
    "B,C,M,P":3,
    "F,H,V,W,Y":4,
    "K":5,
    "J,X":8,
    "Q,Z":10
}

def calculate_score(sentence):
    s=0
    sentence = sentence.upper()
    for letter in sentence :
        for key , value in scores.items():
            if letter in key :
                s+=value
    return s

sentence = input("enter a string : ")
print(f"ur score is {calculate_score(sentence)}")