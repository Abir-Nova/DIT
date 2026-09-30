import turtle 

toto = turtle.Screen()  #Screen() creates the drawing window --> We store that window inside a variable called toto

toto.bgcolor("black") #  change the background color of our screen to black 

titi = turtle.Turtle() # This creates the actual turtle that will draw --> we store ot in titi

titi.color("red") # we change the turtle drawing color to red

for i in range(3): # repeat 3 times : from 0 to 3 excluded : 0 --> 2
    titi.right(90) # the turtle turns 90° to the right
    titi.circle(42) # the turtle draws a circle with the radius 42 pixels 

toto.exitonclick() # Keep the window open until the user clicks on it

