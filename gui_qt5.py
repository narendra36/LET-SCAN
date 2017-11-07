import sys
from PyQt5.QtWidgets import QApplication, QWidget, QInputDialog, QLineEdit, QFileDialog, QPushButton, QGridLayout
from PyQt5.QtGui import QIcon
 
class App(QWidget):
 
    def __init__(self):
        super().__init__()
        self.left = 10
        self.top = 10
        self.width = 640
        self.height = 480
        self.path =''
        self.lineEdit = QLineEdit()
        self.initUI()


    def openFileNameDialog(self):    
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        fileName, _ = QFileDialog.getOpenFileName(self,"QFileDialog.getOpenFileName()", "","All Files (*);;PNG Files (*.png);;Jpg Files (*.jpg)", options=options)
        if fileName:
            self.path = fileName
            self.lineEdit.setText(fileName)
            print(self.path)
            #print(fileName)
  
    def initUI(self):
        self.setWindowTitle('LET\'SCAN ')  
        self.setGeometry(self.left, self.top, self.width, self.height)

        btn = QPushButton('Browse', self)
        btn.resize(btn.sizeHint())
        btn.move(50, 50)            
        btn.clicked.connect(self.openFileNameDialog)

        # grid layout
        grid = QGridLayout()
        grid.setSpacing(10)
        grid.addWidget(self.lineEdit, 1, 0)
        grid.addWidget(btn, 1, 1)
        
        self.setLayout(grid)          
        self.show()
 

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = App()
    sys.exit(app.exec_())