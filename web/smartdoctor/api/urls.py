from collections import UserList
from django.urls import path
from rest_framework.authtoken.views import obtain_auth_token
from .views import CreateUserView,UserProfileDetailView,CreateUserProfileView,EditUserProfileView,CreateChatView,DeleteChatView,CreateMessageView,DeleteUpdateMessageView,UserChatsListView,DoctorChatsListView,ChatMessagesView,CityListView,GetUserDetailView

urlpatterns = [
    
    #authenticate
    path('api-token-auth/', obtain_auth_token), 

    #register
    path('api-register-user', CreateUserView.as_view()), 

    #profile
    path('create-user-profile', CreateUserProfileView.as_view()), 
    path('user-profile/', UserProfileDetailView.as_view()),
    path('user-detail/', GetUserDetailView.as_view()),
    path('edit-user-profile/<int:pk>',EditUserProfileView.as_view()),

    #chat
    path('create-chat/',CreateChatView.as_view()),
    path('delete-chat/<int:pk>',DeleteChatView.as_view()),

    #message
    path('create-message/',CreateMessageView.as_view()),
    path('delete-update-message/<int:pk>',DeleteUpdateMessageView.as_view()),
    path('chat-messages/',ChatMessagesView.as_view()),

    #chats list
    path('user-chats-list/', UserChatsListView.as_view()),
    path('doctor-chats-list/', DoctorChatsListView.as_view()),


    #city list
    path('city-list/',CityListView.as_view()),
]