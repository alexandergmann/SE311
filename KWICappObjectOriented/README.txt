Alexander Mann

The director structure is just the default from eclipse, where all code is in src. Any input files must be in this directory or have their full path defined.
The output of this program will go into a txt file in this directory called "kwicresults.txt".

To run:
java KWICapp –f myfile.txt

or

java KWICapp –s stopwords.txt –f myfile.txt


This program shows object oriented design because each class is decoupled from each other. The reading from files is done in its own class in a generic way away from everything else, while also being independent from everything else.

For example, if i changed the way kwics were compared to be sorted by the values instead of keys, or I would only have to change it in the kwicmap class. Also, If i wanted to change the type of output or input to console, since they are using interfaces, the only thing that would need to change are the declarations in the main file.