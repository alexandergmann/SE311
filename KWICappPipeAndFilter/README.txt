Alexander Mann

The directory structure is just the default from eclipse, where all code is in src. Any input files must be in this directory or have their full path defined.
The output of this program will go into a txt file in this directory called "kwicresults.txt".

To run:
java KWICapp –f myfile.txt

or

java KWICapp –s stopwords.txt –f myfile.txt


To help with simulating a pipe and filter architecture I created a class called pipe, which has an ArrayList<ArrayList<Strings>> that when the pipe is read, returns this list of "lines". 
Then you write to the pipe, it replaces that object with a that the ArrayList<ArrayList<String>> that is inputed.
Then I created a interface for filters so that all filters must implement, and that each filter has a filter function that accepts a pipe and returns a pipe.
From here my program takes the arguements, decides if it has enough args, puts them into the pipe, and send the pipe object to the first filter by calling the filter function, which is the input filter.
Then after input is gotten from files, it outputs a pipe, which is then used and sent to the shifter filter, which shifts the lines and then outputs a pipe object that's then sent to the sorter, which outputs another pipe object which is sent to the output filter.
This helps reflect the architecture because the data is only ever flowing one way, and each time the data is being passed from one filter to another, it's being done through a connecting "pipe".
