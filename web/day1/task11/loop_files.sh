# !/bin/bash 

for file in *.md
do 
	echo "this is a new line." >> "$file"
done


#  for file in *.md : 
#  This loops over all files ending in .md in the current directory.


# >> : append 

# "$file" → the current Markdown file
