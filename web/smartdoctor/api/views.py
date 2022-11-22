from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from rest_framework.permissions import AllowAny,IsAuthenticated
from rest_framework.generics import ListCreateAPIView,RetrieveDestroyAPIView, RetrieveUpdateDestroyAPIView, UpdateAPIView,RetrieveUpdateAPIView,CreateAPIView
from django.contrib.auth.models import User
from rest_framework import permissions
from .serializers import CreateUserSerializer



#--------------------user-------------------
#register user
class CreateUserView(ListCreateAPIView): 
    queryset  = User.objects.all()
    permission_classes = [
        permissions.AllowAny 
    ]
    serializer_class = CreateUserSerializer 