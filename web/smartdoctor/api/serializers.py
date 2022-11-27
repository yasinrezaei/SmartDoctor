from django.db import models
from django.db.models import fields
from rest_framework import serializers
from django.contrib.auth.models import User
from .models import UserProfile,DoctorProfile


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
        fields = ( "id", "username", "password", )

class UserProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserProfile
        fields="__all__"
    
    def to_representation(self, instance):
        rep = super(UserProfileSerializer, self).to_representation(instance)
        rep['user'] = instance.user.username
        rep['city'] = instance.city.city_name
        return rep 

class DoctorProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = DoctorProfile
        fields="__all__"
    
