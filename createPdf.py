# -*- coding: utf8 -*-

import pytesseract
from PIL import Image, ImageEnhance, ImageFilter
from fpdf import FPDF
import re
import random

def imageToText(fileList):
	print(fileList)
	txt = ""
	for fileName in fileList:
		txt += pytesseract.image_to_string(Image.open(fileName), lang='eng')
	ans = ""
	for (i, item) in enumerate(txt):
		if (i != len(txt) - 1):
			if(item != '\n'):
				ans += item
			elif(txt[i+1] == '\n'):
				ans += '\n\n'
	print("Done!")
	return ans

def create(text, file):
	letArray = []
	for i in range(ord('a'), ord('z')+1):
		letArray.append(chr(i))
	if(file == ''):
		random.shuffle(letArray)
		file = ''.join(letArray[:10])
	if not re.match("\.pdf$", file):
		file = file + '.pdf'
	print(file)
	book = FPDF()
	book.add_font('Roboto', '', 'roboto/Roboto-Regular.ttf', uni=True)
	book.set_font('Roboto','',12)
	book.add_page()
	book.write(8, text)
	book.output(file, 'F')
