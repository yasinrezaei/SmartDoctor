from django.contrib import admin
from .models import UserProfile,DoctorProfile,City,Booking,BookingSettings
# Register your models here.



class BookingAdmin(admin.ModelAdmin):
    list_display = ("user", "doctor", "date", "time", "approved")
    list_filter = ("approved", "date")
    ordering = ("date", "time")
admin.site.register(Booking,BookingAdmin)

class BookingSettingAdmin(admin.ModelAdmin):
    list_display = ("doctor",)
admin.site.register(BookingSettings,BookingSettingAdmin)


#-----------------------------------------

class CityAdmin(admin.ModelAdmin):
    list_display=('city_name',)
    search_fields=('city_name',)

admin.site.register(City,CityAdmin)


class UserProfileAdmin(admin.ModelAdmin):
    list_display=('full_name','age','city')
    list_filter=(['city'])
    search_fields=('full_name',)

admin.site.register(UserProfile,UserProfileAdmin)

class DoctorProfileAdmin(admin.ModelAdmin):
    list_display=('full_name','city','address')
    list_filter=(['city','gender'])
    search_fields=('full_name',)

admin.site.register(DoctorProfile,DoctorProfileAdmin)