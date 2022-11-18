from django.contrib import admin
from .models import UserProfile
# Register your models here.
class UserProfileAdmin(admin.ModelAdmin):
    list_display=('full_name','age','city')
    list_filter=(['city'])
    search_fields=('full_name',)

admin.site.register(UserProfile,UserProfileAdmin)