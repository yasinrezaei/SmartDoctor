from django.urls import path
from rest_framework.authtoken.views import obtain_auth_token
from .views import CreateUserView
urlpatterns = [
    path('api-token-auth/', obtain_auth_token), 
    path('api-register-user', CreateUserView.as_view()), 
    #path('user-info/', UserInfo.as_view(), name="user-info")
]