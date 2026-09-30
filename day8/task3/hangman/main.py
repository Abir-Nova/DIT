import pygame

pygame.init() # intialize pygame 

screen = pygame.display.set_mode((600, 600)) 
# create the game window : (width , height) pixels  --> store that window in a variable called screen 
# screen represents the game window/surface where we'll draw things

background = pygame.image.load("assets/bg.jpg") # load the background image into pygame --> we store it in the background variable

screen.blit(background, (0, 0)) 
# draw/copy this image onto the screen at this position --> (0,0) means the top-left corner 
#Draw background onto screen, starting at position (0, 0)

# Function to draw the stickman 
def draw_stickman(): 

    
    #Head 
    pygame.draw.circle(screen, (0, 0, 0), (300, 200), 50) 
    
    # syntax : pygame.draw.circle(surface, color, position, radius)
    # screen       -> where to draw
    # (0, 0, 0)    -> color :  black 
    # (300, 200)   -> center of the circle
    # 50           ->  radius

    # Body 
    pygame.draw.line(screen, (0, 0, 0), (300, 250), (300, 400), 5) 
    
    # syntax :pygame.draw.line(surface, color, start_position, end_position, width/ thickness)
    
    # Left arm 
    pygame.draw.line(screen, (0, 0, 0), (300, 280), (220, 350), 5) 
    
    # Right arm 
    pygame.draw.line(screen, (0, 0, 0), (300, 280), (380, 350), 5) 
    
    # Left leg 
    pygame.draw.line(screen, (0, 0, 0), (300, 400), (230, 500), 5) 
    
    # Right leg 
    pygame.draw.line(screen, (0, 0, 0), (300, 400), (370, 500), 5)

# Draw the stickman 
draw_stickman()

pygame.display.flip() 
# This updates the window so the image becomes visible
# This tells Pygame: "Take everything I've drawn and show it on the actual window"

running = True # Create a variable controlling the game loop -- > We're going to create a loop that keeps the game running

while running:# while running is true  = keep executing this code
    # Pygame constantly receives events from the operating system
    # Mouse click , keyboard press , window close , mouse movement ...
    for event in pygame.event.get(): # gets the events that have happened checking them one by one 
        if event.type == pygame.QUIT: # if this event : the window close event  --> If the user clicked the window's close button...
            running = False # stop the game loop

pygame.quit() # shut down pygame and cleans up the pygame modules