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
from .serializers import CreateUserSerializer,UserProfileSerializer,ChatSerializer,MessageSerializer
from .models import UserProfile,Chat,Message



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


#----------------------------Chat and message---------------------------------------
#create chat 
#http://127.0.0.1:8000/api/create-chat/
class CreateChatView(ListCreateAPIView):
    permission_classes = [
        permissions.AllowAny 
    ]
    queryset=Chat.objects.all()
    serializer_class=ChatSerializer

#delete chat
#http://127.0.0.1:8000/api/delete-chat/2
class DeleteChatView(RetrieveDestroyAPIView):
    queryset=Chat.objects.all()
    serializer_class=ChatSerializer

#create message
#http://127.0.0.1:8000/api/create-chat/
class CreateMessageView(ListCreateAPIView):
    queryset=Message.objects.all()
    serializer_class=MessageSerializer

#delete message
#http://127.0.0.1:8000/api/delete-update-message/2
class DeleteUpdateMessageView(RetrieveUpdateDestroyAPIView):
    queryset=Message.objects.all()
    serializer_class=MessageSerializer


#get user chats
#http://127.0.0.1:8000/api/user-chats-list?profile_id=1    
class UserChatsListView(APIView):
    permission_classes = [
        permissions.AllowAny 
    ]
    def get(self,request):
        try:
            chats = Chat.objects.filter(user_id = request.query_params['profile_id'] ) 
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=ChatSerializer(chats,many=True)
        return Response(ser.data,status=status.HTTP_200_OK)
#get user chats
#http://127.0.0.1:8000/api/doctor-chats-list?profile_id=1    
class DoctorChatsListView(APIView):
    permission_classes = [
        permissions.AllowAny 
    ]
    def get(self,request):
        try:
            chats = Chat.objects.filter(doctor_id = request.query_params['profile_id'] ) 
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=ChatSerializer(chats,many=True)
        return Response(ser.data,status=status.HTTP_200_OK)
#get chat messages
