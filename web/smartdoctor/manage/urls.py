from collections import UserList
from django.urls import path
from . import views

urlpatterns = [
    #home
    path('', views.home,name="home"), 
]