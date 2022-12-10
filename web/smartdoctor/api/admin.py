from django.contrib import admin
from .models import UserProfile,City,Booking,BookingSettings,Chat,Message


#---------------------------------------------
class ChatAdmin(admin.ModelAdmin):
    list_display = ("user_id", "doctor_id")
admin.site.register(Chat,ChatAdmin)

class MessageAdmin(admin.ModelAdmin):
    list_display = ("chat", "text","date")
admin.site.register(Message,MessageAdmin)
#----------------------------------------
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
    list_display=('full_name','isDoctor','city')
    list_filter=(['city'])
    search_fields=('full_name',)

admin.site.register(UserProfile,UserProfileAdmin)
