#Print all integers, in decreasing order from 10 000 to 1, that are divisible by 7.

for i in range(10000,0,-1):
    if( i%7 == 0) :
        print(i)
