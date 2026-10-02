import pygame
import random
import time
import os
import sys
import argparse

# CONFIGURATION

WIDTH = 1380
HEIGHT = 800

FPS = 60

# Colors
BACKGROUND = (42, 47, 48)
PANEL = (55, 60, 61)
WHITE = (245, 245, 245)
BLACK = (20, 20, 20)
RED = (220, 50, 50)
GREEN = (60, 190, 100)
YELLOW = (230, 190, 60)
GRAY = (150, 150, 150)
DARK_GRAY = (75, 80, 81)
LIGHT_GRAY = (210, 210, 210)
BLUE = (70, 130, 200)

# DIFFICULTIES

DIFFICULTIES = {
    "Easy": {
        "attempts": 8,
        "penalties": 15,
        "time": 90,
        "hint_cost": 1
    },

    "Medium": {
        "attempts": 6,
        "penalties": 12,
        "time": 60,
        "hint_cost": 2
    },

    "Hard": {
        "attempts": 5,
        "penalties": 10,
        "time": 40,
        "hint_cost": 3
    }
}

# LOAD WORDS

def load_words(filename):

    try:

        with open(filename, "r", encoding="utf-8") as file:

            words = []

            for line in file:

                word = line.strip().lower()

                if word == "":
                    continue

                if " " in word:
                    continue

                if not word.isalpha():
                    continue

                words.append(word)

        if len(words) == 0:
            print("Error: no valid words found.")
            return []

        return words

    except FileNotFoundError:

        print(f"Error: file '{filename}' not found.")
        return []

    except PermissionError:

        print(f"Error: cannot read '{filename}'.")
        return []

    except OSError:

        print("Error: could not read word file.")
        return []

# LEADERBOARD

LEADERBOARD_FILE = "leaderboard.txt"

def load_leaderboard():

    scores = []

    #checks if the leaderboard file exists

    if not os.path.exists(LEADERBOARD_FILE):
        return scores

    try:

        with open(LEADERBOARD_FILE, "r", encoding="utf-8") as file:

            for line in file:
                parts = line.strip().split(",")

                if len(parts) != 3:
                    continue

                name = parts[0]

                try:
                    score = int(parts[1])
                    date = parts[2]

                    scores.append({
                        "name": name,
                        "score": score,
                        "date": date
                    })

                except ValueError:
                    continue

    except OSError:
        pass

    scores.sort(key=lambda x: x["score"], reverse=True)

    return scores[:10]


def save_score(name, score):

    today = time.strftime("%Y-%m-%d")

    try:
        #"a" means append : It adds new data without deleting existing scores

        with open(LEADERBOARD_FILE, "a", encoding="utf-8") as file:

            file.write(f"{name},{score},{today}\n")

    except OSError:
        pass

# FONT HELPERS

def get_font(size, bold=False):

    return pygame.font.SysFont(
        "Gothic",
        size,
        bold=bold
    )

# DRAW TEXT

# This function draws text on a given surface at specified coordinates. It can center the text if needed.
def draw_text(surface, text, font, color, x, y, center=False):

    rendered = font.render(text, True, color)
    # Converts the text into an image that Pygame can display
    rect = rendered.get_rect()
    #Gets the rectangle surrounding the text

    if center:
        rect.center = (x, y)

    else:
        rect.topleft = (x, y)

    surface.blit(rendered, rect)

    return rect

# BUTTON

class Button:

    def __init__(
        self,
        x,
        y,
        width,
        height,
        text,
        color=DARK_GRAY
    ):

        self.rect = pygame.Rect(
            x,
            y,
            width,
            height
        )

        self.text = text # stores the text displayed on the button
        self.color = color # stores the color of the button

    def draw(self, surface): # draws the button on the given surface

        mouse_position = pygame.mouse.get_pos() # gets the current position of the mouse cursor

        color = self.color

        if self.rect.collidepoint(mouse_position): # checks if the mouse cursor is over the button's rectangle

            color = tuple(            # increases the brightness of the button's color when hovered over by adding 25 to each RGB component, but ensures that the value does not exceed 255
                min(c + 25, 255)
                for c in color
            )

        pygame.draw.rect(   # draws the button's rectangle on the given surface with the specified color, dimensions, and rounded corners
            surface,
            color,
            self.rect,
            border_radius=8
        )

        pygame.draw.rect( # draws the border of the button's rectangle on the given surface with a light gray color, a thickness of 2 pixels, and rounded corners
            surface,
            LIGHT_GRAY,
            self.rect,
            2,
            border_radius=8
        )

        font = get_font(24, True)

        draw_text(           # draws the button's text on the given button's rectangle, centered both horizontally and vertically, using the specified font and white color
            surface,
            self.text,
            font,
            WHITE,
            self.rect.centerx,
            self.rect.centery,
            True
        )

    def clicked(self, position): # checks if the button was clicked by checking if the given position (usually the mouse click position) is within the button's rectangle. Returns True if clicked, otherwise False.

        return self.rect.collidepoint(position)

# HANGMAN DRAWING

def draw_hangman(surface, mistakes): # draws the hangman figure on the given surface based on the number of mistakes made by the player. The more mistakes, the more parts of the hangman are drawn.

    # Gallows are always drawn, regardless of mistakes

    pygame.draw.line(    # draws the base of the gallows
        surface,         # draw a white line from (90, 550) to (330, 550) with a thickness of 8 pixels
        WHITE,
        (90, 550),
        (330, 550),
        8
    )

    pygame.draw.line(
        surface,
        WHITE,
        (130, 550),
        (130, 130),
        8
    )

    pygame.draw.line(
        surface,
        WHITE,
        (130, 130),
        (300, 130),
        8
    )

    pygame.draw.line(
        surface,
        WHITE,
        (300, 130),
        (300, 180),
        8
    )

    # Extra diagonal support

    pygame.draw.line(
        surface,
        WHITE,
        (130, 220),
        (220, 130),
        6
    )

    # Head

    if mistakes >= 1:    

        pygame.draw.circle(    
            surface,
            WHITE,
            (300, 220),
            42,
            5
        )

    # Body

    if mistakes >= 2:

        pygame.draw.line(
            surface,
            WHITE,
            (300, 262),
            (300, 390),
            6
        )

    # Left arm

    if mistakes >= 3:

        pygame.draw.line(
            surface,
            WHITE,
            (300, 290),
            (245, 350),
            6
        )

    # Right arm

    if mistakes >= 4:

        pygame.draw.line(
            surface,
            WHITE,
            (300, 290),
            (355, 350),
            6
        )

    # Left leg

    if mistakes >= 5:

        pygame.draw.line(
            surface,
            WHITE,
            (300, 390),
            (245, 475),
            6
        )

    # Right leg

    if mistakes >= 6:

        pygame.draw.line(
            surface,
            WHITE,
            (300, 390),
            (355, 475),
            6
        )

    # Dead eyes

    if mistakes >= 6:

        pygame.draw.line(
            surface,
            RED,
            (285, 205),
            (295, 215),
            3
        )

        pygame.draw.line(
            surface,
            RED,
            (295, 205),
            (285, 215),
            3
        )

        pygame.draw.line(
            surface,
            RED,
            (305, 205),
            (315, 215),
            3
        )

        pygame.draw.line(
            surface,
            RED,
            (315, 205),
            (305, 215),
            3
        )

# KEYBOARD

class Keyboard:

    def __init__(self):

        self.buttons = []

        letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        start_x = 500
        start_y = 480

        button_width = 45
        button_height = 42

        gap = 8

        for index, letter in enumerate(letters):

            row = index // 9  # calculates the row number based on the index of the letter in the alphabet. Each row contains 9 letters, so dividing the index by 9 gives the row number
            column = index % 9 # calculates the column number based on the index of the letter in the alphabet. The modulo operator (%) gives the remainder when the index is divided by 9, which corresponds to the column number within the row

            x = start_x + column * (button_width + gap) # calculates the x-coordinate of the button based on the starting x-coordinate, the column number, the button width, and the gap between buttons. It ensures that each button is placed correctly in its respective column
            y = start_y + row * (button_height + gap) # calculates the y-coordinate of the button based on the starting y-coordinate, the row number, the button height, and the gap between buttons. It ensures that each button is placed correctly in its respective row

            self.buttons.append(
                {
                    "letter": letter.lower(),
                    "rect": pygame.Rect(
                        x,
                        y,
                        button_width,
                        button_height
                    )
                }
            )

    def draw(self, surface, guessed_letters): # draws the keyboard on the given surface, displaying each letter button and indicating whether it has been guessed or not. The guessed letters are displayed in a different color to provide visual feedback to the player.

        font = get_font(20, True)

        for button in self.buttons:

            letter = button["letter"]
            rect = button["rect"]

            if letter in guessed_letters: # if the letter has already been guessed, it is displayed in a darker color to indicate that it is no longer available for selection

                color = DARK_GRAY
                text_color = GRAY

            else:

                color = PANEL
                text_color = WHITE

                if rect.collidepoint(
                    pygame.mouse.get_pos()
                ):
                    color = BLUE

            pygame.draw.rect(
                surface,
                color,
                rect,
                border_radius=5
            )

            pygame.draw.rect(
                surface,
                LIGHT_GRAY,
                rect,
                1,
                border_radius=5
            )

            draw_text(
                surface,
                letter.upper(),
                font,
                text_color,
                rect.centerx,
                rect.centery,
                True
            )

    def get_clicked_letter(self, position):  # returns the letter corresponding to the button that was clicked based on the given position (usually the mouse click position). If no button was clicked, it returns None.

        for button in self.buttons:

            if button["rect"].collidepoint(position):

                return button["letter"]

        return None

# GAME CLASS

class HangmanGame:
    #This class contains the actual Hangman logic , including the target word, guessed letters, attempts left, penalties, time limit, and scoring. It provides methods for guessing letters and words, using hints, checking the game result, updating the game state based on time, and calculating the final score.
    # this is seperate from the visual interface, allowing for a clear separation of game logic and user interface.

    def __init__(self, words, difficulty):

        self.words = words

        self.difficulty = difficulty

        settings = DIFFICULTIES[difficulty]

        self.max_attempts = settings["attempts"]

        self.max_penalties = settings["penalties"]

        self.time_limit = settings["time"]

        self.hint_cost = settings["hint_cost"]

        self.reset() # reset all game variable 

    def reset(self):

        self.target = random.choice(self.words)

        self.guessed_letters = set() # creates an empty set to store the letters that the player has guessed so far. A set is used to ensure that each letter is stored only once, preventing duplicates.   

        self.attempts_left = self.max_attempts

        self.penalties = 0

        self.start_time = time.time()

        self.finished = False

        self.won = False

        self.score = 0

        self.message = ""

        self.message_timer = 0

        self.hint_used = 0

    
    # DISPLAY WORD
    

    def display_word(self):

        result = ""

        for letter in self.target:

            if letter in self.guessed_letters:

                result += letter.upper()

            else:

                result += "_ "

        return result

    
    # ELAPSED TIME
    

    def remaining_time(self):

        elapsed = time.time() - self.start_time

        remaining = self.time_limit - elapsed

        return max(0, int(remaining))

    # GUESS LETTER

    def guess_letter(self, letter):

        if self.finished:
            return

        letter = letter.lower()

        if letter in self.guessed_letters:

            self.message = "You already guessed that letter."

            return

        self.guessed_letters.add(letter)

        if letter in self.target:

            self.message = "Correct!"

        else:

            self.attempts_left -= 1

            self.message = "Wrong letter!"

        self.check_result() #checks if the game has been won or lost after each guess, updating the game state accordingly.

    # GUESS WORD
    
    def guess_word(self, word):

        if self.finished:
            return

        word = word.lower().strip()

        if word == self.target:

            self.won = True

            self.finished = True

            self.calculate_score()

            self.message = "You guessed the word!"

        else:

            self.penalties += 5

            self.message = "Wrong word! -5 penalties."

            self.check_result()    
    
    # HINT
    
    def use_hint(self):

        if self.finished:
            return

        hidden_letters = [] # This list will store the letters in the target word that have not yet been guessed by the player. It is used to determine which letters can be revealed as hints.

        for letter in self.target:

            if letter not in self.guessed_letters:

                hidden_letters.append(letter)

        if not hidden_letters:

            self.message = "No letters left to reveal."

            return

        if self.penalties + self.hint_cost >= self.max_penalties:

            self.message = "Not enough penalty points for a hint."

            return

        hint = random.choice(hidden_letters)

        self.guessed_letters.add(hint) # adds the revealed hint letter to the set of guessed letters, effectively revealing it to the player.

        self.penalties += self.hint_cost # increases the player's penalty points by the cost of using a hint, which is defined in the difficulty settings. This ensures that using hints has a trade-off in terms of penalties.

        self.hint_used += 1

        self.message = f"Hint revealed: {hint.upper()}"

        self.check_result()
    
    # CHECK RESULT

    def check_result(self):

        if all(
            letter in self.guessed_letters
            for letter in self.target
        ):

            self.won = True

            self.finished = True

            self.calculate_score()

            return

        if self.attempts_left <= 0:

            self.won = False

            self.finished = True

            self.message = f"You lost! Word: {self.target}"

            return

        if self.penalties >= self.max_penalties:

            self.won = False

            self.finished = True

            self.message = f"You lost! Word: {self.target}"
    
    # TIMER CHECK    

    def update(self):

        if self.finished:
            return

        if self.remaining_time() <= 0:

            self.finished = True

            self.won = False

            self.message = f"Time's up! Word: {self.target}"


    # SCORE
    
    def calculate_score(self):

        if not self.won:
            self.score = 0
            return

        remaining = self.remaining_time()

        self.score = (
            self.attempts_left * 100
            + remaining * 5
            - self.penalties * 10
            - self.hint_used * 25
        )

        self.score = max(
            10,
            self.score
        )

# INPUT BOX

class InputBox: # This class represents an input box that allows the player to enter their name after winning the game. It handles user input events, manages the text entered by the player, and provides a method to draw the input box on the screen

    def __init__(self, x, y, width, height):

        self.rect = pygame.Rect(
            x,
            y,
            width,
            height
        )

        self.text = ""

        self.active = False

    def handle_event(self, event):

        if event.type == pygame.MOUSEBUTTONDOWN:
            # The box becomes active if the click was inside it
            self.active = self.rect.collidepoint(
                event.pos
            ) # checks if the mouse click occurred within the input box's rectangle. If it did, the input box becomes active, allowing the player to type their name. If the click was outside the input box, it becomes inactive.
 
        if ( # checks if a key was pressed while the input box is active. If so, it processes the key event to update the text in the input box.
            event.type == pygame.KEYDOWN
            and self.active
        ):

            if event.key == pygame.K_BACKSPACE:

                self.text = self.text[:-1] # removes the last character from the text in the input box, allowing the player to delete characters they have typed.

            elif event.key == pygame.K_RETURN:

                return self.text  # returns the current text in the input box when the Enter key is pressed, indicating that the player has finished entering their name. This allows the game to capture the player's name for saving their score.

            elif event.unicode.isprintable(): # checks if the key pressed corresponds to a printable character (letters, numbers, symbols, etc.). If it is printable, the character is added to the text in the input box, allowing the player to type their name.

                self.text += event.unicode

        return None

    def draw(self, surface, placeholder=""):

        color = BLUE if self.active else LIGHT_GRAY

        pygame.draw.rect(
            surface,
            PANEL,
            self.rect,
            border_radius=6
        )

        pygame.draw.rect(
            surface,
            color,
            self.rect,
            2,
            border_radius=6
        )

        font = get_font(22)

        if self.text:

            draw_text(
                surface,
                self.text,
                font,
                WHITE,
                self.rect.x + 10,
                self.rect.y + 8
            )

        else:

            draw_text(
                surface,
                placeholder,
                font,
                GRAY,
                self.rect.x + 10,
                self.rect.y + 8
            )

# MAIN APPLICATION

class HangmanApp: 
    # This class represents the main application for the Hangman game.
    #It initializes the Pygame library, 
    #sets up the game window, 
    # manages the game state, 
    # handles user input events, 
    # controls the flow of the game screens (menu, difficulty selection, gameplay, leaderboard, etc.). 
    # creates buttons for user interaction and manages the overall game loop.

    def __init__(self, words):

        pygame.init()  # starts the Pygame library, which is necessary for creating the game window, handling events, and rendering graphics.

        pygame.display.set_caption( # sets the title of the game window to "Hangman", which will be displayed in the title bar of the window when the game is running.
            "Hangman"  
        )

        self.screen = pygame.display.set_mode( # creates the main game window with the specified width and height (WIDTH, HEIGHT). This is where all the game graphics and user interface elements will be displayed.
            (WIDTH, HEIGHT)
        )

        self.clock = pygame.time.Clock() # creates a clock object that will be used to control the frame rate of the game. It allows the game to run at a consistent speed regardless of the performance of the computer.

        self.words = words # stores the list of words that will be used in the Hangman game. These words will be randomly selected for the player to guess during gameplay.

        self.running = True # indicates whether the game is currently running. It will be set to False when the player chooses to quit the game.

        self.screen_name = "menu" # keeps track of the current screen being displayed.

        self.difficulty = "Medium" # stores the selected difficulty level.

        self.game = None # represents the current game instance  So no game has started yet.

        self.keyboard = Keyboard() # creates an instance of the Keyboard class, which will be used to display the on-screen keyboard for letter selection during gameplay.

        self.history = [] # stores the history of game results, including the player's name, score, and date. This will be used to display the leaderboard.

        self.player_name = "Player" # stores the name of the player, which will be used for saving scores to the leaderboard.

        self.input_box = InputBox( # creates an instance of the InputBox class, which will be used to allow the player to enter their name after winning the game. The input box is positioned at (360, 450) with a width of 280 pixels and a height of 45 pixels.
            360,
            450,
            280,
            45
        )

        self.create_buttons() # initializes the buttons used in the game, such as the play button, difficulty button, leaderboard button, and others. This method sets up the buttons with their positions, sizes, and labels for user interaction.

    # BUTTONS
    
    def create_buttons(self):

        self.play_button = Button(
            350, #x
            260, #y
            300, #width
            60, #height
            "PLAY" # text
        )

        self.difficulty_button = Button(
            350,
            340,
            300,
            60,
            "DIFFICULTY"
        )

        self.leaderboard_button = Button(
            350,
            420,
            300,
            60,
            "LEADERBOARD"
        )

        self.quit_button = Button(
            350,
            500,
            300,
            60,
            "QUIT"
        )

        self.easy_button = Button(
            350,
            280,
            300,
            55,
            "EASY"
        )

        self.medium_button = Button(
            350,
            350,
            300,
            55,
            "MEDIUM"
        )

        self.hard_button = Button(
            350,
            420,
            300,
            55,
            "HARD"
        )

        self.back_button = Button(
            30,
            680,
            150,
            45,
            "BACK"
        )

        self.hint_button = Button(
            500,
            680,
            150,
            45,
            "HINT"
        )

        self.word_button = Button(
            670,
            680,
            230,
            45,
            "GUESS WORD"
        )

        self.menu_button = Button(
            350,
            520,
            300,
            55,
            "MAIN MENU"
        )

        self.play_again_button = Button(
            350,
            450,
            300,
            55,
            "PLAY AGAIN"
        )

    # MENU
    
    def draw_menu(self):

        self.screen.fill( #Clears the entire screen and fills it with the background color.
            BACKGROUND
        )

        title_font = get_font(
            64,
            True
        )

        draw_text(
            self.screen,
            "HANGMAN",
            title_font,
            WHITE,
            WIDTH // 2,
            100,
            True
        )

        subtitle_font = get_font(22)

        draw_text(
            self.screen,
            "Guess the word before the time runs out!",
            subtitle_font,
            GRAY,
            WIDTH // 2,
            155,
            True
        )

        self.play_button.draw(
            self.screen
        )

        self.difficulty_button.draw(
            self.screen
        )

        self.leaderboard_button.draw(
            self.screen
        )

        self.quit_button.draw(
            self.screen
        )

        draw_text(
            self.screen,
            f"Current difficulty: {self.difficulty}",
            get_font(20),
            YELLOW,
            WIDTH // 2,
            620,
            True
        )

    # DIFFICULTY SCREEN
    
    def draw_difficulty(self):

        self.screen.fill(
            BACKGROUND
        )

        draw_text(
            self.screen,
            "DIFFICULTY",
            get_font(50, True),
            WHITE,
            WIDTH // 2,
            100,
            True
        )

        self.easy_button.draw(
            self.screen
        )

        self.medium_button.draw(
            self.screen
        )

        self.hard_button.draw(
            self.screen
        )

        settings = DIFFICULTIES[
            self.difficulty
        ]

        draw_text(
            self.screen,
            f"Selected: {self.difficulty}",
            get_font(22),
            YELLOW,
            WIDTH // 2,
            550,
            True
        )

        draw_text(
            self.screen,
            f"Attempts: {settings['attempts']}   "
            f"Penalties: {settings['penalties']}   "
            f"Time: {settings['time']}s",
            get_font(20),
            GRAY,
            WIDTH // 2,
            590,
            True
        )

        self.back_button.draw(
            self.screen
        )

    # GAME SCREEN
    
    def draw_game(self):

        self.screen.fill( # clears the entire screen and fills it with the background color, preparing it for drawing the game elements.
            BACKGROUND
        )

        self.game.update() # updates the game state, checking for time expiration and other conditions that may affect the gameplay. It ensures that the game logic is up-to-date before rendering the visual elements.

        
        # Header
        

        draw_text(
            self.screen,
            "HANGMAN",
            get_font(36, True),
            WHITE,
            40,
            25
        )

        draw_text(
            self.screen,
            f"Difficulty: {self.difficulty}",
            get_font(18),
            GRAY,
            40,
            70
        )

        # Timer

        remaining = self.game.remaining_time()

        timer_color = (
            RED
            if remaining <= 10
            else WHITE
        )

        draw_text(
            self.screen,
            f"TIME: {remaining}s",
            get_font(26, True),
            timer_color,
            800,
            30
        )

        # Hangman
        
        mistakes = (  
            self.game.max_attempts
            - self.game.attempts_left
        )

        draw_hangman(
            self.screen,
            mistakes
        )

        
        # Word
        

        word = self.game.display_word()

        draw_text(
            self.screen,
            word,
            get_font(48, True),
            WHITE,
            250,
            580,
            True
        )
        
        # Statistics

        draw_text(
            self.screen,
            f"Attempts: {self.game.attempts_left}",
            get_font(20),
            WHITE,
            500,
            100
        )

        draw_text(
            self.screen,
            f"Penalties: {self.game.penalties}/{self.game.max_penalties}",
            get_font(20),
            WHITE,
            500,
            135
        )

        draw_text(
            self.screen,
            f"Score: {self.game.score}",
            get_font(20),
            YELLOW,
            500,
            170
        )

        # Life bar
        
        bar_x = 500
        bar_y = 210
        bar_width = 400
        bar_height = 25

        pygame.draw.rect(
            self.screen,
            DARK_GRAY,
            (
                bar_x,
                bar_y,
                bar_width,
                bar_height
            ),
            border_radius=5
        )

        life_ratio = ( # percentage of remaining attempts relative to the maximum attempts, used to determine the width of the life bar and its color. A higher ratio indicates more remaining attempts, while a lower ratio indicates fewer attempts left.
            self.game.attempts_left
            / self.game.max_attempts
        )

        pygame.draw.rect(
            self.screen,
            GREEN if life_ratio > 0.4 else RED,
            (
                bar_x,
                bar_y,
                int(bar_width * life_ratio), # calculates the width of the remaining life bar.
                bar_height
            ),
            border_radius=5
        )

        draw_text(
            self.screen,
            "LIVES",
            get_font(16, True),
            WHITE,
            bar_x,
            bar_y - 25
        )

        # Message
        
        if self.game.message:

            message_color = (
                GREEN
                if self.game.won
                else RED
                if self.game.finished
                else WHITE
            )

            draw_text(
                self.screen,
                self.game.message,
                get_font(22, True),
                message_color,
                500,
                260,
                True
            )

        # Keyboard        

        self.keyboard.draw(
            self.screen,
            self.game.guessed_letters
        )

        # Bottom buttons
        
        if not self.game.finished:

            self.hint_button.draw(
                self.screen
            )

            self.word_button.draw(
                self.screen
            )

        else:

            self.play_again_button.draw(
                self.screen
            )

        self.back_button.draw(
            self.screen
        )
    
    # LEADERBOARD
    
    def draw_leaderboard(self):

        self.screen.fill(
            BACKGROUND
        )

        draw_text(
            self.screen,
            "LEADERBOARD",
            get_font(50, True),
            WHITE,
            WIDTH // 2,
            80,
            True
        )

        scores = load_leaderboard()

        if not scores:

            draw_text(
                self.screen,
                "No scores yet.",
                get_font(25),
                GRAY,
                WIDTH // 2,
                250,
                True
            )

        else: # displays the leaderboard scores in a tabular format, showing the rank, player name, score, and date for each entry. It iterates through the top scores and draws them on the screen.
            # Rank + player + score + date

            draw_text(
                self.screen,
                "RANK",
                get_font(20, True),
                YELLOW,
                180,
                160
            )

            draw_text(
                self.screen,
                "PLAYER",
                get_font(20, True),
                YELLOW,
                300,
                160
            )

            draw_text(
                self.screen,
                "SCORE",
                get_font(20, True),
                YELLOW,
                560,
                160
            )

            draw_text(
                self.screen,
                "DATE",
                get_font(20, True),
                YELLOW,
                700,
                160
            )

            # scores = dictionary : index + score

            # Example
            #  index = 0
            #  score = {"name": "Abir", "score": 500, "date": "2023-10-01" }

            for index, score in enumerate(scores):

                y = 205 + index * 40

                draw_text(
                    self.screen,
                    str(index + 1),  # starts from 1, so we add 1 to the index to display the rank correctly.
                    get_font(19),
                    WHITE,
                    190,
                    y
                )

                draw_text(
                    self.screen,
                    score["name"],
                    get_font(19),
                    WHITE,
                    300,
                    y
                )

                draw_text(
                    self.screen,
                    str(score["score"]),
                    get_font(19),
                    WHITE,
                    560,
                    y
                )

                draw_text(
                    self.screen,
                    score["date"],
                    get_font(19),
                    GRAY,
                    700,
                    y
                )

        self.back_button.draw(
            self.screen
        )

    # GAME OVER

    def draw_game_over(self):

        self.screen.fill(
            BACKGROUND
        )

        if self.game.won:

            title = "YOU WON!"

            title_color = GREEN

        else:

            title = "YOU LOST!"

            title_color = RED

        draw_text(
            self.screen,
            title,
            get_font(60, True),
            title_color,
            WIDTH // 2,
            130,
            True
        )

        draw_text(
            self.screen,
            f"The word was: {self.game.target.upper()}",
            get_font(30),
            WHITE,
            WIDTH // 2,
            220,
            True
        )

        draw_text(
            self.screen,
            f"Score: {self.game.score}",
            get_font(28),
            YELLOW,
            WIDTH // 2,
            275,
            True
        )

        draw_text(
            self.screen,
            f"Penalties: {self.game.penalties}",
            get_font(22),
            WHITE,
            WIDTH // 2,
            320,
            True
        )

        if self.game.won:

            draw_text(
                self.screen,
                "Enter your name:",
                get_font(22),
                WHITE,
                WIDTH // 2,
                390,
                True
            )

            self.input_box.draw(
                self.screen,
                "Player"
            )

        self.play_again_button.draw(
            self.screen
        )

        self.menu_button.draw(
            self.screen
        )    
    
    # START GAME

    def start_game(self):

        self.game = HangmanGame( # creates a new instance of the HangmanGame class, initializing it with the list of words and the selected difficulty level. This sets up a new game session with the appropriate settings for gameplay.
            self.words,
            self.difficulty
        )

        self.screen_name = "game" # changes the current screen to the game screen, allowing the player to start playing the Hangman game.

    # EVENTS
    
    def handle_event(self, event):
        
        # GLOBAL        

        if event.type == pygame.QUIT:

            self.running = False

            return

        # MENU
        
        if self.screen_name == "menu":

            if (
                event.type == pygame.MOUSEBUTTONDOWN
            ):
 
                position = event.pos # gets the position of the mouse click event, which will be used to determine if any of the menu buttons were clicked.

                if self.play_button.clicked(position):

                    self.start_game()

                elif self.difficulty_button.clicked(position):

                    self.screen_name = "difficulty"

                elif self.leaderboard_button.clicked(position):

                    self.screen_name = "leaderboard"

                elif self.quit_button.clicked(position):

                    self.running = False

        # DIFFICULTY
        
        elif self.screen_name == "difficulty":

            if event.type == pygame.MOUSEBUTTONDOWN:

                position = event.pos

                if self.easy_button.clicked(position):

                    self.difficulty = "Easy"

                elif self.medium_button.clicked(position):

                    self.difficulty = "Medium"

                elif self.hard_button.clicked(position):

                    self.difficulty = "Hard"

                elif self.back_button.clicked(position):

                    self.screen_name = "menu"
    
        # LEADERBOARD
    
        elif self.screen_name == "leaderboard":

            if event.type == pygame.MOUSEBUTTONDOWN:

                if self.back_button.clicked(event.pos):

                    self.screen_name = "menu"

        # GAME    

        elif self.screen_name == "game":

            if event.type == pygame.KEYDOWN:

                # Keyboard letter

                if (
                    event.unicode.isalpha()
                    and len(event.unicode) == 1
                ):

                    self.game.guess_letter(
                        event.unicode.lower()
                    )

                # ESC

                elif event.key == pygame.K_ESCAPE:

                    self.screen_name = "menu"

            if event.type == pygame.MOUSEBUTTONDOWN:

                position = event.pos

                # Click keyboard

                letter = ( # Determines which letter was clicked
                    self.keyboard
                    .get_clicked_letter(position)
                )

                if letter:

                    self.game.guess_letter(
                        letter
                    )

                # Hint

                if self.hint_button.clicked(position):

                    self.game.use_hint()

                # Guess word : Switches to the word-entry screen

                if self.word_button.clicked(position):

                    self.screen_name = "guess_word"

                # Back

                if self.back_button.clicked(position):

                    self.screen_name = "menu"

                # Play again after finished

                if (
                    self.game.finished
                    and self.play_again_button.clicked(
                        position
                    )
                ):

                    self.start_game()

        # GUESS WORD
        
        elif self.screen_name == "guess_word": # here the input box is active, allowing the player to type in their guess for the complete word. The event handling checks for key presses and mouse clicks to manage the input box and navigate back to the game screen.

            if event.type == pygame.KEYDOWN:

                if event.key == pygame.K_ESCAPE:

                    self.screen_name = "game"

            result = self.input_box.handle_event(
                event
            )

            if result is not None:

                self.game.guess_word(result)

                self.input_box.text = ""

                self.screen_name = "game"

            if event.type == pygame.MOUSEBUTTONDOWN:

                if self.back_button.clicked(event.pos):

                    self.screen_name = "game"
    
        # GAME OVER
    
        elif self.screen_name == "game_over":

            result = self.input_box.handle_event(
                event
            )

            if result is not None:

                name = result.strip()

                if name == "":
                    name = "Player"

                save_score(
                    name,
                    self.game.score
                )

                self.screen_name = "leaderboard"

            if event.type == pygame.MOUSEBUTTONDOWN:

                if self.play_again_button.clicked(
                    event.pos
                ):

                    self.start_game()

                elif self.menu_button.clicked(
                    event.pos
                ):

                    self.screen_name = "menu"

    # GUESS WORD SCREEN    

    def draw_guess_word(self):

        self.screen.fill(
            BACKGROUND
        )

        draw_text(
            self.screen,
            "GUESS THE WORD",
            get_font(50, True),
            WHITE,
            WIDTH // 2,
            180,
            True
        )

        draw_text(
            self.screen,
            f"The word has {len(self.game.target)} letters",
            get_font(22),
            GRAY,
            WIDTH // 2,
            250,
            True
        )

        self.input_box.draw(
            self.screen,
            "Type the complete word..."
        )

        self.back_button.draw(
            self.screen
        )

        draw_text(
            self.screen,
            "Press ENTER to submit",
            get_font(18),
            GRAY,
            WIDTH // 2,
            530,
            True
        )
    
    # SCREEN UPDATE
    
    def draw(self):

        if self.screen_name == "menu":

            self.draw_menu()

        elif self.screen_name == "difficulty":

            self.draw_difficulty()

        elif self.screen_name == "game":

            self.draw_game()

            if self.game.finished:

                # Store score once

                if not hasattr(
                    self.game,
                    "score_saved"
                ):

                    self.game.score_saved = True

                    self.history.append({
                        "won": self.game.won,
                        "score": self.game.score,
                        "word": self.game.target,
                        "penalties": self.game.penalties
                    })

                    if self.game.won:

                        self.screen_name = "game_over"

        elif self.screen_name == "guess_word":

            self.draw_guess_word()

        elif self.screen_name == "leaderboard":

            self.draw_leaderboard()

        elif self.screen_name == "game_over":

            self.draw_game_over()

        pygame.display.flip()
    
    # MAIN LOOP

    def run(self):

        while self.running:

            for event in pygame.event.get():

                self.handle_event(event)

            self.draw()

            self.clock.tick(FPS)

        pygame.quit()

# MAIN

def main():

    parser = argparse.ArgumentParser(
        description="Graphical Hangman game"
    )

    parser.add_argument(
        "filename",
        help="Word file"
    )

    args = parser.parse_args()

    words = load_words(
        args.filename
    )

    if not words:

        sys.exit(1)

    app = HangmanApp(
        words
    )

    app.run()

if __name__ == "__main__":
    main()