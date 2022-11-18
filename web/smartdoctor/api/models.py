from django.db import models

# Create your models here.
class UserProfile(models.Model):
  full_name = models.CharField(max_length=255,verbose_name="نام و نام خانوادگی",blank=True,null=True)
  age = models.IntegerField(verbose_name="سن",blank=True,null=True)
  city = models.CharField(max_length = 30,verbose_name="شهر",blank=True,null=True)

