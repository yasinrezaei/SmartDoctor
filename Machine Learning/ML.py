import pandas as pd
import numpy as np
from sklearn import tree
import matplotlib.pyplot as plt
import seaborn as sns

# reading data frame and test
df = pd.read_csv("training_data.csv")
test_df = pd.read_csv("test_data.csv")
#print(df)
#print(test_df)

# define train and test data

train_x = df.iloc[:, 0:131]
#print(train_x) --> works!
train_y = df.iloc[:, 132]
#print(train_y) --> works!








