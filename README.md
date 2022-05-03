Homework assignment no. 3, Labeled CSV Data Sorter 
====================================

**Publication date:**  May 4th, 2022  
**Submission deadline:** May 20th, 2022  
**Points:** 15 points in total (10 points for passing tests, 5 points for clean  and efficient implementation)


Change Log
-----------

General Information
-------------------
**Note:** Unlike in previous assignments, this time you may modify ``pom.xml`` by adding dependencies.

The goal of this homework is to implement a simple application capable of sorting labeled data from CSV file into a set of new csv files in output directory.

The application should support the following command line options.

| CLI option (long) | CLI option  (short)   | Arguments | Default   | Required  | Description   |
| ------            | ------                | ------    | ------    | ------    | ------        |
| --help            |                       |           |           | false     | Print application usage |
| --input           | -i                    | String    |           | true      | Path of data csv file  |   
| --output          | -o                    | String    |           | true      | Path of output directory |
| --filters         | -f                    | String    |           | true      | Path of filter csv file |
| --column          | -c                    | String    | labels    | false     | Name of the column with labels |
| --delimiter       | -d                    | String    | ,         | false     | Delimiter used by csv files |
| --charset         |                       | String    | UTF-8     | false     | Charset used by input (and output) files |

### Input Format
The application will be able to work with any ``input`` csv file compliant with  the following conditions

- First line in the file is a header (columns are named)  
- File has a column with space-separated labels.

The following is an example of ``input`` csv file
```csv
name,date_published,hits,labels
Australian Fauna,11.8.2021,123,snakes spiders animals australia danger
Danger Noodles,9.1.2022,42,snakes anaconda viper
In the Skies,8.4.1994,1432,ravens birds
Too Lazy, 9.11.2019, 5322,pandas bamboo endangered fluffy
Man's Best Friend, 13.4.2014,943,animals dogs people history
```

Beside the ``input`` csv file, the application also requires a csv file with ``filters``.  
The schema for ``filters`` csv file can be seen in the following example

```csv
name,expression
birds, birds | parrots | ravens
eggs, turtles | snakes | birds
social, society | people & animals
```
The first column (``name``) is the name of the filter. This value is also used as the name of the output ``csv`` file (e.g.``eggs.csv``).    
The second column (``expression``) is a logical expression as defined in [previous homework](https://gitlab.fi.muni.cz/pb162/2022-hw02-labels)

### Project structure

The structure of the project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.hw03``` contains classes and interfaces provided as a part of the assignment.

- **Do not modify or add any classes or subpackages into this package.**

2. Package  ```cz.muni.fi.pb162.hw03.impl``` should contain your implementation.

- **Anything outside this package will be ignored during evaluation.**
- **Do not change the interface of ``LabeledOperations``` class, you should only implement declared methods**

### Names in this document

Unless fully classified name is provided, all class names are relative to the package ```cz.muni.fi.pb162.hw03``` or ```cz.muni.fi.pb162.hw03.impl``` for classes
implemented as a part of your solution.

### Compiling the project

The project can be compiled and packaged in the same way you already know.

```bash

$ mvn clean install
```

The only difference is that unlike with seminar project, this time the checks for missing documentation and style violation will produce an error. You can temporarily
disable this behavior when running this command.

```bash
$ mvn clean install -Dcheckstyle.skip=true
```

You can consult your seminar teacher to help you set the ``checkstyle.skip`` or ``iml.bonus`` properties in your IDE (or just google it).

### Submitting the assignment

The procedure to submit your solution may differ based on your seminar group. However, it should be generally OK to
submit ```target/homework03-2022-1.0-SNAPSHOT-sources.jar``` to the homework vault.

### Running the application

The build descriptor is configured to produce a single runnable jar file located at target/application.jar. The application can be run using the following command
```bash
# Sort data.csv based on filters.csv into /tmp/output directory
$ java -jar application.jar -i data.csv -c labels -f filters.csv -o /tmp/output 

# Listing contents of /tmp/output (assuming data.csv and filters.csv as shown above)
$ ls /tmp/output
birds.csv eggs.csv social.csv

# Show contents of eggs.csv
$ cat /tmp/output/eggs.csv
name,date_published,hits,labels
Australian Fauna,11.8.2021,123,snakes spiders animals australia danger
Danger Noodles,9.1.2022,42,snakes anaconda viper
In the Skies,8.4.1994,1432,ravens birds
```
Implementation Notes
----
Generally speaking, there are no mandatory requirements on the structure of your code as long as the command line interface works correctly.
The use of classes, enums, and interfaces provided as a part of the project skeleton is up to your decision.

### External Dependencies
There are two areas where you may want to utilise external dependencies to save yourself some work. 

- Evaluation of logical label expressions
- Reading of CSV files

The first point could be covered quite nice by using the library from [previous homework](https://gitlab.fi.muni.cz/pb162/2022-hw02-labels).  
If you inspect the ``<dependencies>`` element in ``pom.xml`` you will see there is marked section where you can add the dependency on ``homework02-2022``.  
To add the dependency, put the following inside the ``<dependencies>`` element (in the marked section).

```xml
<dependency>
    <groupId>cz.muni.fi.pb162</groupId>
    <artifactId>homework02-2022</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

**Note:** A side effect of using ``homework02-2022`` as a dependency is that you need to build HW02 prior to building this assignment (since your implementation of ``homework02-2022`` is not publicly available to maven).   
**Note:** If you have troubles with declaring the dependency, you can always copy the desired classes over to this project. However, such approach is not usable in real-world projects. 

For the second point (Reading of CSV Files) you can use [Simple CSV Reader](https://github.com/jcechace/pb162-csv-parser) or write the code yourself. 
If you decide to use this dependency, all the information is available via the aforementioned link (including dependency information and javadocs).





