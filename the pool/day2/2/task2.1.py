value1 = 1 + 11 + 111 + 1111 + 11111 + 111111 + 1111111 + 11111111 + 111111111

print("Result:", value1)
print("Power 2:", value1 ** 2)
print("Power 3:", value1 ** 3)
print("Power 4:", pow (value1,4))
print("Power 5:", pow (value1,5))

value2= 1 + 11 + 111 + 1111 + 11111 + 111111 + 1111111 + 11111111 + 111111111 + 1111111111
print("Result:", value2)
print("Power 2:", value2 ** 2)
print("Power 3:", value2 ** 3)
print("Power 4:", pow (value2,4))
print("Power 5:", pow (value2,5))

value3= 1 + 11 + 111 + 1111 + 11111 + 111111 + 1111111 + 11111111 + 111111111 + 1111111111 + 11111111111
print("Result:", value3)
print("Power 2:", value3 ** 2)
print("Power 3:", value3 ** 3)
print("Power 4:", pow (value3,4))
print("Power 5:", pow (value3,5))


# methode 2 : function

def powers(value):
    print("Result:", value)
    print("Power 2:", value ** 2)
    print("Power 3:", value ** 3)
    print("Power 4:", pow(value, 4))
    print("Power 5:", pow(value, 5))

# example usage
#1
powers( 1 + 11 + 111 + 1111 + 11111 + 111111 + 1111111 + 11111111 + 111111111)
#2
powers( 1 + 11 + 111 + 1111 + 11111 + 111111 + 1111111 + 11111111 + 111111111 + 1111111111)
#3
powers( 1 + 11 + 111 + 1111 + 11111 + 111111 + 1111111 + 11111111 + 111111111 + 1111111111 + 11111111111)   