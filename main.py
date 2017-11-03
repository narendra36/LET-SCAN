# -*- coding: utf8 -*-

import pytesseract
from PIL import Image, ImageEnhance, ImageFilter
from fpdf import FPDF

text = pytesseract.image_to_string(Image.open('book.jpg'), lang='eng')

book = FPDF()
#book.set_font('Arial', '', 12)
book.add_font('Roboto', '', 'roboto/Roboto-Thin.ttf', uni=True)
book.set_font('Roboto','',16)
book.add_page()
book.write(8, text)
book.output('book.pdf', 'F')

print(text)
