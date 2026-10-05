#task1.1 : Dig this piece of code and try to predict the values of the output. Then, run it to check it.

def f1():
    return 42
def f2(x):
    return 2 * x
print(f1(), f2(5) + f1())
#42 10+42  
#42 52

#task1.2

#Using the following functions, display a lettuce-tomato-double ham sandwich in your terminal.

def bread():
    print("<//////////>")
def lettuce():
    print("~~~~~~~~~~~~")
def tomato():
    print("O O O O O O")
def ham():
    print("============")

bread()
lettuce()
tomato()
ham()
ham()
bread()


#task1.3

#Write a function that takes a number of sandwiches to prepare as a parameter. Then displays as many
#sandwiches as requested if the parameter is correct, and I can't do this! if it's not (such as 3.14).


def prepare_normal_sandwich(number):
    if type(number) != int:
        print("I can't do this")
        return
    for i in range(number):
        bread()
        lettuce()
        tomato()
        ham()
        ham()
        bread()


# task1.4

# Add a parameter to provide the possibility for a veg sandwich (double vegetables + no ham).
# If this option isn't specified, the sandwich must be a lettuce-tomato-double ham one by default.

def prepare(number,choice="normal"): 
    if type(number) != int:
        print("I can't do this")
        return

    if choice.lower() == "vegetarian":
        for i in range(number):
            bread()
            lettuce()
            lettuce()
            tomato()
            tomato()
            bread()
    else :
        prepare_normal_sandwich(number)
