n,s = input("enter an int then a string: ").split()
n=int(n)
if n == 0 : 
    quit() 
elif any(vowel in s.lower() for vowel in "aeiouy" ):
    print(n)
elif n>=42 : 
    print (n)
else : 
    print(s)

