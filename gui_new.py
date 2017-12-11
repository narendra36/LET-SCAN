import  sys
from PyQt5.QtWidgets import QMainWindow, QApplication, QFileDialog, QMessageBox
import qt_designer 
import edit_text
from PyQt5 import QtCore
import createPdf
from threading import Thread
from PyQt5.QtCore import QThread 

class MainWindow(QMainWindow):
    def __init__(self):
        super(MainWindow, self).__init__()
        self.initialWindow()

    def initialWindow(self):
        self.ui = qt_designer.Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.convertToPdf.clicked.connect(self.convertPrograss)
        self.ui.browse.clicked.connect(self.openFileNameDialog)
        self.path = []

    def editTextWindow(self, text):
        self.ui = edit_text.Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.completeText.insertPlainText(text)
        self.ui.createPdfButton.clicked.connect(self.convertToPdf)

    def loaderWindow(self):
        self.ui = loader.Ui_MainWindow()
        self.ui.setupUi(self)

    def convertToPdf(self):
        text = self.ui.completeText.toPlainText()
        fileName = self.ui.fileName.text()
        createPdf.create(text, fileName)
        self.initialWindow()

    def runConvert(self):
        text = createPdf.imageToText(self.path)
        self.editTextWindow(text)

    def convertPrograss(self):
        if not self.path:
            QMessageBox.about(self, 'No File Selected', 'Please Select atleast One File')
            return
        
        self.ui.label_4.setGeometry(QtCore.QRect(0, 0, 800, 600))
        self.ui.centralwidget.repaint()

        text = createPdf.imageToText(self.path)
        self.editTextWindow(text)
    
    def openFileNameDialog(self):    
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        fileName, _ = QFileDialog.getOpenFileNames(self,"QFileDialog.getOpenFileName()", "","All Files (*.png, *.jpg);;PNG Files (*.png);;Jpg Files (*.jpg)", options=options)
        if fileName[0]:
            self.path = fileName
            self.ui.filePath.setText(fileName[0]+' , '+ str(len(fileName)) + ' images selected')
            print(self.path)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MainWindow()
    window.show()
    window.setFixedSize(800, 600)
    sys.exit(app.exec_())