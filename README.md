# Introduction

Parses the HTML at http://www.craigslist.org/about/sites.

Note that the program does not go out to the site and download it.  It expects the output to be passed in on stdin (or in a file).

# Command Line Arguments

    java -jar city-list-scraper-1.0-SNAPSHOT.jar [input file or "-" for stdin [output file or "-" for stdout]]

The first parameter is the name of the input file.  If you specify "-" or you specify no command line arguments at all, the program will read from stdin.

The second paramter is the name of the output file.  If you specify "-" or you provide fewer than 2 arguments, the program will write to stdout.

# Building

    mvn install

# Running

Perhaps the best way to run it would be

    curl http://www.craigslist.org/about/sites | java -jar city-list-scraper-1.0-SNAPSHOT.jar - cities.xml

