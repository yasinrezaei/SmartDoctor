import os
import django

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'smartdoctor.settings')
django.setup()
from api.models import City

def sayHello():
    return "City created successfully"

def createCity(city_name):
    c = City()
    c.city_name = city_name
    c.save()










