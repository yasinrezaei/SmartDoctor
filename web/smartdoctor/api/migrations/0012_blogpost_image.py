# Generated by Django 4.1.3 on 2023-01-24 12:48

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0011_remove_blogpost_post_image'),
    ]

    operations = [
        migrations.AddField(
            model_name='blogpost',
            name='image',
            field=models.ImageField(blank=True, null=True, upload_to='images/', verbose_name='تصویر '),
        ),
    ]