from django.shortcuts import render
from api.models import UserProfile
# Create your views here.
def home(request):
    profiles = UserProfile.objects.all()
    context = {
        'profiles': profiles
    }
    return render(request, 'main.html', context)