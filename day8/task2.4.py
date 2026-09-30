import turtle 

screen = turtle.Screen()
pencil=turtle.Turtle()

distance=1

for i in range(200):
    pencil.forward(distance)
    pencil.right(20)
    distance+=0.5

screen.exitonclick()
