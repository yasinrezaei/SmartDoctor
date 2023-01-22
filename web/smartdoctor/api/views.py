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
from .serializers import CreateUserSerializer,UserProfileSerializer,ChatSerializer,MessageSerializer,CitySerializer,UserSerializer,BookingSerializer,BookingSettingsSerializer,MedicalTestSerializer,MedicalTestResponseSerializer
from .models import UserProfile,Chat,Message,City,Booking,BookingSettings,MedicalTest,MedicalTestResponse
from . import a
from django.http import HttpResponse
import json




#----------------------test---------------
def createCity(request):
    message = a.sayHello()
    a.createCity("bandar")
    return HttpResponse("<Html> <h1>"+message+"</h1></Html>")

def createResponse(test_id):
    a.createResponse(test_id)

#--------------------user-------------------
#http://127.0.0.1:8000/api/api-register-user
class CreateUserView(CreateAPIView): 
    queryset  = User.objects.all()
    permission_classes = [
        permissions.AllowAny 
    ]
    serializer_class = CreateUserSerializer 
#http://127.0.0.1:8000/api/user-detail?username=yasin
class GetUserDetailView(APIView):
    def get(self,request):
        try:
            user=User.objects.get(username = request.query_params['username'])
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=UserSerializer(user)
        return Response(ser.data,status=status.HTTP_200_OK)

#------------------ create profile -------------------
#this model create by signal(when user created)
class CreateUserProfileView(CreateAPIView): 
    model = UserProfile
    permission_classes = [
        permissions.AllowAny 
    ]
    serializer_class = UserProfileSerializer


#------------------------- get profile ----------------------
#http://127.0.0.1:8000/api/user-profile?user_id=1
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

#-----------------------------City-----------------------------------
#http://127.0.0.1:8000/api/city-list/
class CityListView(ListCreateAPIView):
    permission_classes = [
        permissions.AllowAny 
    ]
    queryset=City.objects.all()
    serializer_class=CitySerializer


#----------------------------Chat and message---------------------------------------
#get chat messages
#http://127.0.0.1:8000/api/chat-messages?chat_id=1
class ChatMessagesView(APIView):
    def get(self,request):
        try:
            chats=Message.objects.filter(chat=request.query_params['chat_id'])
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=MessageSerializer(chats,many=True)
        return Response(ser.data,status=status.HTTP_200_OK)


#create chat 
#http://127.0.0.1:8000/api/create-chat/
class CreateChatView(ListCreateAPIView):
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
    def get(self,request):
        try:
            chats = Chat.objects.filter(doctor_id = request.query_params['profile_id'] ) 
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=ChatSerializer(chats,many=True)
        return Response(ser.data,status=status.HTTP_200_OK)
#---------------------Medical test-----------
class CreateMedicalTestView(ListCreateAPIView):
    permission_classes = [
        permissions.AllowAny 
    ]
    queryset=MedicalTest.objects.all()
    serializer_class=MedicalTestSerializer

#get user tests
class UserMedicalTestsListView(APIView):
    permission_classes = [
        permissions.AllowAny 
    ]
    def get(self,request):
        try:
            chats = MedicalTest.objects.filter(user_id = request.query_params['profile_id'] ) 
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=MedicalTestSerializer(chats,many=True)
        return Response(ser.data,status=status.HTTP_200_OK)

#-------------------------------
class GetResponseView(APIView):
    permission_classes = [
        permissions.AllowAny 
    ]
    def get(self,request):
        a.createResponse(int(request.query_params['test_id']))
        return Response(status=status.HTTP_200_OK)
class GetMedicalTestResponseDetailView(APIView):
    permission_classes = [
        permissions.AllowAny 
    ]
    def get(self,request):
        try:
            response=MedicalTestResponse.objects.get(test_id = request.query_params['test_id'])
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=MedicalTestResponseSerializer(response)
        return Response(ser.data,status=status.HTTP_200_OK)
#-------------------booking--------------------
#http://127.0.0.1:8000/api/create-booking/
class CreateBookingView(ListCreateAPIView):
    queryset=Booking.objects.all()
    serializer_class=BookingSerializer

#http://127.0.0.1:8000/api/user-bookings/?profile_id=6
class UserBookingListView(APIView):
    def get(self,request):
        try:
            bookings = Booking.objects.filter(user = request.query_params['profile_id'] ) 
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=BookingSerializer(bookings,many=True)
        return Response(ser.data,status=status.HTTP_200_OK)
#http://127.0.0.1:8000/api/doctor-bookings/?profile_id=2
class DoctorBookingListView(APIView):
    def get(self,request):
        try:
            bookings = Booking.objects.filter(doctor = request.query_params['profile_id'] ) 
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=BookingSerializer(bookings,many=True)
        return Response(ser.data,status=status.HTTP_200_OK)

#-------------------booking settings--------------------
#http://127.0.0.1:8000/api/doctor-booking-settings/?profile_id=2
class DoctorBookingSettingsView(APIView):
    def get(self,request):
        try:
            setting = BookingSettings.objects.get(doctor = request.query_params['profile_id'] ) 
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        ser=BookingSettingsSerializer(setting)
        return Response(ser.data,status=status.HTTP_200_OK)
