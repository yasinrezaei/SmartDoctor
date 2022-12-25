from django.db import models
from django.db.models import fields
from rest_framework import serializers
from django.contrib.auth.models import User
from .models import UserProfile,Chat,Message


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
        rep['city'] = instance.city.city_name
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
