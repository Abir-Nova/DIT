#task1.1
# Write a function analyze_grades(grades) that takes a list of numbers representing student scores. Using the
# built-in functions you learned today, the function must calculate and print:
# - The highest grade in the class.
# - The lowest grade in the class.
# - The average grade.

def avg(grades):
    return sum(grades)/len(grades)

def analyse_grades(grades):
    print(f"the highest grade in the class = {max(grades)}\n")
    print(f"the lowest grade in the class = {min(grades)}\n")
    print(f"the average grade in the class = {avg(grades)}\n")


# Example use case 

grades = [18.75,16,10,9.75,18]
analyse_grades(grades)


    # task1.2
# Write a recursive function reverse_string(text) that takes a string of characters and returns it completely backwards
# Think about the base case: what should the function return if the string is empty? For the recursive
# step, try separating the very first character from the rest of the string.

def reverse_string(text):
    if (text == ""):
        return ""
    return reverse_string(text[1:])+text[0]

# example
text="Epitech"
result=reverse_string(text)
print(f"the reversed is : {result} ")

#task1.3:
    
def make_custom_sandwich(ingredients):


    # Check if ham OR tomato is present
    has_filling = any(ingredient == "ham" or ingredient == "tomato"
                      for ingredient in ingredients)

    # Check if there are at least two breads
    has_bread = ingredients.count("bread") >= 2

    # Check if the sandwich is valid
    if not has_bread:
        print("Error: A sandwich needs top and bottom bread!")
        return

    if not has_filling:
        print("Error: ingrédients non valides : no ham nor tomato")
        return

    # Print each ingredient
    for ingredient in ingredients:
        print(ingredient)

        
# example : 

sandwich1 =  ["bread", "lettuce", "tomato", "ham", "bread"]
sandwich2 =  ["bread", "lettuce", "tomato", "ham", "tomato"] # 1 bread 
sandwich3 =  ["bread", "lettuce", "bread"] # no tomato no ham
make_custom_sandwich(sandwich1)
make_custom_sandwich(sandwich2)
make_custom_sandwich(sandwich3)