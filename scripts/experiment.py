# Script to automate the experiment process
# Jordy Kafwe Kioni
# 6 March 2022

import os
import math

TOTAL_ENTRIES = 9919

"""Creates a subset of n entries from the sample data. 
Writes all the entries in the subset to a CSV file.
"""
def create_subset(subset_size):
    with open('data/big_vaccinations.csv', 'r') as reader:
        with open('data/vaccinations.csv','w') as writer:
            for x in range(0, subset_size):
                line = reader.readline()
                writer.write(line)


""" Creates a a file of test input - 1 input per line.
The test input is in the format: country, date. 
The files ends with an empty line.
 """
def create_testinput():
    with open('data/vaccinations.csv', 'r') as vaccinations:
        with open('testinput','w') as writer:
            for line in vaccinations:
                country, date, _ = line.split(',')
                writer.write(f'{country},{date}\n')
            else:
                writer.write('\n')

"""Driver function for the script."""
def main():
    for x in range(1, 11):
        subset_size = math.ceil(TOTAL_ENTRIES * ((x * 10)/100))
        create_subset(subset_size)
        create_testinput()
        
        print(f'Running experiment on subset {x}')
        op_count_filename = f'subset{x}'
        os.system(f'java -cp bin VaccineArrayApp {op_count_filename} < testinput')
        os.system(f'java -cp bin VaccineBSTApp {op_count_filename} < testinput')
    print('Experiment done!')

if __name__ == '__main__':
    main()