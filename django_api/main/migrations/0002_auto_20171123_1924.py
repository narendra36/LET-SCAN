# -*- coding: utf-8 -*-
# Generated by Django 1.11.7 on 2017-11-23 19:24
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('main', '0001_initial'),
    ]

    operations = [
        migrations.RenameModel(
            old_name='LiveRecord',
            new_name='Images',
        ),
    ]