# Script to automate the experiment process
# Jordy Kafwe Kioni
# 6 March 2022

import os
import math
import itertools

TOTAL_ENTRIES = 9919
current_line = 0

def create_subset(subset_size):
    with open('data/big_vaccinations.csv', 'r') as reader:
        for x in range(0, subset_size):
            with open('data/vaccinations.csv','a') as writer:
                line = reader.readline()
                writer.write(line)


def create_testinput():
    global current_line
    with open('data/vaccinations.csv', 'r') as file:
        with open('testinput','w') as writer:
            for line in itertools.islice(file, current_line, current_line + 1):
                country, date, _ = line.split(',')
                writer.write(date + '\n')
                writer.write(country + '\n')
                writer.write('\n')
    current_line += 1


def empty_file(file):
    with open(file, 'w') as file:
        pass
            

def main():
    global current_line
    for x in range(1, 11): 
        current_line = 0
        empty_file('data/vaccinations.csv')
        subset_size = math.ceil(TOTAL_ENTRIES * ((x * 10)/100))
        create_subset(subset_size)
        op_count_filename = f'{x}n{subset_size}'
         
        for n in range(0, subset_size):
            create_testinput()
            os.system(f'java -cp bin VaccineArrayApp {op_count_filename} < testinput')
            #os.system(f'java -cp bin VaccineBSTApp {op_count_filename} < testinput')


if __name__ == '__main__':
    main()