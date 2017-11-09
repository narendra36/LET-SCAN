import  sys
from PyQt5.QtWidgets import QMainWindow, QApplication, QFileDialog
import qt_designer 
from PyQt5 import QtGui

class MainWindow(QMainWindow):
    def __init__(self):
        super(MainWindow, self).__init__()
        self.ui = qt_designer.Ui_MainWindow()
        self.ui.setupUi(self) 
        self.ui.convertToPdf.clicked.connect(self.convertPrograss)
        self.ui.browse.clicked.connect(self.openFileNameDialog)
        self.ui.progressBar.setValue(0)

    def convertPrograss(self):
        self.completed = 0

        while self.completed < 100:
            self.completed += 0.0001
            self.ui.progressBar.setValue(self.completed)

    def openFileNameDialog(self):    
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        fileName, _ = QFileDialog.getOpenFileNames(self,"QFileDialog.getOpenFileName()", "","All Files (*.png, *.jpg);;PNG Files (*.png);;Jpg Files (*.jpg)", options=options)
        if fileName[0]:
            self.path = fileName
            self.ui.filePath.setText(fileName[0]+' , '+ str(len(fileName)) + ' images selected')
            print(self.path)
            #print(fileName)       

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MainWindow()
    window.show()
    sys.exit(app.exec_())