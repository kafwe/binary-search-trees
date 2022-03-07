# Script to summarise values and write to CSV file
# Jordy Kafwe
# 7 March 2022

import csv
import math
from experiment import TOTAL_ENTRIES

FIELDS = ['Data Structure', 'n','Insert Operation Count', 'Best', 'Average', 'Worst']

def summarise_values(op_counts):
    op_counts = list(map(int,op_counts))
    insert = op_counts[0]
    op_counts = op_counts[1:]
    best = min(op_counts)
    average = math.ceil(sum(op_counts)/len(op_counts))
    worst = max(op_counts)
    return insert, best, average, worst


def write_csv(data_structure):
    rows = []
    for x in range(1, 11):
        subset_size = math.ceil(TOTAL_ENTRIES * ((x * 10)/100))

        with open(f'data/{data_structure}/subset{x}.txt', 'r') as file:
            op_counts = file.readlines()
            insert, best, average, worst = summarise_values(op_counts)
            row = [data_structure, subset_size, insert, best, average, worst]
            rows.append(row)
        
    with open(f'analysis/{data_structure}.csv', 'w') as csv_file:
        csv_writer = csv.writer(csv_file)
        csv_writer.writerow(FIELDS)
        csv_writer.writerows(rows)


def main():
    write_csv('array')
    write_csv('bst')

if __name__ == '__main__':
    main()