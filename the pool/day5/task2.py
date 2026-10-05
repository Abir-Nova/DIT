#task2.1
pokemons = {
    "pikachu":"elecric",
    "Bulbasaur":"grass",
    "Charmander":"fire"
}
print("task2.1")
print(pokemons)

# task 2.2 : Inside pokemons, add the key Blaziken with the value Fire. Then, print this dictionary
pokemons["blaziken"]= "fire"
print("task2.2")
print(pokemons)

# task 2.3 : In your dictionary pokemons, add the key Pikachu with the value ["Pichu", "Raichu"].
#Look at what is happening. What do you observe? What do you make of it?

pokemons["pikachu"]= ["pichu","raichu"]
print("task2.3")
print(pokemons)

# we lost the first values 

# task 2.4
# Let's do it again, but differently. Start by creating the dictionary types.
# The keys are strings representing Pokemon types (e.g., 'Electric', 'Grass', 'Fire').
# For each key, add an empty list as value (for now). Finally, print the dictionary.

types = {
    "elecric": [],
    "grass":[],
    "fire":[]
}
print("task2.4")
print(types)

# task 2.5
# In the values/lists of your dictionary types, add each Pokemon from this list [”Pikachu”, ”Bulbasaur”,
# ”Charmander”, ”Leafeaon”, ”Scovillain”] to the list of its type.

types["electric"]= "pikachu"
types["grass"]= ["Bulbasaur", "Leafeaon", "Scovillain"]
types["fire"]= ["Charmander"]

print("task2.5")
print(types)

# task2.6 : Print all the keys (Pokemon types) contained in your previous dictionary.
# already done in task2.5

# task 2.7 :Using your previous dictionary, retrieve the type of ”Pikachu”.

result = pokemons["pikachu"]
print("task2.7")
print( f"methode 1 :{result}")

result = pokemons.get("pikachu")
print( f"methode 2 :{result}")

#task 2.8 : Store this dictionary into the variable superheroes.
# Then, print the value of Superman's city.


superheroes  = {
    "Batman" : {
        "id": 1,
        "aliases": ["Bruce Wayne", "Dark knight"],
        "location": {
            "number" : 1007,
            "street": "Mountain Drive",
            "city": "Gotham"
        }
    },
    "Superman" : {
        "id": 2,
        "aliases": ["Kal-El", "Clark Kent", "The Man of Steel"],
        "location": {
            "number" : 344,
            "street": "Clinton Street",
            "apartment": "3D",
            "city": "Metropolis"
        }
    },
    }

city = superheroes["Superman"]["location"]["city"]
print("task2.8")
print(city)

# task2.9

# Inside the dictionary superheroes, add:
# ✓ Caped Crusader inside Batman's aliases;
# ✓ a new superhero Wolverine, with 3 as id, and no aliases nor location.

superheroes["Batman"]["aliases"]=["Bruce Wayne", "Dark knight", "Caped Crusader"]
superheroes.update({"Wolverine": {"id": 3}})

print("task2.9")
print(superheroes)

#task 2.10 
# For each superhero contained in superheroes, enumerate all the aliases she/he has.
# Your result should look like this:

# Batman:
# Bruce Wayne
# Dark knight
# Caped Crusader

# Superman:
# Kal-El
# Clark Kent
# The Man of Steel

# Wolverine:
# No aliases found


# task 2.10
print("task2.10")

for superhero, info in superheroes.items():
    print(f"{superhero}:")
    
    aliases = info.get("aliases", [])
    
    if aliases:
        for alias in aliases:
            print(alias)
    else:
        print("No aliases found")
    
    print("\n")


#Task 2.11
#Inside this dictionary, get the key with the maximum value:


dict1 = {
    "dalmatians": 101,
    "pi": 3.14,
    "beast": 666,
    "life": 42,
    "googol": 10**100,
    "jordan": 23,
    "life, the universe and everything": 42,
    "emergency": 911,
    "euler": 2.71828
}

max_value = 0
max_key = ""

for key, value in dict1.items():
    if value > max_value:
        max_value = value
        max_key = key

print(max_key)

