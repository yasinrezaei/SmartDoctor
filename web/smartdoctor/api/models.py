from django.db import models
from django.contrib.auth.models import User
from django.db.models import signals
from django.db.models.signals import post_save
from django.dispatch import receiver


#-------------------------------------------
# 
class BlogPost(models.Model):
    title = models.CharField(verbose_name = "عنوان ", max_length=100)
    text = models.TextField(verbose_name="متن",blank=True,null=True)
    date = models.DateTimeField(auto_now_add=True,verbose_name="زمان")
    
    def __str__(self):
        return self.title
    class Meta:
        verbose_name = 'پست آموزشی   '
        verbose_name_plural = ' پست های آموزشی '

#---------------City------------------------------
class City(models.Model):
    city_name = models.CharField(verbose_name = "نام شهر", max_length=50,unique=True)
    def __str__(self):
        return self.city_name
    #for foreign key default value :)
    @classmethod
    def get_default_pk(cls):
        city, created = cls.objects.get_or_create(
            city_name='سایر ', 
        )
        return city.pk
    class Meta:
        verbose_name = 'شهر '
        verbose_name_plural = 'شهر ها'

#-----------------------------medical expertise -----------
class MedicalExpertise(models.Model):
    medical_expertise_name = models.CharField(verbose_name = "نام تخصص", max_length=50,unique=True)
    def __str__(self):
        return self.medical_expertise_name
    #for foreign key default value :)
    @classmethod
    def get_default_pk(cls):
        medical_expertise, created = cls.objects.get_or_create(
            medical_expertise_name='ندارد ', 
        )
        return medical_expertise.pk
    class Meta:
        verbose_name = 'تخصص '
        verbose_name_plural = 'تخصص ها'

#----------------------User Profile--------------------------
GENDER_CHOICES = (
    ('female','زن'),
    ('male','مرد'),
    ('others','دیگر')
)
class UserProfile(models.Model):
  user_id = models.ForeignKey(User,verbose_name="کاربر",on_delete=models.PROTECT,blank=True,null=True,related_name='profile_user')
  isDoctor = models.BooleanField(default=False,verbose_name="کاربر پزشک")
  gmc_number = models.CharField(max_length=255,verbose_name="شماره نظام پزشکی",blank=True,null=True)
  national_code = models.CharField(max_length=255,verbose_name="کد ملی",blank=True,null=True)
  full_name = models.CharField(max_length=255,verbose_name="نام و نام خانوادگی",blank=True,null=True)
  age = models.IntegerField(verbose_name="سن",blank=True,null=True)
  city = models.ForeignKey(City,verbose_name="شهر",on_delete=models.PROTECT,blank=True,null=True, default=City.get_default_pk)
  address = models.CharField(max_length = 255,verbose_name="آدرس",blank=True,null=True)
  gender = models.CharField(max_length=6, choices=GENDER_CHOICES, default='male',verbose_name="جنسیت")
  medical_expertise = models.ForeignKey(MedicalExpertise,verbose_name="تخصص",on_delete=models.PROTECT,blank=True,null=True, default=MedicalExpertise.get_default_pk)
  def __str__(self):
        return self.full_name
  class Meta:
        verbose_name = 'پروفایل  '
        verbose_name_plural = 'پروفایل ها '

#-------------Booking-----------------------------------

BOOKING_PERIOD = (
    ("5", "5M"),
    ("10", "10M"),
    ("15", "15M"),
    ("20", "20M"),
    ("25", "25M"),
    ("30", "30M"),
    ("35", "35M"),
    ("40", "40M"),
    ("45", "45M"),
    ("60", "1H"),
    ("75", "1H 15M"),
    ("90", "1H 30M"),
    ("105", "1H 45M"),
    ("120", "2H"),
    ("150", "2H 30M"),
    ("180", "3H"),
)


class Booking(models.Model):
    user = models.ForeignKey(UserProfile,on_delete=models.CASCADE, blank=True, null=True,verbose_name ="بیمار",related_name='normal_user')
    doctor = models.ForeignKey(UserProfile,verbose_name="پزشک",on_delete=models.PROTECT,blank=True,null=True,related_name='doctor_user')
    date = models.DateField(verbose_name="تاریخ")
    time = models.TimeField(verbose_name="ساعت")
    approved = models.BooleanField(default=True,verbose_name="تایید شده")

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self) -> str:
        return self.user.full_name or "(No Name)"
    class Meta:
        verbose_name = 'نوبت'
        verbose_name_plural = ' نوبت ها'

class BookingSettings(models.Model):
    doctor = models.ForeignKey(UserProfile,verbose_name="پزشک",on_delete=models.PROTECT,blank=True,null=True)
    max_booking_per_day = models.IntegerField(null=True, blank=True,verbose_name="حداکثر بیمار در روز")
    start_time = models.TimeField(verbose_name="ساعت شروع کار")
    end_time = models.TimeField(verbose_name="ساعت پایان کار")
    period_of_each_booking = models.CharField(max_length=3, default="30", choices=BOOKING_PERIOD, verbose_name="زمان هر نوبت")
    class Meta:
        verbose_name = 'تنظیم نوبت دکتر'
        verbose_name_plural = ' تنظیمات نوبت دکتر ها'

#------------------------------------------Chat----------------------
class Chat(models.Model):
    user_id = models.ForeignKey(UserProfile,on_delete=models.CASCADE, blank=True, null=True,verbose_name ="بیمار",related_name='user')
    doctor_id = models.ForeignKey(UserProfile,verbose_name="پزشک",on_delete=models.PROTECT,blank=True,null=True,related_name='doctor')
    def __str__(self):
        return self.user_id.full_name + " - "+self.doctor_id.full_name
    class Meta:
        verbose_name = 'گفت و گو'
        verbose_name_plural = ' گفت و گو ها'
class Message(models.Model):
    chat = models.ForeignKey(Chat,on_delete=models.CASCADE, blank=True, null=True,verbose_name ="چت")
    text = models.TextField(verbose_name="متن پیام",blank=True,null=True)
    sender_id = models.ForeignKey(UserProfile,on_delete=models.CASCADE, blank=True, null=True,verbose_name ="فرستنده",related_name='sender')
    date = models.DateTimeField(auto_now_add=True,verbose_name="زمان")
    class Meta:
        verbose_name = 'پیام'
        verbose_name_plural = ' پیام ها'


#--------------------Medical test----------------

class MedicalTest(models.Model):
    user_id = models.ForeignKey(UserProfile,on_delete=models.CASCADE, blank=True, null=True,verbose_name ="بیمار")
    test_input = models.CharField(max_length=200,verbose_name="ورودی تست")
    date = models.DateTimeField(auto_now_add=True,verbose_name="زمان")
    def __str__(self):
        return self.user_id.full_name
    class Meta:
        verbose_name = 'تست پزشکی'
        verbose_name_plural = ' تست های پزشکی'


#--------------------------Medical test response ----------
class MedicalTestResponse(models.Model):
  test_id = models.ForeignKey(MedicalTest,verbose_name="تست",on_delete=models.PROTECT,blank=True,null=True)
  test_response = models.CharField(max_length=100,verbose_name="بیماری")
  def __str__(self):
        return self.test_response
  class Meta:
        verbose_name = 'پاسخ تست  '
        verbose_name_plural = 'پاسخ های تست '
