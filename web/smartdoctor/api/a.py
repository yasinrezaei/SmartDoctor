import os
import django
from .TestModel import ML

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'smartdoctor.settings')
django.setup()
from api.models import City,MedicalTestResponse,MedicalTest

def sayHello():
    return "City created successfully"

def createCity(city_name):
    c = City()
    c.city_name = city_name
    c.save()

def createResponse(test):
    inp = MedicalTest.objects.get(id=test).test_input
    result = ML.mlf(inp)
    mt = MedicalTestResponse()
    mt.test_id = MedicalTest.objects.get(id = test )
    mt.test_response = result
    mt.save()











