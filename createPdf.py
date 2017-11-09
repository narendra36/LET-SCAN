# -*- coding: utf8 -*-

import pytesseract
from PIL import Image, ImageEnhance, ImageFilter
from fpdf import FPDF

def create(fileList):
	book = FPDF()
	book.add_font('Roboto', '', 'roboto/Roboto-Thin.ttf', uni=True)
	book.set_font('Roboto','',16)
	book.add_page()
	for fileName in fileList:
		text = pytesseract.image_to_string(Image.open(fileName), lang='eng')
		book.write(8, text)
	book.output('book.pdf', 'F')
