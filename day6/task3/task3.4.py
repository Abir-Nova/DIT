my_list=[1>2,3==6,0]

print(any(my_list)) # false

print(all(my_list)) # false 


my_list=[1<2,3!=6,0]

print(any(my_list)) # true

print(all(my_list)) # false 


my_list=[1<2,3!=6,1]

print(any(my_list)) # true

print(all(my_list)) # true


# any() → Is there AT LEAST ONE true?
# all() → Are ALL of them true?