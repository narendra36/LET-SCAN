from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework import status
from rest_framework.response import Response
from django.shortcuts import redirect, HttpResponse
from rest_framework.parsers import FileUploadParser, FormParser, MultiPartParser
from .models import Images
import pytesseract
from PIL import Image, ImageEnhance, ImageFilter
from fpdf import FPDF
import re
import random
import os


class Index(APIView):
	parser_classes = (MultiPartParser, FormParser)
	#parser_classes = (FileUploadParser, MultiPartParser, FormParser)
	def get(self,request):
		response = {'message' : 'Welcome!!'}
		return Response(response)

	def post(self, request, format='jpg'):
		BASE_URL = 'http://192.168.43.184:8000/'
		#file_obj = request.FILES.getlist('files')
		data = request.data.copy()
		file_obj = data['files']
		#file_obj = request.FILES['files']
		#print(request.POST['description'])
		#print(file1)
		l = Images.objects.create(file=file_obj).file.name
		print(len(file_obj))
		flist = []
		'''for f in file_obj:
			
			l=""
			l = Images.objects.create(file=f).file.name
			url = BASE_URL + l
			flist.append(url)'''
		print("Now I'll be creating PDF....")
		txt = pytesseract.image_to_string(Image.open(l), lang='eng')
		ans = ""
		for (i, item) in enumerate(txt):
			if (i != len(txt) - 1):
				if(item != '\n'):
					ans += item
				elif(txt[i+1] == '\n'):
					ans += '\n\n'
		letArray = []
		for i in range(ord('a'), ord('z')+1):
			letArray.append(chr(i))
		random.shuffle(letArray)
		file = ''.join(letArray[:10])
		file += '.pdf'
		book = FPDF()
		book.add_font('Roboto', '', 'Roboto-Regular.ttf', uni=True)
		book.set_font('Roboto','',12)
		book.add_page()
		book.write(8, txt)
		book.output(os.path.realpath(".") + '/pdf/' + file, 'F')

		print("Created file " + file)

		return Response({'pdfUrl': BASE_URL+'pdf/'+file})

