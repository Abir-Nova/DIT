# task 1.4
# Prompt the user for a string:
# ✓ if it's ”open sesame”, display ”access granted”;
# ✓ if it's ”will you open, you goddamn !@&/°”, display ”access fucking granted”;
# ✓ else, display ”permission denied”.

while True : 
    code = input ("enter the code : ")
    if code == "open sesame":
        print("access granted")
        break
    elif code == "will you open, you goddamn !@&/°":
        print("access fucking granted")
        break
    else:
        print("permission denied")
