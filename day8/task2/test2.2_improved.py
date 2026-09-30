import turtle 
import time 
screen = turtle.Screen()
pencil=turtle.Turtle()

screen.bgcolor("black")
pencil.color("red")
for i in range(5):
    pencil.right(90)
    time.sleep(3)
    pencil.circle(40)
    time.sleep(3)

screen.exitonclick()