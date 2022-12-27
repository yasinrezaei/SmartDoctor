import pandas as pd

dataFrame = pd.read_csv("training_data.csv")

numberOfUniqD = dataFrame["prognosis"].nunique()
#print(dataFrame)

print(numberOfUniqD) # we have 41 unique diseases

uniqD = dataFrame["prognosis"].unique()
print(uniqD)
# let's write them in a file

txtFile = open("uniqueD.txt" , "w+")
for disease in uniqD:
    txtFile.write(disease + "\n")