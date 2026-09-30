import turtle 

screen = turtle.Screen()
pencil= turtle.Turtle()

screen.bgcolor("black")
pencil.color("yellow")

pencil.begin_fill() #for solid objects

points = [
    (-200, 0), (-170, 40), (-120, 60), (-70, 70),
    (-65, 35), (-45, 25), (-30, 45), (-20, 20),
    (0, 50), (20, 20), (30, 45), (45, 25),
    (65, 35), (70, 70), (120, 60), (170, 40),
    (200, 0), (170, -40), (120, -55), (70, -65),
    (60, -30), (40, -35), (20, -65), (0, -40),
    (-20, -65), (-40, -35), (-60, -30),
    (-70, -65), (-120, -55), (-170, -40)
]

pencil.penup() # when we don't want a line # stop drawing 
pencil.goto(points[0]) # move 
pencil.pendown() #resume drawing 

for point in points[1:]:
    pencil.goto(point)

pencil.goto(points[0])
pencil.end_fill()

screen.exitonclick()

# Circle → circle()
# Line → forward()
# Rectangle → forward() + right()
# Polygon → goto()
# Filled shape → begin_fill() / end_fill()
# Separate object → penup() / pendown()