from django.db.models.signals import post_save, pre_delete
from django.contrib.auth.models import User
from django.dispatch import receiver
from .models import UserProfile
 
 
@receiver(post_save, sender=User)
def create_profile(sender, instance, created, **kwargs):
    if created:
        UserProfile.objects.create(user_id=instance,full_name="کاربر")
  
#@receiver(post_save, sender=User)
#def save_profile(sender, instance, **kwargs):
#        instance.profile.save()