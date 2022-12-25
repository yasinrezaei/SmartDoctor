# Generated by Django 4.1.3 on 2022-12-21 22:08

import api.models
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0002_remove_message_receiver_id'),
    ]

    operations = [
        migrations.AddField(
            model_name='userprofile',
            name='national_code',
            field=models.CharField(blank=True, max_length=255, null=True, verbose_name='کد ملی'),
        ),
        migrations.AlterField(
            model_name='city',
            name='city_name',
            field=models.CharField(max_length=50, unique=True, verbose_name='نام شهر'),
        ),
        migrations.AlterField(
            model_name='userprofile',
            name='city',
            field=models.ForeignKey(blank=True, default=api.models.City.get_default_pk, null=True, on_delete=django.db.models.deletion.PROTECT, to='api.city', verbose_name='شهر'),
        ),
    ]
