from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView
from django.db.models import query, Q
from rest_framework.decorators import api_view,permission_classes
from rest_framework import status
from rest_framework.permissions import AllowAny,IsAuthenticated
from rest_framework.generics import ListCreateAPIView,RetrieveDestroyAPIView, RetrieveUpdateDestroyAPIView, UpdateAPIView,RetrieveUpdateAPIView,CreateAPIView
from django.contrib.auth.models import User
from rest_framework import permissions
from .serializers import CreateUserSerializer,UserProfileSerializer
from .models import UserProfile



#--------------------user-------------------

class CreateUserView(CreateAPIView): 
    queryset  = User.objects.all()
    permission_classes = [
        permissions.AllowAny 
    ]
    serializer_class = CreateUserSerializer 

#------------------ create profile -------------------
class CreateUserProfileView(CreateAPIView): 
    model = UserProfile
    permission_classes = [
        permissions.AllowAny 
    ]
    serializer_class = UserProfileSerializer


#------------------------- get profile ----------------------

#http://127.0.0.1:8000/api/profile?user_id=1
class UserProfileDetailView(APIView):
    def get(self,request):
        try:
            user=UserProfile.objects.get(user_id=request.query_params['user_id'])
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=UserProfileSerializer(user)
        return Response(ser.data,status=status.HTTP_200_OK)

#-------------------------Edit Profile--------------------------------
class EditUserProfileView(UpdateAPIView): 
    queryset=UserProfile.objects.all()
    serializer_class=UserProfileSerializer 

