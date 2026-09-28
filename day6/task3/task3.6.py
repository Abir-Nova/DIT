names=["Joe", "William", "Jack", "Averell"]

# shortest --> longest 

print( f"sorting shortest --> longest : \n {sorted(names, key=len)}")

# longest --> shortest 

print(f"sorting longest --> shortest : \n {sorted(names, key=len, reverse=True)}")

# sort the names according to their length.