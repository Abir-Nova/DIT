

#Write a recursive function that computes the sum of all integers from 1 to n, a given parameter.
# methode 1 : de n --> 1

def compute_sum(n):
    if n==1 :
        return 1
    return n+ compute_sum(n-1)

# example use case 

n=int(input("enter an int : ")) 

print(f"the sum of all integers from 1 to your number {n} is {compute_sum(n)}")

# methode 2 : de 1 --> n

def compute_sum(n,i=1):
    if i==n :
        return i
    return i+ compute_sum(n,i+1)


