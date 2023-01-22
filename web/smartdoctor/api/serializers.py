from django.db import models
from django.db.models import fields
from rest_framework import serializers
from django.contrib.auth.models import User
from .models import UserProfile,Chat,Message,City,Booking,BookingSettings,MedicalTest,MedicalTestResponse


class CreateUserSerializer(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True)

    def create(self, validated_data):

        user = User.objects.create_user(
            username=validated_data['username'],
            password=validated_data['password'],
        )
        
        return user
    class Meta:
        model = User
        fields =  "__all__"

class UserProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserProfile
        fields="__all__"
    
    def to_representation(self, instance):
        rep = super(UserProfileSerializer, self).to_representation(instance)
        rep['user_id'] = instance.user_id.username
        rep['city_name'] = instance.city.city_name
        rep['medical_expertise'] = instance.medical_expertise.medical_expertise_name
        return rep 

    

#---------------------Chat and message----------------------
class ChatSerializer(serializers.ModelSerializer):
    class Meta:
        model = Chat
        fields="__all__"
    def to_representation(self, instance):
        rep = super(ChatSerializer, self).to_representation(instance)
        rep['user_id'] = instance.user_id.full_name
        rep['doctor_id'] = instance.doctor_id.full_name
        return rep

class MessageSerializer(serializers.ModelSerializer):
    class Meta:
        model = Message
        fields="__all__"

#----------------------------city--------
class CitySerializer(serializers.ModelSerializer):
    class Meta:
        model = City
        fields="__all__"


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields=("id","username")


class MedicalTestSerializer(serializers.ModelSerializer):
    class Meta:
        model = MedicalTest
        fields="__all__"

class MedicalTestResponseSerializer(serializers.ModelSerializer):
    class Meta:
        model = MedicalTestResponse
        fields="__all__"

class BookingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Booking
        fields="__all__"
    def to_representation(self, instance):
        rep = super(BookingSerializer, self).to_representation(instance)
        rep['user_full_name'] = instance.user.full_name
        rep['doctor_full_name'] = instance.doctor.full_name
        return rep

class BookingSettingsSerializer(serializers.ModelSerializer):
    class Meta:
        model = BookingSettings
        fields="__all__"
    def to_representation(self, instance):
        rep = super(BookingSettingsSerializer, self).to_representation(instance)
        rep['doctor_full_name'] = instance.doctor.full_name
        return rep