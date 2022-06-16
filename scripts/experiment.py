# Script to automate the experiment process
# Jordy Kafwe Kioni
# 6 March 2022

import os
import math

TOTAL_ENTRIES = 9919


def create_subset(subset_size):
    """Creates a subset of n entries from the sample data.
    Writes all the entries in the subset to a CSV file.
    """
    with open('data/big_vaccinations.csv','r') as reader:
        with open('data/vaccinations.csv','w') as writer:
            for x in range(0, subset_size):
                line = reader.readline()
                writer.write(line)


def create_testinput():
    """ Creates a a file of test input - 1 input per line.
    The test input is in the format: country, date.
    The files ends with an empty line.
    """
    with open('data/vaccinations.csv', 'r') as vaccinations:
        with open('testinput','w') as writer:
            for line in vaccinations:
                country, date, _ = line.split(',')
                writer.write(f'{country},{date}\n')
            else:
                writer.write('\n')

def run_array(op_count_file):
    """Runs the VaccineArrayApp and write the 
    operation count values to a file 
    (using Unix output redirection)
    """
    os.system(f'java -cp bin VaccineArrayApp {"experiment"} \
        < testinput > data/array/{op_count_file}')

def run_bst(op_count_file):
    """Runs the VaccineBSTApp and writes the 
    operation count values to a file 
    (using Unix output redirection)
    """
    os.system(f'java -cp bin VaccineBSTApp  {"experiment"} \
        < testinput > data/bst/{op_count_file}')


def main():
    for x in range(1, 11):
        subset_size = math.ceil(TOTAL_ENTRIES * ((x * 10)/100))
        create_subset(subset_size)
        create_testinput()
        
        print(f'Running experiment on subset {x}')
        op_count_file = f'subset{x}.txt'
        run_array(op_count_file)
        run_bst(op_count_file)

    print('Experiment done!')

if __name__ == '__main__':
    main()