import pygame

pygame.init() # intialize pygame 

screen = pygame.display.set_mode((600, 600)) 
# create the game window : (width , height) pixels  --> store that window in a variable called screen 
# screen represents the game window/surface where we'll draw things

background = pygame.image.load("assets/bg.jpg") # load the background image into pygame --> we store it in the background variable

screen.blit(background, (0, 0)) 
# draw/copy this image onto the screen at this position --> (0,0) means the top-left corner 
#Draw background onto screen, starting at position (0, 0)
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