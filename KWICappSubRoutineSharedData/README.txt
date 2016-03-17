Alexander Mann

The directory structure is just the default from eclipse, where all code is in src. Any input files must be in this directory or have their full path defined.
The output of this program will go into a txt file in this directory called "kwicresults.txt".

To run:
java KWICapp –f myfile.txt

or

java KWICapp –s stopwords.txt –f myfile.txt

This program uses runs the kwic algorithm on a set of lines using the subroutine and shared data structure. It is implemented using one class and a single global variable that is an ArrayList<ArrayList<String>>. The reason this represents the Main program and subroutine style is because it is implemented in one class with a single global variable. Each subroutine does the same thing as the classes did in the previous assignments. The main function drives the whole program by calling each of these subroutines.  Each subroutine uses the shared data structure, by reading the shared data, doing something and overwriting the structure with the output of the actions it has performed if it has any. Since the program is designed like this, that means only a single data structure is used for all the methods.  With these attributes and this design, it is similar to programs implemented using the Main Program and Subroutine architecture.  