''' this is for converting a imagein to a text file '''
import pytesseract
from PIL import Image, ImageEnhance, ImageFilter

'''im = Image.open("temp.jpg") # the second one 
im = im.filter(ImageFilter.MedianFilter())
enhancer = ImageEnhance.Contrast(im)
im = enhancer.enhance(2)
im = im.convert('1')
im.save('temp2.jpg')'''
#file = open("book.txt",'w') 
text = pytesseract.image_to_string(Image.open('book.jpg'))
#text = text.decode('unicode_escape').encode('utf-8')
#file.write(text);
#file.close();
print(text)
