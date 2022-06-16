# Script to summarise values and write to CSV file
# Jordy Kafwe
# 7 March 2022

import csv
import math
from experiment import TOTAL_ENTRIES

FIELDS = ['Data Structure', 'n', 'Insert Best', 'Insert Average', 'Insert Worst', 
'Search Best', 'Search Average', 'Search Worst']

def summarise_values(op_counts):
    """Returns the best, average, and worst cases."""
    op_counts = list(map(int, op_counts))
    best = min(op_counts)
    average = math.ceil(sum(op_counts)/len(op_counts))
    worst = max(op_counts)
    return best, average, worst

def get_row(data_structure, subset_size, num):
    """Returns a row of the CSV file."""
    with open(f'data/{data_structure}/subset{num}.txt', 'r') as file:
        op_counts = file.readlines()
        insert_counts = op_counts[:subset_size]
        search_counts = op_counts[subset_size:]
        insert_best, insert_average, insert_worst = summarise_values(insert_counts)
        search_best, search_average, search_worst = summarise_values(search_counts)

    row = [data_structure, subset_size, insert_best, insert_average,
        insert_worst, search_best, search_average, search_worst]

    return row


def write_csv(data_structure):
    """Writes all the summarised values for a data 
    structure into a CSV file.
    """
    rows = []

    for x in range(1, 11):
        subset_size = math.ceil(TOTAL_ENTRIES * ((x * 10)/100))
        row = get_row(data_structure, subset_size, x)
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