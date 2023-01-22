import pandas as pd
import numpy as np
from sklearn import tree




def mlFunction(mlModel ,symptDic, bitString):
    sympNames = list(symptDic.keys())
    for i in range(len(bitString)):
        symptDic[sympNames[i]] = int(bitString[i])
    df_test = pd.DataFrame(columns=list(symptDic.keys()))
    df_test.loc[0] = np.array(list(symptDic.values()))
    result = mlModel.predict(df_test)
    return result[0]
    



# reading data frame and test
df = pd.read_csv("training_data.csv")


# define train and test data

train_x = df.iloc[:, 0:132]

train_y = df.iloc[:, 132]


# define classification algorithm
# first let's define max depth
#raw_clf = tree.DecisionTreeClassifier()
#raw_clf = raw_clf.fit(train_x,train_y)
#print(clf.tree_.max_depth) --> result is 54

dt_clf = tree.DecisionTreeClassifier(max_depth = 54, criterion = "entropy")
dt_clf = dt_clf.fit(train_x,train_y)


# defaul value for symptoms
symptoms = {'itching': 1, 'skin_rash': 1, 'nodal_skin_eruptions': 1, 'continuous_sneezing': 0,
                'shivering': 0, 'chills': 0, 'joint_pain': 0, 'stomach_pain': 1, 'acidity': 0, 'ulcers_on_tongue': 0,
                'muscle_wasting': 0, 'vomiting': 0, 'burning_micturition': 1, 'spotting_ urination': 0, 'fatigue': 0,
                'weight_gain': 0, 'anxiety': 0, 'cold_hands_and_feets': 1, 'mood_swings': 0, 'weight_loss': 0,
                'restlessness': 0, 'lethargy': 0, 'patches_in_throat': 1, 'irregular_sugar_level': 0, 'cough': 0,
                'high_fever': 0, 'sunken_eyes': 0, 'breathlessness': 1, 'sweating': 0, 'dehydration': 0,
                'indigestion': 0, 'headache': 1, 'yellowish_skin': 1, 'dark_urine': 0, 'nausea': 0, 'loss_of_appetite': 0,
                'pain_behind_the_eyes': 0, 'back_pain': 0, 'constipation': 0, 'abdominal_pain': 0, 'diarrhoea': 0, 'mild_fever': 0,
                'yellow_urine': 0, 'yellowing_of_eyes': 0, 'acute_liver_failure': 0, 'fluid_overload': 0, 'swelling_of_stomach': 0,
                'swelled_lymph_nodes': 0, 'malaise': 0, 'blurred_and_distorted_vision': 0, 'phlegm': 0, 'throat_irritation': 0,
                'redness_of_eyes': 0, 'sinus_pressure': 0, 'runny_nose': 0, 'congestion': 0, 'chest_pain': 0, 'weakness_in_limbs': 0,
                'fast_heart_rate': 0, 'pain_during_bowel_movements': 0, 'pain_in_anal_region': 0, 'bloody_stool': 0,
                'irritation_in_anus': 0, 'neck_pain': 0, 'dizziness': 0, 'cramps': 0, 'bruising': 0, 'obesity': 0, 'swollen_legs': 0,
                'swollen_blood_vessels': 0, 'puffy_face_and_eyes': 0, 'enlarged_thyroid': 0, 'brittle_nails': 0, 'swollen_extremeties': 0,
                'excessive_hunger': 0, 'extra_marital_contacts': 0, 'drying_and_tingling_lips': 0, 'slurred_speech': 0,
                'knee_pain': 0, 'hip_joint_pain': 0, 'muscle_weakness': 0, 'stiff_neck': 0, 'swelling_joints': 0, 'movement_stiffness': 0,
                'spinning_movements': 0, 'loss_of_balance': 0, 'unsteadiness': 0, 'weakness_of_one_body_side': 0, 'loss_of_smell': 0,
                'bladder_discomfort': 0, 'foul_smell_of urine': 1, 'continuous_feel_of_urine': 0, 'passage_of_gases': 0, 'internal_itching': 0,
                'toxic_look_(typhos)': 0, 'depression': 0, 'irritability': 0, 'muscle_pain': 0, 'altered_sensorium': 0,
                'red_spots_over_body': 0, 'belly_pain': 0, 'abnormal_menstruation': 0, 'dischromic _patches': 0, 'watering_from_eyes': 0,
                'increased_appetite': 1, 'polyuria': 0, 'family_history': 0, 'mucoid_sputum': 0, 'rusty_sputum': 0, 'lack_of_concentration': 0,
                'visual_disturbances': 0, 'receiving_blood_transfusion': 0, 'receiving_unsterile_injections': 0, 'coma': 0,
                'stomach_bleeding': 0, 'distention_of_abdomen': 0, 'history_of_alcohol_consumption': 0, 'fluid_overload.1': 0,
                'blood_in_sputum': 0, 'prominent_veins_on_calf': 0, 'palpitations': 0, 'painful_walking': 0, 'pus_filled_pimples': 0,
                'blackheads': 0, 'scurring': 0, 'skin_peeling': 0, 'silver_like_dusting': 0, 'small_dents_in_nails': 0, 'inflammatory_nails': 0,
                'blister': 0, 'red_sore_around_nose': 0, 'yellow_crust_ooze': 0}

# this section is just for test, you should give your valid bitstring.
# Other parts there's no need to change
##########################################
bitString = ""
for i in range (132):
    if i > 50:
        bitString = bitString + "1"
    else:
        bitString = bitString + "0"
##########################################

result = mlFunction(dt_clf,symptoms, bitString)
#print(result)












