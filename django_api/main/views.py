from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework import status
from rest_framework.response import Response
from django.shortcuts import redirect, HttpResponse
from rest_framework.parsers import FileUploadParser, FormParser, MultiPartParser
from .models import Images


class Index(APIView):
	parser_classes = (FormParser, MultiPartParser)
	def get(self,request):
		response = {'message' : 'Welcome!!'}
		return Response(response)

	def post(self, request, format='jpg'):
		BASE_URL = 'http://localhost:8000/media/'
		file_obj = request.FILES.getlist('file')
		print(file_obj[0])
		flist = []
		for f in file_obj:
			l = Images.objects.create(file=f).file.name
			url = BASE_URL + l
			flist.append(url)

		return Response({'url': flist})

