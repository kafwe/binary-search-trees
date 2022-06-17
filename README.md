The goal of this assignment is to compare the Binary Search Tree with a traditional unsorted array data structure, both implemented in Java, using an application to obtain daily vaccination numbers for a list of countries. A more elaborate application could offer additional functionality but we focus on just this feature for this assignment.

## Folder Structure
- `src`: the folder to maintain sources
- `data`: the folder that contains all the vaccination data and all the data from doing the experiment.
- `scripts`: the folder that contains all the Python scripts
- `analysis`: the folder that contains all the results from the experiment and the CSV files summarising all the findings

## Dataset

The attached file (vaccinations.csv) is a listing of country names, dates, and vaccination numbers.

This data is derived from the dataset made available by Our World in Data (https://github.com/owid/covid-19-data/tree/master/public/data/vaccinations).

There are 9919 entries in the subset used for the assignment, corresponding to data for 2022 only. The order of entries has been randomized.

Study the data carefully. The pre-processing is non-trivial but something you can do yourself as well.

In your application, you MUST write your own code to read in the text file. Your data structure items must each store 3 values for an entry, such as the ones below. Hint: The key to use is the concatenation of the country and date.

-   Angola
-   2022-01-10
-   74210

## Part 1: Programs

Create 2 applications as described below to store and retrieve data from 2 different data structures.

**VaccineArrayApp**

Write an application  **VaccineArrayApp**  to read in the attached text file (vaccinations.csv) and store the data items within a traditional array (a single array of objects). There are 9919 data items - you may use a fixed-size array or try to determine the size programmatically. Do not use a LinkedList, ArrayList or other advanced data structure and do not sort the data.

Your program must ask the user to enter a date and a list of countries (ending with an empty line), then it must print out the number of vaccinations for each country on that date in the same sequence.

The following output is an example.

    Enter the date:
    2022-02-17
    Enter the list of countries (end with an empty line):
    Norway
    Suriname
    South Namibia
    
    Results:
    Norway = 5803
    Suriname = 134
    South Namibia = <Not Found>

You should be able to invoke your application using commands such as

    java -cp bin VaccineArrayApp

Given that your program is interactive, you can use input redirection from a file during testing.

    java -cp bin VaccineArrayApp < testdata

Test your application with 3 lists of data of different sizes and at least 1 list that contains one or more invalid names. Use output redirection in Unix to save the output in each case to different files.

**VaccineBSTApp**

Write an application  **VaccineBSTApp**  to perform the same tasks, but using a Binary Search Tree (BST) instead of an array.

Your BST implementation can be created from scratch or re-used from anywhere. You may NOT replace the BST with a different data structure and you may not use a balanced tree.

Test your application with the same test data you used previously. Use output redirection in Unix to save the output in each case to different files.

## Part 2: Experiment

Conduct an experiment with  **VaccineArrayApp**  and  **VaccineBSTApp**  to demonstrate the speed difference for searching between a BST and a traditional array.

**Instrumentation**

Add additional code to your programs from Part 1 to discretely count the number of comparison operations (<, >, =) you are performing in the code. Only count where you are comparing the keys. This is called  **instrumentation**. There are 3 basic steps.

First, create a variable/object (e.g., opCount=0) somewhere in your code to track the counter; maybe use an instance variable in the data structure class.

Secondly, wherever there is an operation you want to count, increment the counter (opCount++). For example:

    opCount++; // instrumentation`
    
    if (queryString == theKey)  
    ...

Finally, report the value of the counter before the program terminates. Maybe add a method to write the value to a file before the program terminates or print it to the screen.

**Experiment**

Vary the size of the dataset (n) and measure the number of comparison operations in the best/average/worst case for different values of n. Use 10 values of n that are spaced approximately equally apart (n=991, 1982, ..., 9919). For each value of n:

-   Create a subset of n entries from the sample data (preferably use a random subset of lines).
-   Run both instrumented applications for every one of the n country+date entries corresponding to the subset of the data file. This means your query list has exactly one item in it each time but your data structure has n items inserted. Store all operation count values for both search and insert operations.
-   Determine the minimum (best case), maximum (worst case) and average of these count values.

**It is recommended that you use Unix or Python scripts to automate this process.**

## Report

Write a report (of up to 6 pages) that includes the following:

-   What your OO design is: what classes you created and how they interact.
-   What the goal of the experiment is and how you executed the experiment.
-   What test values you used in the trial runs (Part 1) and what the output was in each case.
-   What your final results are (use one or more graphs), showing best, average and worst cases for both applications. Discuss what the results mean.
-   A statement of what you included in your application(s) that constitutes creativity - how you went beyond the basic requirements of the assignment.
-   Summary statistics from your use of git to demonstrate usage. Print out the first 10 lines and last 10 lines from "git log" , with line numbers added. You can use a Unix command such as:

`git log | (ln=0; while read l; do echo $ln\: $l; ln=$((ln+1)); done) | (head -10; echo ...; tail -10)`

## Dev requirements

As a software developer, you are required to make appropriate use of the following tools:

-   git, for source code management
-   javadoc, for documentation generation
-   make, for automation of compilation and documentation generation

## Submission requirements

Submit a .tar.gz compressed archive containing:

-   Makefile
-   src/
    -   all source code
-   bin/
    -   all class files
-   doc/
    -   javadoc output
-   report.pdf

Your report must be in PDF format. Do not submit the git repository.

## Marking Guidelines

Your assignment will be marked by tutors, using the following marking guide.

|Artefact|Aspect |Mark|
|--|--|--|
| Report | Appropriate design and implementation of OOP and data structures |10|
| Report | Experiment description |10|
|  |Trial test values and outputs (Part 1 Array) |5|
|  | Trial test values and outputs (Part 1 BST) |5|
|  | Discussion of results |10|
|  |  Creativity |10|
|  | Git usage log|5|
| Code |Works correctly, looks reasonable and no obvious inefficiencies  |20|
| Dev |  Documentation - javadoc|10|
|  |  Makefile - make and clean targets (docs is optional)|5|