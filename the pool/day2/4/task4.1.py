#calculate the first 6 decimals of Pi using the formula:
#π =4∗(1/1−1/3+1/5−1/7...)
pi = 4 * (1/1 - 1/3 + 1/5 - 1/7 + 1/9 - 1/11)
print(f"The value of Pi is (methode 1): {pi:.6f}")


# methode 2
pi = 0
n = 0
while True:
    term = 4 * ((-1) ** n) / (2 * n + 1) # -1^n is used to alternate the sign of the term, and 2n + 1 is used to generate the odd numbers in the denominator
    pi += term

    if round(pi, 6) == 3.141593:
        break

    n += 1
print(f"The value of Pi is (methode 2): {pi:.6f}")

#methode 3 
pi = 0

for n in range(1000000):
    pi += 4 * ((-1) ** n) / (2 * n + 1)

print(f"The value of Pi is (methode 3): {pi:.6f}")

