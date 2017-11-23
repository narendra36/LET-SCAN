from django.conf.urls import include, url
from main import views

urlpatterns = [
    url(r'^index/$', views.Index.as_view() , name='index'),
]