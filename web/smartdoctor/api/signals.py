from django.db.models.signals import post_save, pre_delete
from django.contrib.auth.models import User
from django.dispatch import receiver
from .models import UserProfile,MedicalTest,MedicalTestResponse
#from .views import CreateUserView
 
@receiver(post_save, sender=User)
def create_profile(sender, instance, created, **kwargs):
    if created:
        UserProfile.objects.create(user_id=instance,full_name="کاربر")


#@receiver(post_save, sender=MedicalTest)
#def create_medical_test(sender, instance, created, **kwargs):
#    if created:
#        MedicalTestResponse.objects.create(test_id=instance,test_response="BBB")
  
