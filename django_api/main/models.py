from django.db import models
from datetime import datetime
from django.core.files.storage import FileSystemStorage
from django.conf import settings

fs = FileSystemStorage(location=settings.MEDIA_URL)


class Images(models.Model):
    file = models.FileField(upload_to='images/%Y/%m/%d', storage=fs)
    upload_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.file.name

