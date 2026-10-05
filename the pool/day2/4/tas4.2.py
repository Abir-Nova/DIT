# methode 1
pi = 3 + ((1**2) / ((6+((pow(3,2))/(6+((pow(5,2))/(6+((pow(7,2))/(6+((pow(9,2))/(6+((pow(11,2))/6))))))))))) )
print(f"The value of Pi is (method 1): {pi:.6f}")
# methode 2
pi = 3 + ((1**2) / ((6+((3**2))/(6+((5**2))/(6+((7**2)/(6+((9**2)/(6+((11**2)/6))))))))))
print(f"The value of Pi is (method 2): {pi:.6f}")

# Methode 3
pi = 3
denominator = 6

for n in range(11, 0, -2):
    denominator = 6 + (n ** 2) / denominator

pi += 1 / denominator

print(f"The value of Pi is (method 3): {pi:.6f}")