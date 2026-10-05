import turtle 

screen = turtle.Screen()
pencil=turtle.Turtle()

def draw_polygon(sides):
    angle = 360/sides
    for i in range(sides):
        pencil.forward(50)
        pencil.right(angle)

while True :

    sides = int(input("enter the number of sides : 0 to quit "))
    
    if sides==0:
        break

    draw_polygon(sides)

screen.exitonclick()
