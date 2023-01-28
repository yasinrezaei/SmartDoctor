from collections import UserList
from django.urls import path
from rest_framework.authtoken.views import obtain_auth_token
from .views import CreateUserView,UserProfileDetailView,CreateUserProfileView,EditUserProfileView,CreateChatView,DeleteChatView,CreateMessageView,DeleteUpdateMessageView,UserChatsListView,DoctorChatsListView,ChatMessagesView,CityListView,GetUserDetailView,CreateBookingView,UserBookingListView,DoctorBookingListView,DoctorBookingSettingsView,CreateMedicalTestView,UserMedicalTestsListView,GetResponseView,GetMedicalTestResponseDetailView,BlogPostListView
from . import views
urlpatterns = [

    #test
    path("test/",views.createCity),
    
    path('get-response',GetResponseView.as_view()),

    #medical test
    path('user-medical-tests-list/', UserMedicalTestsListView.as_view()),
    path('create-medical-test', CreateMedicalTestView.as_view()),
    path('medical-test-response-detail/', GetMedicalTestResponseDetailView.as_view()),


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

    #blog post
    path('blog-post-list/',BlogPostListView.as_view()),

    #booking
    path('create-booking/',CreateBookingView.as_view()),
    path('user-bookings/',UserBookingListView.as_view()),
    path('doctor-bookings/',DoctorBookingListView.as_view()),

    #doctor-booking-settings
    path('doctor-booking-settings/',DoctorBookingSettingsView.as_view())
]