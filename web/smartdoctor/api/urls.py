from collections import UserList
from django.urls import path
from rest_framework.authtoken.views import obtain_auth_token
from .views import CreateUserView,UserProfileDetailView,CreateUserProfileView,EditUserProfileView

urlpatterns = [
    
    path('api-token-auth/', obtain_auth_token), 
    path('api-register-user', CreateUserView.as_view()), 
    path('create-user-profile', CreateUserProfileView.as_view()), 
    path('user-profile/', UserProfileDetailView.as_view()),
    path('edit-user-profile/<int:pk>',EditUserProfileView.as_view()),
   
]