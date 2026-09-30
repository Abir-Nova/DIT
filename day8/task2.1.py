# turtle is included with Python's standard library
#we don't need to install turtle with pip : 
# I checked with this cmd : python -c "import turtle; print('Turtle works!')"

import turtle      

for i in range(4): #cause a square has 4 sides
    turtle.forward(50) # Move forward 50 pixels.
    turtle.right(90) #Turn 90°

turtle.done() # The drawing is finished but keep the turtle graphics window open so I can see it