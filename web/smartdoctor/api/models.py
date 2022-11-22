from django.db import models
from django.contrib.auth.models import User
#---------------City------------------------------
class City(models.Model):
    city_name = models.CharField(verbose_name = "نام شهر", max_length=50)
    def __str__(self):
        return self.city_name
    class Meta:
        verbose_name = 'شهر '
        verbose_name_plural = 'شهر ها'

#----------------------User Profile--------------------------
class UserProfile(models.Model):
  user = models.ForeignKey(User,verbose_name="کاربر",on_delete=models.PROTECT,blank=True,null=True)
  full_name = models.CharField(max_length=255,verbose_name="نام و نام خانوادگی",blank=True,null=True)
  age = models.IntegerField(verbose_name="سن",blank=True,null=True)
  city = models.ForeignKey(City,verbose_name="شهر",on_delete=models.PROTECT,blank=True,null=True)
  def __str__(self):
        return self.full_name
  class Meta:
        verbose_name = 'کاربر عادی '
        verbose_name_plural = 'کاربران عادی'
#----------------------Doctor Profile--------------------------
GENDER_CHOICES = (
    ('زن','female'),
    ('مرد', 'male'),
    ('دیگر','others')
)
class DoctorProfile(models.Model):
  user = models.ForeignKey(User,verbose_name="کاربر",on_delete=models.PROTECT,blank=True,null=True)
  full_name = models.CharField(max_length=255,verbose_name="نام و نام خانوادگی",blank=True,null=True)
  city = models.CharField(max_length = 30,verbose_name="شهر",blank=True,null=True)
  gmc_number = models.CharField(max_length=255,verbose_name="شماره نظام پزشکی",blank=True,null=True)
  gender = models.CharField(max_length=6, choices=GENDER_CHOICES, default='male',verbose_name="جنسیت")
  age = models.IntegerField(verbose_name="سن",blank=True,null=True)
  city = models.ForeignKey(City,verbose_name="شهر",on_delete=models.PROTECT,blank=True,null=True)
  address = models.CharField(max_length = 255,verbose_name="آدرس",blank=True,null=True)
  def __str__(self):
        return self.full_name
  class Meta:
        verbose_name = 'پزشک  '
        verbose_name_plural = 'پزشکان '


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
    user = models.ForeignKey(User,
                             on_delete=models.CASCADE, blank=True, null=True,verbose_name ="بیمار")
    doctor = models.ForeignKey(DoctorProfile,verbose_name="پزشک",on_delete=models.PROTECT,blank=True,null=True)
    date = models.DateField(verbose_name="تاریخ")
    time = models.TimeField(verbose_name="ساعت")
    approved = models.BooleanField(default=True,verbose_name="تایید شده")

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self) -> str:
        return self.user_name or "(No Name)"
    class Meta:
        verbose_name = 'نوبت'
        verbose_name_plural = ' نوبت ها'

class BookingSettings(models.Model):
    # Doctor
    doctor = models.ForeignKey(DoctorProfile,verbose_name="پزشک",on_delete=models.PROTECT,blank=True,null=True)
    # Date
    max_booking_per_day = models.IntegerField(null=True, blank=True,verbose_name="حداکثر بیمار در روز")
    # Time
    start_time = models.TimeField(verbose_name="ساعت شروع کار")
    end_time = models.TimeField(verbose_name="ساعت پایان کار")
    period_of_each_booking = models.CharField(max_length=3, default="30", choices=BOOKING_PERIOD, verbose_name="زمان هر نوبت")
    class Meta:
        verbose_name = 'تنظیم نوبت دکتر'
        verbose_name_plural = ' تنظیمات نوبت دکتر ها'





