import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

array = pd.read_csv('analysis/array.csv')
bst = pd.read_csv('analysis/bst.csv')
df = pd.concat([array, bst], ignore_index=True)

def plot_insertion():
    sns.scatterplot(df['n'], df['insertion'])
    