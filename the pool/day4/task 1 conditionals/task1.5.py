#task 1.5
# Prompt the user for an integer:
# ✓ if it's 42, display ”a”;
# ✓ if it's smaller than or equal to 21, display ”b”;
# ✓ if it's even, display ”c”;
# ✓ if this integer divided by 2 is smaller than 21 (excluded), display ”d”;
# ✓ finally, if it is odd and greater than or equal to 45, display ”e”;
# ✓ in any other case, display ”f”.
number = int (input ("enter a number : "))
found = False 
if (number == 42) : 
    print("a") 
    found = True
    
if (number <= 21):
    print("b")
    found = True

if (number % 2 == 0):
    print("c")
    found = True

if( (number/2) < 21) : 
    print("d")
    found = True

if ((number % 2 != 0) and (number >= 45)) : 
    print("e") 
    found = True
    
if not found :
    print("f") 
