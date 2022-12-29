import pandas as pd
import numpy as np
from sklearn import tree
from sklearn.metrics import f1_score
from sklearn.metrics import accuracy_score


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
test_x = test_df.iloc[:, 0:131]
#print(test_x) --> works!
test_y = test_df.iloc[:, 132]
#print(test_y)

# define classification algorithm
# first let's define max depth
#raw_clf = tree.DecisionTreeClassifier()
#raw_clf = raw_clf.fit(train_x,train_y)
#print(clf.tree_.max_depth) --> result is 54

dt_clf = tree.DecisionTreeClassifier(max_depth = 54, criterion = "entropy")
dt_clf = dt_clf.fit(train_x,train_y)

# generating predictions
pred_y = dt_clf.predict(test_x)

#model evaluations

#f1 score test
f1_score_val = f1_score(test_y, pred_y ,average='micro')
print("f1 score test result is:",f1_score_val)

#accuracy
accuracy_val = accuracy_score(test_y,pred_y)
print("Accuracy score test result is:",accuracy_val)















