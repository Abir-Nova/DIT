#!/bin/bach

for ((i=1;i<=$1;i++))
do 
	mkdir -p "day$(printf "%02d" "$2")/task$(printf "%02d" "$i")"
done


# %02d means :  
# Print an integer using at least 2 digits, adding a 0 in front when necessary.


