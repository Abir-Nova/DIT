# task 1.1 : Create a my_list of 5 elements. Then, print its first element

my_list = [1,2,"abir",3.14,[4,5,6]]

print( f" task1.1 : \n {my_list[0]}\n")

# task1.2 : Display the last element of your my_list
print(f" task1.2 : \n {my_list[-1]}\n")

#task1.3 : Add the integer 42 at the end of your my_list. Then, add the string forty-two at the end of your my_list.

my_list.append(42)
my_list.append("forty-two")

#task1.4 : Display your entire my_list. Then, display each element of your my_list one by one.


print(f" task1.4 : \n methode 1:  {my_list}\n")
print(" methode 2 : ")
for item in my_list :
    print(item, end=" , ") # to force the my_liste to be in the same line 
  
# task1.5 : Delete the last element of your my_list. Then, display your my_list to check if you did it properly.

del my_list[-1] # or my_list.pop()
print(f" \n task1.5 : \n {my_list}\n ")

#task 1.6 : Add an element at the beginning of the my_list and display all its elements.

my_list.insert(0,"start")
print(f" task1.6 : \n {my_list}\n ")

#task 1.7 : Display the sub-my_list from the second to the fourth element (included)

print(f" task1.7 : \n {my_list[1:4]}\n ")

# task 1.8 : Create and display a new reversed my_list oy your previous my_list, starting from the end

reversed_list=[]
for i in range(len(my_list)) :
    reversed_list.append(my_list[-1-i]) 
    
print(f" task1.8 : \n {reversed_list}\n ")

#task 1.9 : Add the ten integers from 11 to 20 at the end of your my_list.

for i in range(11,21):
    my_list.append(i)

print(f" task1.9 : \n {my_list}\n ")

#task 1.10 : 

my_first_list = [4, 5, 6]
my_second_list = [1, 2, 3]
my_first_list.extend(my_second_list)

# extend() takes each element from my_second_list and adds it to the end of my_first_list
print(f" task1.10 : \n code 1: \n my first list : {my_first_list}   my second list : {my_second_list} \n ")

my_first_list = [7, 8, 9]
my_second_list = [4, 5, 6]
my_first_list = [*my_first_list, *my_second_list]

print(f" code 2: \n my first list : {my_first_list}   my second list : {my_second_list} \n ")


#task 1.11 : Create a list of 5 numbers. Then, print the result of the multiplication of all its elements.

list2=[1, 2, 3, 4, 5] 

p=1 
for number in list2 :
    p*=number
print(f" task1.11 : \n methode 1: \n result= {p}\n ")

p=1

for i in range(len(list2)):
    p*=list2[i]

print(f" task1.11 : \n methode 2: \n result= {p}\n ")

#task1.12 : Test this code and try to explain it: [x + 10 for x in [3, 2, 6, 7, 1, 4]]

result = [x + 10 for x in [3, 2, 6, 7, 1, 4]]

print(f" task1.12 : \n {result}\n ")


# for every number in the liste we add 10 then the output will be onother list 
# result = [13,12,16,17,11,14] 

#task 1.13 : Create a list of 5 numbers. Then, display the smallest element. Finally, display the biggest element.

numbers=[13,20,0,1,-9]

min=numbers[0]
max=numbers[0]

for number in numbers :
    if number < min:
        min= number
    if number> max :
        max= number
    
print(f" task1.13 : \n the min = {min} , the max = {max} \n ")

#task1.14 :Sort your list in descending order.

for j in range(len(numbers)):
    for i in range(len(numbers)-1) :
        temp=numbers[i]
        if numbers[i+1] > numbers[i] :
            temp=numbers[i]
            numbers[i]=numbers[i+1]
            temp=numbers[i+1]=temp

print(f" task 1.14 : \n sorted list descending : \n methode 1 : {numbers} \n")

numbers.sort(reverse=True)

print(f" methode 2 : {numbers} \n")


#task1.15 : Test this code and try to explain it: [x // 2 if x % 2 == 0 else x * 2 for x in [42, 3, 4, 18, 3, 10]]

result = [x // 2 if x % 2 == 0 else x * 2 for x in [42, 3, 4, 18, 3, 10]]

print(f" task1.15 : \n {result}\n ")

#in each x in the list
#if x % 2 == 0 : x is even -> x //2 : floor division 
#else : x is odd -> x*2 
#[42, 3, 4, 18, 3, 10] -> [21, 6, 2, 9, 6, 5]

#task1.16
#Write a program that deletes all the duplicated elements in a list

def delete_duplicates(my_list):
    result = []

    for element in my_list:
        if element not in result:
            result.append(element)

    return result


# Test 1
my_list = [1, 1, 1, 1, 2, 2, 2, 2, 2]
print(delete_duplicates(my_list))

# Test 2
my_list = [42, '42', 42.0, 21+21, 42*10/10]
print(delete_duplicates(my_list))



